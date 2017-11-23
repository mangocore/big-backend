package com.mangocore.biz.biz;

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonInput;
import com.mangocore.common.common.CommonOutput;
import com.mangocore.biz.biz.bean.*;
import com.mangocore.core.bpm.common.ProcessContext;
import com.mangocore.core.bpm.common.ProcessDefinition;
import com.mangocore.core.bpm.common.ProcessEvent;
import com.mangocore.core.bpm.common.ProcessTypeIdentify;
import com.mangocore.core.bpm.execute.LogicExecutor;

/**
 * 流程实例
 * Created by notreami on 17/11/22.
 */
public abstract class ProcessInstance<IN extends BaseInput, OUT> implements ProcessDefinition, ProcessTypeIdentify, ProcessEvent {
    protected static LogicExecutor executor = new LogicExecutor<>();

    @Override
    public CommonOutput process(CommonInput commonInput) {
        startEvent(commonInput);
        ProcessContext<IN, OUT> processContext = null;
        try {
            CommonOutput commonOutput = checkInput(commonInput);
            if (!commonOutput.isSuccess()) {
                return commonOutput;
            }
            processContext = mergeContext(commonInput);
            if (!processContext.getCommonOutput().isSuccess()) {
                return commonOutput;
            }
            intermediateEvent(processContext);
            commonOutput = doProcess(processContext);
            processContext.setCommonOutput(commonOutput);
            return commonOutput;
        } catch (Exception e) {
            exceptionEvent(processContext != null ? processContext : ProcessContext.create(commonInput, null), e);
            return CommonOutput.FAILURE;
        } finally {
            endEvent(processContext);
        }
    }

    /**
     * 合并
     *
     * @param commonInput
     * @return
     */
    private ProcessContext<IN, OUT> mergeContext(CommonInput<IN> commonInput) {
        return null;
    }

    /**
     * 获取所需数据
     *
     * @param commonInput
     * @return
     */
    protected RichDataDefaultModel getRichDataModel(CommonInput<IN> commonInput) {
        return new RichDataDefaultModel();
    }

    protected abstract CommonOutput<OUT> doProcess(ProcessContext<IN, OUT> processContext) throws Exception;
}
