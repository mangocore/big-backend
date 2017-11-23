package com.mangocore.core.bpm.common;

import com.mangocore.common.common.BaseInput;
import com.mangocore.common.common.CommonInput;
import com.mangocore.common.common.CommonOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by notreami on 17/11/23.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessContext<IN extends BaseInput, OUT> implements BaseInput {
    private CommonInput<IN> commonInput;
    private CommonOutput<OUT> commonOutput = CommonOutput.FAILURE;
    private RichDataModelHolder richDataModelHolder;

    /**
     * 备注
     */
    private String description;


    public static <IN extends BaseInput, OUT> ProcessContext<IN, OUT> create(CommonInput<IN> commonInput, CommonOutput<OUT> commonOutput) {
        ProcessContext<IN, OUT> processContext = new ProcessContext<>();
        processContext.setCommonInput(commonInput);
        processContext.setCommonOutput(commonOutput);
        return processContext;
    }

    @Override
    public String getTraceId() {
        return commonInput.getData().getTraceId();
    }
}
