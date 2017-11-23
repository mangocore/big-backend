package com.mangocore.core.bpm.common;

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流程事件
 * Created by notreami on 17/11/22.
 */
public interface ProcessEvent<IN extends BaseInput, OUT> {
     Logger log = LoggerFactory.getLogger(ProcessEvent.class);
    /**
     * 起始事件
     */
    default void startEvent(CommonInput<IN> commonInput) {

    }

    /**
     * 中间事件
     */
    default void intermediateEvent(ProcessContext<IN, OUT> processContext) {

    }


    default void intermediateBoundaryEvent(ProcessContext<IN, OUT> processContext) {

    }

    default void subProcessEvent(ProcessContext<IN, OUT> processContext) {

    }

    /**
     * 异常事件
     */
    default void exceptionEvent(ProcessContext<IN, OUT> processContext, Exception e) {

    }

    /**
     * 结束事件
     */
    default void endEvent(ProcessContext<IN, OUT> processContext) {

    }
}
