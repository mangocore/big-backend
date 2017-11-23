package com.mangocore.core.bpm.execute;

import java.util.concurrent.Future;

/**
 * Created by notreami on 17/11/23.
 */
public interface ProcessFuture<V> extends Future<V> {
    /**
     * 操作流水
     *
     * @return
     */
    default String getTraceId() {
        return "" + System.currentTimeMillis();
    }
}
