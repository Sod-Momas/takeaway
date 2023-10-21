
DROP TABLE IF EXISTS drug;
DROP SEQUENCE IF EXISTS drug_id_sequence;

CREATE TABLE drug (
    id BIGINT PRIMARY KEY,
    approval_number VARCHAR(255),
    product_name VARCHAR(255),
    english_name VARCHAR(255),
    brand_name VARCHAR(255),
    dosage_form VARCHAR(255),
    specification VARCHAR(255),
    license_holder VARCHAR(255),
    license_holder_address VARCHAR(255),
    manufacturer VARCHAR(255),
    approval_date DATE,
    manufacturing_address VARCHAR(255),
    product_category VARCHAR(255),
    original_approval_number VARCHAR(255),
    drug_standard_code VARCHAR(255),
    drug_standard_code_remark VARCHAR(255)
);

CREATE SEQUENCE drug_id_sequence START 1;
ALTER TABLE drug ALTER COLUMN id SET DEFAULT nextval('drug_id_sequence');

ALTER TABLE IF EXISTS public."drug" OWNER to takeaway;
COMMENT ON TABLE drug IS '药品信息';

COMMENT ON COLUMN drug.approval_number IS '批准文号';
COMMENT ON COLUMN drug.product_name IS '产品名称';
COMMENT ON COLUMN drug.english_name IS '英文名称';
COMMENT ON COLUMN drug.brand_name IS '商品名';
COMMENT ON COLUMN drug.dosage_form IS '剂型';
COMMENT ON COLUMN drug.specification IS '规格';
COMMENT ON COLUMN drug.license_holder IS '上市许可持有人';
COMMENT ON COLUMN drug.license_holder_address IS '上市许可持有人地址';
COMMENT ON COLUMN drug.manufacturer IS '生产单位';
COMMENT ON COLUMN drug.approval_date IS '批准日期';
COMMENT ON COLUMN drug.manufacturing_address IS '生产地址';
COMMENT ON COLUMN drug.product_category IS '产品类别';
COMMENT ON COLUMN drug.original_approval_number IS '原批准文号';
COMMENT ON COLUMN drug.drug_standard_code IS '药品本位码';
COMMENT ON COLUMN drug.drug_standard_code_remark IS '药品本位码备注';
