package io.github.sodmomas.takeaway.drug;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author Sod-Momas
 * @since 2023/7/2
 */
@TableName("drug")
public class DrugEntity {
    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 商品名
     */
    private String brandName;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 规格
     */
    private String specification;

    /**
     * 上市许可持有人
     */
    private String licenseHolder;

    /**
     * 上市许可持有人地址
     */
    private String licenseHolderAddress;

    /**
     * 生产单位
     */
    private String manufacturer;

    /**
     * 批准日期
     */
    private Date approvalDate;

    /**
     * 生产地址
     */
    private String manufacturingAddress;

    /**
     * 产品类别
     */
    private String productCategory;

    /**
     * 原批准文号
     */
    private String originalApprovalNumber;

    /**
     * 药品本位码
     */
    private String drugStandardCode;

    /**
     * 药品本位码备注
     */
    private String drugStandardCodeRemark;

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getLicenseHolder() {
        return licenseHolder;
    }

    public void setLicenseHolder(String licenseHolder) {
        this.licenseHolder = licenseHolder;
    }

    public String getLicenseHolderAddress() {
        return licenseHolderAddress;
    }

    public void setLicenseHolderAddress(String licenseHolderAddress) {
        this.licenseHolderAddress = licenseHolderAddress;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getManufacturingAddress() {
        return manufacturingAddress;
    }

    public void setManufacturingAddress(String manufacturingAddress) {
        this.manufacturingAddress = manufacturingAddress;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getOriginalApprovalNumber() {
        return originalApprovalNumber;
    }

    public void setOriginalApprovalNumber(String originalApprovalNumber) {
        this.originalApprovalNumber = originalApprovalNumber;
    }

    public String getDrugStandardCode() {
        return drugStandardCode;
    }

    public void setDrugStandardCode(String drugStandardCode) {
        this.drugStandardCode = drugStandardCode;
    }

    public String getDrugStandardCodeRemark() {
        return drugStandardCodeRemark;
    }

    public void setDrugStandardCodeRemark(String drugStandardCodeRemark) {
        this.drugStandardCodeRemark = drugStandardCodeRemark;
    }

    @Override
    public String toString() {
        return "DrugEntity{" +
                "approvalNumber='" + approvalNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", specification='" + specification + '\'' +
                ", licenseHolder='" + licenseHolder + '\'' +
                ", licenseHolderAddress='" + licenseHolderAddress + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", approvalDate=" + approvalDate +
                ", manufacturingAddress='" + manufacturingAddress + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", originalApprovalNumber='" + originalApprovalNumber + '\'' +
                ", drugStandardCode='" + drugStandardCode + '\'' +
                ", drugStandardCodeRemark='" + drugStandardCodeRemark + '\'' +
                '}';
    }
}
