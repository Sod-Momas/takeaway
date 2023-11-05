

-- SEQUENCE: public.takeaway_seq
-- DROP SEQUENCE IF EXISTS public.takeaway_seq;
CREATE SEQUENCE IF NOT EXISTS public.takeaway_seq
    INCREMENT 1 START 10 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
ALTER SEQUENCE public.takeaway_seq OWNER TO takeaway;

-- Table: public.t_account
-- DROP TABLE IF EXISTS public.t_account;
CREATE TABLE IF NOT EXISTS public.t_account
(
    id integer NOT NULL DEFAULT nextval('takeaway_seq'::regclass),
    username character varying COLLATE pg_catalog."default",
    nickname character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    phone character varying COLLATE pg_catalog."default",
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT t_account_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.t_account OWNER to takeaway;

INSERT INTO public.t_account ( id, username, nickname, password, email, phone, create_time, update_time, enabled) VALUES
	(1, 'sod', 'Sod-Momas', MD5('123456'), 'sod@qq.com', '17600000000', NOW()	, NOW(), true);

-- Table: public.t_rel_account_role
-- DROP TABLE IF EXISTS public.t_rel_account_role;
CREATE TABLE IF NOT EXISTS public.t_rel_account_role
(
    id integer NOT NULL DEFAULT nextval('takeaway_seq'::regclass),
    account_id integer,
    role_id integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT rar_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.t_rel_account_role OWNER to takeaway;
COMMENT ON TABLE public.t_rel_account_role IS '账号角色信息表';
COMMENT ON COLUMN public.t_rel_account_role.account_id IS '账号id';
COMMENT ON COLUMN public.t_rel_account_role.role_id IS '角色：1-患者，2-药店，3-医师药师，4-系统管理员';
COMMENT ON COLUMN public.t_rel_account_role.create_time IS '创建时间';
COMMENT ON COLUMN public.t_rel_account_role.update_time IS '更新时间';

INSERT INTO public.t_rel_account_role( id, account_id, role_id, create_time, update_time) VALUES
 (1,1,1,NOW(),NOW())
,(2,1,2,NOW(),NOW())
,(3,1,3,NOW(),NOW())
,(4,1,4,NOW(),NOW())
;

-- Table: public.t_drug
-- DROP TABLE IF EXISTS public.t_drug;
CREATE TABLE IF NOT EXISTS public.t_drug
(
    id integer NOT NULL DEFAULT nextval('takeaway_seq'::regclass),
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
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT drug_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.drug OWNER to takeaway;

COMMENT ON TABLE public.t_drug IS '药品信息';
COMMENT ON COLUMN public.t_drug.approval_number IS '批准文号';
COMMENT ON COLUMN public.t_drug.product_name IS '产品名称';
COMMENT ON COLUMN public.t_drug.english_name IS '英文名称';
COMMENT ON COLUMN public.t_drug.brand_name IS '商品名';
COMMENT ON COLUMN public.t_drug.dosage_form IS '剂型';
COMMENT ON COLUMN public.t_drug.specification IS '规格';
COMMENT ON COLUMN public.t_drug.license_holder IS '上市许可持有人';
COMMENT ON COLUMN public.t_drug.license_holder_address IS '上市许可持有人地址';
COMMENT ON COLUMN public.t_drug.manufacturer IS '生产单位';
COMMENT ON COLUMN public.t_drug.approval_date IS '批准日期';
COMMENT ON COLUMN public.t_drug.manufacturing_address IS '生产地址';
COMMENT ON COLUMN public.t_drug.product_category IS '产品类别';
COMMENT ON COLUMN public.t_drug.original_approval_number IS '原批准文号';
COMMENT ON COLUMN public.t_drug.drug_standard_code IS '药品本位码';
COMMENT ON COLUMN public.t_drug.drug_standard_code_remark IS '药品本位码备注';
COMMENT ON COLUMN public.t_drug.published IS '是否发布, 1-上架, 0-下架';
COMMENT ON COLUMN public.t_drug.deleted IS '是否被删除, 1-已被删除, 0-未被删除';
COMMENT ON COLUMN public.t_drug.create_time IS '创建时间';
COMMENT ON COLUMN public.t_drug.update_time IS '更新时间';


-- Table: public.t_rp
-- DROP TABLE IF EXISTS public.t_rp;
CREATE TABLE IF NOT EXISTS public.t_rp
(
    id integer NOT NULL DEFAULT nextval('takeaway_seq'::regclass),
    pharmacy_id integer,
    patient_id integer,
    doctor_id integer,
    check_pharmacist_id integer,
    recheck_pharmacist_id integer,
    status integer,
    reject_reason character varying(255) COLLATE pg_catalog."default",
    doctor_advice character varying(500) COLLATE pg_catalog."default",
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT rp_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.t_rp OWNER to takeaway;
COMMENT ON TABLE public.t_rp IS '处方表';
COMMENT ON COLUMN public.t_rp.pharmacy_id IS '药店用户id';
COMMENT ON COLUMN public.t_rp.patient_id IS '患者用户id';
COMMENT ON COLUMN public.t_rp.doctor_id IS '医师用户id';
COMMENT ON COLUMN public.t_rp.check_pharmacist_id IS '审核药师用户id';
COMMENT ON COLUMN public.t_rp.recheck_pharmacist_id IS '复核药师用户id';
COMMENT ON COLUMN public.t_rp.status IS '审核状态';
COMMENT ON COLUMN public.t_rp.reject_reason IS '驳回原因';
COMMENT ON COLUMN public.t_rp.doctor_advice IS '医嘱';

