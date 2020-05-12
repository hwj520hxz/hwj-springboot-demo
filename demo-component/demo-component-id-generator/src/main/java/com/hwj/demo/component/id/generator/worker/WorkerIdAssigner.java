
package com.hwj.demo.component.id.generator.worker;

/**
 * 工作节点ID分配
 * @author hwj
 */
public interface WorkerIdAssigner {

    /**
     * 分配工作节点ID
     * @return assigned worker id
     */
    long assignWorkerId();

}
