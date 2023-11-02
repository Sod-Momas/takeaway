package io.github.sodmomas.takeaway.common.enums;

import lombok.Getter;

/**
 * @author Sod-Momas
 * @since 2023/11/2
 */
@Getter
public enum DrugPublishEnum {
    DOWN(0, "下架"),
    UP(1, "上架"),
    ;
    private final int code;
    private final String desc;

    DrugPublishEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
