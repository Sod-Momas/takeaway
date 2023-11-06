package io.github.sodmomas.takeaway.model;

import lombok.Data;

import java.util.List;

/**
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Data
public class DoctorRpDetail {
    private Integer id;
    private Integer pharmacyId;
    private Integer patientId;
    private Integer doctorId;
    private Integer checkPharmacistId;
    private Integer recheckPharmacistId;
    private Integer status;
    private String rejectReason;
    private String doctorAdvice;
    private List<Item> itemList;

    @Data
    public static class Item {
        private Integer id;
        private Integer rpId;
        private String drugName;
        private String drugType;
        private Integer drugCount;
    }
}
