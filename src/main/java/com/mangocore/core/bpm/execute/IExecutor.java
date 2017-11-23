package com.mangocore.core.bpm.execute;

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonInput;
import com.mangocore.common.common.CommonOutput;
import com.mangocore.biz.biz.ProcessInstance;

/**
 * Created by notreami on 17/11/23.
 */
public interface IExecutor<IN extends BaseInput, OUT> {
    ProcessFuture<CommonOutput<OUT>> execute(ProcessInstance<IN, OUT> processInstance, CommonInput<IN> commonInput);
}
