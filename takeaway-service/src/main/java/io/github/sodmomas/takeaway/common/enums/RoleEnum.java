package io.github.sodmomas.takeaway.common.enums;

/**
 * @author Sod-Momas
 * @since 2023/10/22
 */
public enum RoleEnum {
    PHARMACY(1, "药店"),
    DOCTOR(2, "医生"),
    PATIENT(3, "患者"),
    OPERATOR(4, "系统管理员"),
    ;

    /**
     * 角色编码
     */
    private final int code;
    /**
     * 角色描述
     */
    private final String desc;

    RoleEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
