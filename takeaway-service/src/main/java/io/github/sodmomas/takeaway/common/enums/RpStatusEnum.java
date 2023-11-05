package io.github.sodmomas.takeaway.common.enums;

import lombok.Getter;

/**
 * 处方状态
 *
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Getter
public enum RpStatusEnum {
    WAIT_CHECK(0, "待审核"),
    //PATIENT_OK(10, "患者已确认"),
    //PHARMACY_OK(20, "药店已确认"),
    //PHARMACY_REJECT(30, "药店已驳回"),
    DOCTOR_REJECT(40, "医生已驳回"),
    DOCTOR_OK(50, "医生已确认"),
    CHECK_PHARMACIST_REJECT(60, "审核药师已驳回"),
    CHECK_PHARMACIST_OK(70, "审核药师已确认"),
    RECHECK_PHARMACIST_REJECT(80, "复核药师已驳回"),
    RECHECK_PHARMACIST_OK(90, "复核药师已确认"),
    ;

    private final int code;
    private final String desc;

    RpStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
