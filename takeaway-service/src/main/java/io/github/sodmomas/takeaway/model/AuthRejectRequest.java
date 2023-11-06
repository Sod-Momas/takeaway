package io.github.sodmomas.takeaway.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Sod-Momas
 * @since 2023/11/6
 */
@Data
public class AuthRejectRequest {
    @NotNull(message = "处方id为空")
    @Schema(description = "处方id")
    private Integer id;
    @NotBlank(message = "驳回原因为空")
    @Schema(description = "驳回原因")
    private String rejectReason;
}
