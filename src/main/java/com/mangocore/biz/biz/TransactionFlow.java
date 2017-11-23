package com.mangocore.biz.biz;

/**
 * Created by notreami on 17/11/23.
 */

import com.mangocore.common.common.BaseInput;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;

public abstract class TransactionFlow<IN extends BaseInput, OUT> extends ProcessInstance implements InitializingBean {
        protected abstract List<ProcessInstance<IN, OUT>> getProcessorList();
        protected abstract Map<ProcessInstance<IN, OUT>,List<ProcessInstance<IN, OUT>>> getProcessorMap();


        }
