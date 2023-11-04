package io.github.sodmomas.takeaway.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Sod-Momas
 * @since 2023/11/4
 */
@Data
@Schema(title = "药品列表查询")
public class DrugListQuery {
    @Schema(description = "页码")
    private Integer page;
    @Schema(description = "页容量")
    private Integer size;
    @Schema(description = "id")
    private List<Integer> id;
    @Schema(description = "生产单位")
    private String manufacturer;
    @Schema(description = "产品名称")
    private String productName;
    @Schema(description = "是否发布, 1-上架, 0-下架")
    private Integer published;
}
