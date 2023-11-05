package io.github.sodmomas.takeaway.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 添加处方
 *
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Schema(description = "添加处方请求")
@Data
public class AddRpRequest {
    @Schema(description = "药店id")
    private Integer pharmacyId;
    @NotNull(message = "患者id为空")
    @Schema(description = "患者id")
    private Integer patientId;
    @Schema(description = "医嘱")
    private String doctorAdvice;
    @NotEmpty(message = "处方明细为空")
    @Schema(description = "处方明细")
    private List<Item> itemList;

    @Data
    @Schema(description = "添加处方请求的明细")
    public static class Item {
        @NotEmpty(message = "药品名为空")
        private String drugName;
        @NotEmpty(message = "药品类型为空")
        private String drugType;
        @NotNull(message = "药品数量为空")
        private Integer drugCount;
    }
}
