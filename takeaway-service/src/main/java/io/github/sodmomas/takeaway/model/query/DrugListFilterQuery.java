package io.github.sodmomas.takeaway.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Sod-Momas
 * @since 2023/11/4
 */
@Data
public class DrugListFilterQuery {
    @Schema(description = "页码")
    private Integer page;
    @Schema(description = "页容量")
    private Integer size;
    @Schema(description = "查询的类型")
    private String type;
    @Schema(description = "模糊搜索关键词")
    private String keyword;
}
