
package com.hwj.demo.component.id.generator.worker;


import com.hwj.demo.component.id.generator.utils.DockerUtils;
import com.hwj.demo.component.id.generator.utils.NetUtils;
import com.hwj.demo.component.id.generator.worker.dao.WorkerNodeDAO;
import com.hwj.demo.component.id.generator.worker.entity.WorkerNodeEntity;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * workId 分配（CachedUidGenerator的一个属性，在启动类的bean中设置）
 * 在分配给UidGenerator之后，workId将被丢弃
 */
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableWorkerIdAssigner.class);

    // @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入
    @Resource
    private WorkerNodeDAO workerNodeDAO;

    /**
     * 根据数据库分配workId，如果环境中有主机名和端口我们则认为是在Docker容器中运行，否则该节点是在实际机器中运行
     * @return assigned worker id
     */
    @Transactional
    public long assignWorkerId() {
        // 构建工作节点实体
        WorkerNodeEntity workerNodeEntity = buildWorkerNode();

        // 将工作节点保存到数据库
        workerNodeDAO.addWorkerNode(workerNodeEntity);
        LOGGER.info("Add worker node:" + workerNodeEntity);
        //返回工作节点ID
        return workerNodeEntity.getId();
    }

    /**
     * 根据IP和端口保存构建工作节点
     */
    private WorkerNodeEntity buildWorkerNode() {
        WorkerNodeEntity workerNodeEntity = new WorkerNodeEntity();
        // 如果是docker容器
        if (DockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkerNodeType.CONTAINER.value());
            workerNodeEntity.setHostName(DockerUtils.getDockerHost());
            workerNodeEntity.setPort(DockerUtils.getDockerPort());
        //实际机器
        } else {
            workerNodeEntity.setType(WorkerNodeType.ACTUAL.value());
            workerNodeEntity.setHostName(NetUtils.getLocalAddress());
            workerNodeEntity.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(1,100000));
        }

        return workerNodeEntity;
    }
}
