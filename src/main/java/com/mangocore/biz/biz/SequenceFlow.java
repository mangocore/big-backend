package com.mangocore.biz.biz;

/**
 * Created by notreami on 17/11/22.
 */

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonOutput;
import com.mangocore.common.exception.CommonException;
import com.mangocore.core.bpm.common.ProcessContext;
import com.mangocore.core.bpm.execute.ProcessFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

@Slf4j
public abstract class SequenceFlow<IN extends BaseInput, OUT> extends ProcessInstance implements InitializingBean {

    protected abstract List<ProcessInstance<IN, OUT>> getProcessors();

    @Override
    protected CommonOutput doProcess(ProcessContext processContext) throws Exception {
        CommonOutput commonOutput = null;
        for (ProcessInstance<IN, OUT> processInstance : getProcessors()) {
            ProcessFuture<CommonOutput> future = executor.execute(processInstance, processContext.getCommonInput());
            try {
                commonOutput = future.get();
                if (!commonOutput.isSuccess()) {
                    log.error("Flow[{}] process fail, msg:{}", this.getClass().getSimpleName(), commonOutput.getErrorInfo());
                    return commonOutput;
                }
            } catch (Exception e) {
                throw new CommonException("Processor[" + processInstance.getClass().getSimpleName() + "]执行失败", e);
            }
        }

        return null;
    }
}
