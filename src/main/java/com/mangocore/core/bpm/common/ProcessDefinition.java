package com.mangocore.core.bpm.common;

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonInput;
import com.mangocore.common.common.CommonOutput;

/**
 * 流程定义
 * Created by notreami on 17/11/22.
 */
public interface ProcessDefinition<IN extends BaseInput, OUT> {

    /**
     * 校验输入数据
     *
     * @param commonInput
     * @return
     */
    default CommonOutput<OUT> checkInput(CommonInput<IN> commonInput){
        return CommonOutput.OK;
    }

    /**
     * 流程
     *
     * @param commonInput
     * @return
     */
    CommonOutput<OUT> process(CommonInput<IN> commonInput);

    /**
     * 是否异步启动
     *
     * @return
     */
    default boolean isAsync() {
        return false;
    }
}