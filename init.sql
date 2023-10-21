
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