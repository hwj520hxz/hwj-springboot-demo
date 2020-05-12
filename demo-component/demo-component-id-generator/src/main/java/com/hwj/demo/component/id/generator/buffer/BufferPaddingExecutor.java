/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hwj.demo.component.id.generator.buffer;


import com.hwj.demo.component.id.generator.utils.NamingThreadFactory;
import com.hwj.demo.component.id.generator.utils.PaddedAtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents an executor for padding {@link RingBuffer}<br>
 * There are two kinds of executors: one for scheduled padding, the other for padding immediately.
 *
 * @author yutianbao
 */
public class BufferPaddingExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RingBuffer.class);

    /** Constants */
    private static final String WORKER_NAME = "RingBuffer-Padding-Worker";
    private static final String SCHEDULE_NAME = "RingBuffer-Padding-Schedule";
    private static final long DEFAULT_SCHEDULE_INTERVAL = 5 * 60L; // 5 minutes

    /** 是否运行缓冲区填充 */
    private final AtomicBoolean running;

    /** 最近的毫秒数 */
    private final PaddedAtomicLong lastSecond;

    /** RingBuffer & BufferUidProvider */
    private final RingBuffer ringBuffer;
    private final BufferedUidProvider uidProvider;

    /** 线程池 */
    private final ExecutorService bufferPadExecutors;
    /** 填充调度线程 */
    private final ScheduledExecutorService bufferPadSchedule;

    /** 时间间隔 */
    private long scheduleInterval = DEFAULT_SCHEDULE_INTERVAL;

    /**
     * Constructor with {@link RingBuffer} and {@link BufferedUidProvider}, default use schedule
     *
     * @param ringBuffer {@link RingBuffer}
     * @param uidProvider {@link BufferedUidProvider}
     */
    public BufferPaddingExecutor(RingBuffer ringBuffer, BufferedUidProvider uidProvider) {
        this(ringBuffer, uidProvider, true);
    }

    /**
     * Constructor with {@link RingBuffer}, {@link BufferedUidProvider}, and whether use schedule padding
     *
     * @param ringBuffer {@link RingBuffer}
     * @param uidProvider {@link BufferedUidProvider}
     * @param usingSchedule
     */
    public BufferPaddingExecutor(RingBuffer ringBuffer, BufferedUidProvider uidProvider, boolean usingSchedule) {
        this.running = new AtomicBoolean(false);
        this.lastSecond = new PaddedAtomicLong(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        this.ringBuffer = ringBuffer;
        this.uidProvider = uidProvider;

        // initialize thread pool
        int cores = Runtime.getRuntime().availableProcessors();
        bufferPadExecutors = Executors.newFixedThreadPool(cores * 2, new NamingThreadFactory(WORKER_NAME));

        // initialize schedule thread
        if (usingSchedule) {
            bufferPadSchedule = Executors.newSingleThreadScheduledExecutor(new NamingThreadFactory(SCHEDULE_NAME));
        } else {
            bufferPadSchedule = null;
        }
    }

    /**
     * Start executors such as schedule
     */
    public void start() {
        if (bufferPadSchedule != null) {
            bufferPadSchedule.scheduleWithFixedDelay(() -> paddingBuffer(), scheduleInterval, scheduleInterval, TimeUnit.SECONDS);
        }
    }

    /**
     * Shutdown executors
     */
    public void shutdown() {
        if (!bufferPadExecutors.isShutdown()) {
            bufferPadExecutors.shutdownNow();
        }

        if (bufferPadSchedule != null && !bufferPadSchedule.isShutdown()) {
            bufferPadSchedule.shutdownNow();
        }
    }

    /**
     * Whether is padding
     *
     * @return
     */
    public boolean isRunning() {
        return running.get();
    }

    /**
     * Padding buffer in the thread pool
     */
    public void asyncPadding() {
        bufferPadExecutors.submit(this::paddingBuffer);
    }

    /**
     * Padding buffer fill the slots until to catch the cursor
     */
    public void paddingBuffer() {
        LOGGER.info("Ready to padding buffer lastSecond:{}. {}", lastSecond.get(), ringBuffer);

        // is still running
        if (!running.compareAndSet(false, true)) {
            LOGGER.info("Padding buffer is still running. {}", ringBuffer);
            return;
        }
        /**
         * 时间回拨问题：时间校准，以及其他因素，可能导致服务器时间回退（时间向前快进不会有问题），如果恰巧回退前生成过一些ID，而时间回退后，生成的ID就有可能重复
         */
        // fill the rest slots until to catch the cursor
        boolean isFullRingBuffer = false;
        while (!isFullRingBuffer) {
            // 调用nextIdsForOneSecond方法获取UID list，注意此处的时间使用lastSecond.incrementAndGet()方法时间自增的方式获取
            // 而不是System.currentTimeMillis()获取，解决了运行时时间回拨问题
            List<Long> uidList = uidProvider.provide(lastSecond.incrementAndGet());
            for (Long uid : uidList) {
                isFullRingBuffer = !ringBuffer.put(uid);
                if (isFullRingBuffer) {
                    break;
                }
            }
        }

        // not running now
        running.compareAndSet(true, false);
        LOGGER.info("End to padding buffer lastSecond:{}. {}", lastSecond.get(), ringBuffer);
    }

    /**
     * Setters
     */
    public void setScheduleInterval(long scheduleInterval) {
        Assert.isTrue(scheduleInterval > 0, "Schedule interval must positive!");
        this.scheduleInterval = scheduleInterval;
    }

}
