package io.github.sodmomas.takeaway.model.vo;

import lombok.Data;

/**
 * @author Sod-Momas
 * @since 2023/11/4
 */
@Data
public class FilterVO {
    private Object label;
    private Object value;

    public static FilterVO of(Object label, Object value) {
        final FilterVO filterVO = new FilterVO();
        filterVO.setLabel(label);
        filterVO.setValue(value);
        return filterVO;
    }
}
