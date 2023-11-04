
CREATE TABLE public.t_account
(
    id bigint,
    username character varying,
    nickname character varying,
    password character varying,
    email character varying,
    phone character varying,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.t_account
    OWNER to takeaway;

-- SEQUENCE: public.drug_id_sequence
-- DROP SEQUENCE IF EXISTS public.drug_id_sequence;

CREATE SEQUENCE IF NOT EXISTS public.drug_id_sequence
    INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

ALTER SEQUENCE public.drug_id_sequence OWNER TO takeaway;

-- Table: public.drug
-- DROP TABLE IF EXISTS public.drug;

CREATE TABLE IF NOT EXISTS public.drug
(
    id bigint NOT NULL DEFAULT nextval('drug_id_sequence'::regclass),
    approval_number character varying(255) ,
    product_name character varying(255) ,
    english_name character varying(255) ,
    brand_name character varying(255) ,
    dosage_form character varying(255) ,
    specification character varying(255) ,
    license_holder character varying(255) ,
    license_holder_address character varying(255) ,
    manufacturer character varying(255) ,
    approval_date date,
    manufacturing_address character varying(255) ,
    product_category character varying(255) ,
    original_approval_number character varying(255) ,
    drug_standard_code character varying(255) ,
    drug_standard_code_remark character varying(255) ,
    published integer NOT NULL DEFAULT 1,
    deleted integer NOT NULL DEFAULT 0,
    CONSTRAINT drug_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.drug OWNER to takeaway;

COMMENT ON TABLE public.drug IS '药品信息';
COMMENT ON COLUMN public.drug.approval_number IS '批准文号';
COMMENT ON COLUMN public.drug.product_name IS '产品名称';
COMMENT ON COLUMN public.drug.english_name IS '英文名称';
COMMENT ON COLUMN public.drug.brand_name IS '商品名';
COMMENT ON COLUMN public.drug.dosage_form IS '剂型';
COMMENT ON COLUMN public.drug.specification IS '规格';
COMMENT ON COLUMN public.drug.license_holder IS '上市许可持有人';
COMMENT ON COLUMN public.drug.license_holder_address IS '上市许可持有人地址';
COMMENT ON COLUMN public.drug.manufacturer IS '生产单位';
COMMENT ON COLUMN public.drug.approval_date IS '批准日期';
COMMENT ON COLUMN public.drug.manufacturing_address IS '生产地址';
COMMENT ON COLUMN public.drug.product_category IS '产品类别';
COMMENT ON COLUMN public.drug.original_approval_number IS '原批准文号';
COMMENT ON COLUMN public.drug.drug_standard_code IS '药品本位码';
COMMENT ON COLUMN public.drug.drug_standard_code_remark IS '药品本位码备注';
COMMENT ON COLUMN public.drug.published IS '是否发布, 1-上架, 0-下架';
COMMENT ON COLUMN public.drug.deleted IS '是否被删除, 1-已被删除, 0-未被删除';