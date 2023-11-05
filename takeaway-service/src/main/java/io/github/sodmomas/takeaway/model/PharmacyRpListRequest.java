package io.github.sodmomas.takeaway.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Data
public class PharmacyRpListRequest {
    @Schema(description = "页码")
    private Integer page;
    @Schema(description = "页容量")
    private Integer size;
}
