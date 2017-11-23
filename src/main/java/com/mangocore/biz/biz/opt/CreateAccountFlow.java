package com.mangocore.biz.biz.opt;

import com.mangocore.biz.biz.ProcessInstance;
import com.mangocore.biz.biz.SequenceFlow;
import com.mangocore.common.enums.ProcessType;
import com.mangocore.core.bpm.annotation.Processor;

import java.util.List;

/**
 * Created by notreami on 17/11/23.
 */
@Processor
public class CreateAccountFlow extends SequenceFlow {
    @Override
    public ProcessType getProcessType() {
        return ProcessType.CREATE_ACCOUNT;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    protected List<ProcessInstance> getProcessors() {
        return null;
    }
}
