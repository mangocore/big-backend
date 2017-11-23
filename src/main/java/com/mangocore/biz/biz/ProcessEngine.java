package com.mangocore.biz.biz;

import com.google.common.collect.ImmutableMap;
import com.mangocore.core.bpm.annotation.Processor;
import com.mangocore.biz.config.ProcessConfig;
import com.mangocore.common.common.CommonInput;
import com.mangocore.common.common.CommonOutput;
import com.mangocore.common.common.ErrorInfo;
import com.mangocore.common.enums.ProcessType;
import com.mangocore.common.util.JsonBinder;
import com.mangocore.core.bpm.common.ProcessDefinition;
import com.mangocore.core.bpm.common.ProcessTypeIdentify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程引擎
 * Created by notreami on 17/11/22.
 */
@Slf4j
@Processor
public class ProcessEngine implements ProcessDefinition, InitializingBean {
    @Autowired
    private List<ProcessInstance> processInstanceList;

    private ImmutableMap<ProcessType, ProcessInstance> processInstanceListMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        ImmutableMap.Builder<ProcessType, ProcessInstance> builder = ImmutableMap.builder();
        builder.putAll(processInstanceList.stream().collect(Collectors.toMap(ProcessTypeIdentify::getProcessType, v -> v)));
        processInstanceListMap = builder.build();
    }

    /**
     * 校验输入数据
     *
     * @param commonInput
     * @return
     */
    @Override
    public CommonOutput checkInput(CommonInput commonInput) {
        if (ProcessConfig.SERVICE_MAINTAINING) {
            log.info("系统维护中 --> ProcessType={},commonInput={}", commonInput.getProcessType(), JsonBinder.toJSONString(commonInput));
            return CommonOutput.createError(ErrorInfo.create(ErrorInfo.ErrorCode.PROCESS_ERROR, "系统维护中,请稍后在试!"));
        }

        if (commonInput.getData() == null) {
            log.error("流程操作参数错误 --> ProcessType={},commonInput={}", commonInput.getProcessType(), JsonBinder.toJSONString(commonInput));
            return CommonOutput.createError(ErrorInfo.create(ErrorInfo.ErrorCode.PROCESS_ERROR, "流程操作参数错误"));
        }
        return CommonOutput.OK;
    }

    /**
     * 流程
     *
     * @param commonInput
     * @return
     */
    @Override
    public CommonOutput process(CommonInput commonInput) {
        CommonOutput commonOutput = checkInput(commonInput);
        if (!commonOutput.isSuccess()) {
            return commonOutput;
        }

        ProcessInstance processInstance = processInstanceListMap.get(commonInput.getProcessType());
        if (processInstance == null) {
            log.error("不支持的流程 --> ProcessType={},commonInput={}", commonInput.getProcessType(), JsonBinder.toJSONString(commonInput));
            return CommonOutput.createError(ErrorInfo.create(ErrorInfo.ErrorCode.PROCESS_ERROR, "不支持的流程"));
        }
        return processInstance.process(commonInput);
    }
}
