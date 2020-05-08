package com.hwj.demo.component.pagehelper;

import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface PageMapper {

    default <T> Page<T> doSelectPage(Pageable page, Supplier<List<T>> supplier) {
        com.github.pagehelper.Page<T> result = PageHelper.startPage(page.getPageNumber(), page.getPageSize()).doSelectPage(() -> supplier.get());
        return new PageImpl(result.getResult(), page, result.getTotal());
    }


    class PageImpl<T> implements Page<T> {
        private long total;
        private final List<T> content = new ArrayList<>();
        private final Pageable pageable;

        public PageImpl(List<T> content, Pageable pageable, long total) {

            this(content, pageable);
            this.total = total;
        }

        public PageImpl(List<T> content) {
            this(content, Pageable.unpaged(), null == content ? 0 : content.size());
        }

        public PageImpl(List<T> content, Pageable pageable) {

            Assert.notNull(content, "Content must not be null!");
            Assert.notNull(pageable, "Pageable must not be null!");

            this.content.addAll(content);
            this.pageable = pageable;
        }

        public int getNumber() {
            return pageable.isPaged() ? pageable.getPageNumber() : 0;
        }

        public int getSize() {
            return pageable.isPaged() ? pageable.getPageSize() : content.size();
        }

        public int getNumberOfElements() {
            return content.size();
        }

        public boolean hasPrevious() {
            return getNumber() > 0;
        }

        public boolean isFirst() {
            return !hasPrevious();
        }

        public boolean isLast() {
            return !hasNext();
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        public Pageable nextPageable() {
            return hasNext() ? pageable.next() : Pageable.unpaged();
        }

        public Pageable previousPageable() {
            return hasPrevious() ? pageable.previousOrFirst() : Pageable.unpaged();
        }

        public boolean hasContent() {
            return !content.isEmpty();
        }

        public List<T> getContent() {
            return Collections.unmodifiableList(content);
        }

        @Override
        public Sort getSort() {
            return pageable.getSort();
        }

        public Iterator<T> iterator() {
            return content.iterator();
        }


        protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {

            Assert.notNull(converter, "Function must not be null!");

            return this.stream().map(converter::apply).collect(Collectors.toList());
        }

        @Override
        public boolean equals(@Nullable Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof PageImpl<?>)) {
                return false;
            }

            PageImpl<?> that = (PageImpl<?>) obj;

            boolean contentEqual = this.content.equals(that.content);
            boolean pageableEqual = this.pageable.equals(that.pageable);

            return contentEqual && pageableEqual;
        }

        @Override
        public int hashCode() {

            int result = 17;

            result += 31 * pageable.hashCode();
            result += 31 * content.hashCode();

            return result;
        }

        @Override
        public int getTotalPages() {
            return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
        }

        @Override
        public long getTotalElements() {
            return total;
        }

        @Override
        public <U> Page<U> map(Function<? super T, ? extends U> converter) {
            return new PageImpl<>(getConvertedContent(converter), getPageable(), total);
        }
    }
}
