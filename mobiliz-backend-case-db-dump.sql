toc.dat                                                                                             0000600 0004000 0002000 00000033217 14465552347 0014464 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP           (                {            mobiliz-backend-casse    15.3    15.3 .    )           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         *           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         +           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         ,           1262    32951    mobiliz-backend-casse    DATABASE     �   CREATE DATABASE "mobiliz-backend-casse" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';
 '   DROP DATABASE "mobiliz-backend-casse";
                postgres    false         �            1259    33308    authorizations    TABLE     y   CREATE TABLE public.authorizations (
    authorization_id integer NOT NULL,
    group_id integer,
    user_id integer
);
 "   DROP TABLE public.authorizations;
       public         heap    postgres    false         �            1259    33307 #   authorizations_authorization_id_seq    SEQUENCE     �   CREATE SEQUENCE public.authorizations_authorization_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.authorizations_authorization_id_seq;
       public          postgres    false    215         -           0    0 #   authorizations_authorization_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.authorizations_authorization_id_seq OWNED BY public.authorizations.authorization_id;
          public          postgres    false    214         �            1259    33315 	   companies    TABLE     l   CREATE TABLE public.companies (
    company_id integer NOT NULL,
    company_name character varying(255)
);
    DROP TABLE public.companies;
       public         heap    postgres    false         �            1259    33314    companies_company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.companies_company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.companies_company_id_seq;
       public          postgres    false    217         .           0    0    companies_company_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.companies_company_id_seq OWNED BY public.companies.company_id;
          public          postgres    false    216         �            1259    33322    groups    TABLE     �   CREATE TABLE public.groups (
    group_id integer NOT NULL,
    name character varying(255),
    company_id integer,
    parent_group_group_id integer
);
    DROP TABLE public.groups;
       public         heap    postgres    false         �            1259    33321    groups_group_id_seq    SEQUENCE     �   CREATE SEQUENCE public.groups_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.groups_group_id_seq;
       public          postgres    false    219         /           0    0    groups_group_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;
          public          postgres    false    218         �            1259    33329    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    company_name character varying(255),
    name character varying(255),
    role character varying(255),
    surname character varying(255),
    company_id integer
);
    DROP TABLE public.users;
       public         heap    postgres    false         �            1259    33328    users_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_user_id_seq;
       public          postgres    false    221         0           0    0    users_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;
          public          postgres    false    220         �            1259    33338    vehicles    TABLE     B  CREATE TABLE public.vehicles (
    vehicle_id integer NOT NULL,
    brand character varying(255),
    chassis_number character varying(255),
    label character varying(255),
    model character varying(255),
    model_year integer,
    plate_number character varying(255),
    company_id integer,
    group_id integer
);
    DROP TABLE public.vehicles;
       public         heap    postgres    false         �            1259    33337    vehicles_vehicle_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vehicles_vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vehicles_vehicle_id_seq;
       public          postgres    false    223         1           0    0    vehicles_vehicle_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vehicles_vehicle_id_seq OWNED BY public.vehicles.vehicle_id;
          public          postgres    false    222         y           2604    33311    authorizations authorization_id    DEFAULT     �   ALTER TABLE ONLY public.authorizations ALTER COLUMN authorization_id SET DEFAULT nextval('public.authorizations_authorization_id_seq'::regclass);
 N   ALTER TABLE public.authorizations ALTER COLUMN authorization_id DROP DEFAULT;
       public          postgres    false    214    215    215         z           2604    33318    companies company_id    DEFAULT     |   ALTER TABLE ONLY public.companies ALTER COLUMN company_id SET DEFAULT nextval('public.companies_company_id_seq'::regclass);
 C   ALTER TABLE public.companies ALTER COLUMN company_id DROP DEFAULT;
       public          postgres    false    216    217    217         {           2604    33325    groups group_id    DEFAULT     r   ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);
 >   ALTER TABLE public.groups ALTER COLUMN group_id DROP DEFAULT;
       public          postgres    false    219    218    219         |           2604    33332    users user_id    DEFAULT     n   ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);
 <   ALTER TABLE public.users ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    220    221    221         }           2604    33341    vehicles vehicle_id    DEFAULT     z   ALTER TABLE ONLY public.vehicles ALTER COLUMN vehicle_id SET DEFAULT nextval('public.vehicles_vehicle_id_seq'::regclass);
 B   ALTER TABLE public.vehicles ALTER COLUMN vehicle_id DROP DEFAULT;
       public          postgres    false    222    223    223                   0    33308    authorizations 
   TABLE DATA           M   COPY public.authorizations (authorization_id, group_id, user_id) FROM stdin;
    public          postgres    false    215       3358.dat            0    33315 	   companies 
   TABLE DATA           =   COPY public.companies (company_id, company_name) FROM stdin;
    public          postgres    false    217       3360.dat "          0    33322    groups 
   TABLE DATA           S   COPY public.groups (group_id, name, company_id, parent_group_group_id) FROM stdin;
    public          postgres    false    219       3362.dat $          0    33329    users 
   TABLE DATA           W   COPY public.users (user_id, company_name, name, role, surname, company_id) FROM stdin;
    public          postgres    false    221       3364.dat &          0    33338    vehicles 
   TABLE DATA           �   COPY public.vehicles (vehicle_id, brand, chassis_number, label, model, model_year, plate_number, company_id, group_id) FROM stdin;
    public          postgres    false    223       3366.dat 2           0    0 #   authorizations_authorization_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.authorizations_authorization_id_seq', 5, true);
          public          postgres    false    214         3           0    0    companies_company_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.companies_company_id_seq', 1, true);
          public          postgres    false    216         4           0    0    groups_group_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.groups_group_id_seq', 4, true);
          public          postgres    false    218         5           0    0    users_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);
          public          postgres    false    220         6           0    0    vehicles_vehicle_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.vehicles_vehicle_id_seq', 6, true);
          public          postgres    false    222                    2606    33313 "   authorizations authorizations_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.authorizations
    ADD CONSTRAINT authorizations_pkey PRIMARY KEY (authorization_id);
 L   ALTER TABLE ONLY public.authorizations DROP CONSTRAINT authorizations_pkey;
       public            postgres    false    215         �           2606    33320    companies companies_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (company_id);
 B   ALTER TABLE ONLY public.companies DROP CONSTRAINT companies_pkey;
       public            postgres    false    217         �           2606    33327    groups groups_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);
 <   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_pkey;
       public            postgres    false    219         �           2606    33336    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    221         �           2606    33345    vehicles vehicles_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (vehicle_id);
 @   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT vehicles_pkey;
       public            postgres    false    223         �           2606    33356 "   groups fkejvy31bprja3rd54iusumj9hy    FK CONSTRAINT     �   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkejvy31bprja3rd54iusumj9hy FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 L   ALTER TABLE ONLY public.groups DROP CONSTRAINT fkejvy31bprja3rd54iusumj9hy;
       public          postgres    false    219    217    3201         �           2606    33376 $   vehicles fkf1jcbsvsdrnpkxnj8psxd4r5f    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkf1jcbsvsdrnpkxnj8psxd4r5f FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
 N   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT fkf1jcbsvsdrnpkxnj8psxd4r5f;
       public          postgres    false    219    223    3203         �           2606    33366 !   users fkin8gn4o1hpiwe6qe4ey7ykwq7    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkin8gn4o1hpiwe6qe4ey7ykwq7 FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkin8gn4o1hpiwe6qe4ey7ykwq7;
       public          postgres    false    3201    221    217         �           2606    33351 *   authorizations fkmikhd5jkryqpsjnxb9eo6m3cx    FK CONSTRAINT     �   ALTER TABLE ONLY public.authorizations
    ADD CONSTRAINT fkmikhd5jkryqpsjnxb9eo6m3cx FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 T   ALTER TABLE ONLY public.authorizations DROP CONSTRAINT fkmikhd5jkryqpsjnxb9eo6m3cx;
       public          postgres    false    215    3205    221         �           2606    33346 *   authorizations fkn9iqxqeb5yxotix26bdc5526r    FK CONSTRAINT     �   ALTER TABLE ONLY public.authorizations
    ADD CONSTRAINT fkn9iqxqeb5yxotix26bdc5526r FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
 T   ALTER TABLE ONLY public.authorizations DROP CONSTRAINT fkn9iqxqeb5yxotix26bdc5526r;
       public          postgres    false    219    215    3203         �           2606    33361 "   groups fkrwlwolr1o5itg6qe3r5vygxro    FK CONSTRAINT     �   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkrwlwolr1o5itg6qe3r5vygxro FOREIGN KEY (parent_group_group_id) REFERENCES public.groups(group_id);
 L   ALTER TABLE ONLY public.groups DROP CONSTRAINT fkrwlwolr1o5itg6qe3r5vygxro;
       public          postgres    false    219    3203    219         �           2606    33371 #   vehicles fkwvkljwybf9jpcarv5iclh6yo    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkwvkljwybf9jpcarv5iclh6yo FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 M   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT fkwvkljwybf9jpcarv5iclh6yo;
       public          postgres    false    3201    223    217                                                                                                                                                                                                                                                                                                                                                                                         3358.dat                                                                                            0000600 0004000 0002000 00000000021 14465552347 0014264 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        3	3	2
5	2	2
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               3360.dat                                                                                            0000600 0004000 0002000 00000000024 14465552347 0014260 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Mulakat A.S.
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            3362.dat                                                                                            0000600 0004000 0002000 00000000117 14465552347 0014265 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Istanbul Filo	1	\N
2	Avrupa Grubu	1	1
3	Asya Grubu	1	1
4	Asya Kurye	1	3
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                 3364.dat                                                                                            0000600 0004000 0002000 00000000116 14465552347 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Mulakat A.S.	Huseyin	Admin	Ates	1
2	Mulakat A.S.	Emre	Standart	Guven	1
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                  3366.dat                                                                                            0000600 0004000 0002000 00000000416 14465552347 0014273 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Toyota	ABCD123	Arac	Corolla	2019	01BE030	1	2
2	Audi	ABCD323	Arac	A8	2020	07AB023	1	1
3	Audi	ADASDJK1	Arac	A5	2021	27ABO53	1	2
4	Toyota	ASDkjalsd	Arac	Model	2002	52OC020	1	3
5	Audi	asdASDASd	Arac	A9	2023	25CH332	1	4
6	Toyota	ASDkjalsd	Arac	Model	2002	46EFG033	1	3
\.


                                                                                                                                                                                                                                                  restore.sql                                                                                         0000600 0004000 0002000 00000026166 14465552347 0015416 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "mobiliz-backend-casse";
--
-- Name: mobiliz-backend-casse; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "mobiliz-backend-casse" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';


ALTER DATABASE "mobiliz-backend-casse" OWNER TO postgres;

\connect -reuse-previous=on "dbname='mobiliz-backend-casse'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authorizations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorizations (
    authorization_id integer NOT NULL,
    group_id integer,
    user_id integer
);


ALTER TABLE public.authorizations OWNER TO postgres;

--
-- Name: authorizations_authorization_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authorizations_authorization_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authorizations_authorization_id_seq OWNER TO postgres;

--
-- Name: authorizations_authorization_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.authorizations_authorization_id_seq OWNED BY public.authorizations.authorization_id;


--
-- Name: companies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companies (
    company_id integer NOT NULL,
    company_name character varying(255)
);


ALTER TABLE public.companies OWNER TO postgres;

--
-- Name: companies_company_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.companies_company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.companies_company_id_seq OWNER TO postgres;

--
-- Name: companies_company_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.companies_company_id_seq OWNED BY public.companies.company_id;


--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    group_id integer NOT NULL,
    name character varying(255),
    company_id integer,
    parent_group_group_id integer
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- Name: groups_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.groups_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_group_id_seq OWNER TO postgres;

--
-- Name: groups_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    company_name character varying(255),
    name character varying(255),
    role character varying(255),
    surname character varying(255),
    company_id integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: vehicles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehicles (
    vehicle_id integer NOT NULL,
    brand character varying(255),
    chassis_number character varying(255),
    label character varying(255),
    model character varying(255),
    model_year integer,
    plate_number character varying(255),
    company_id integer,
    group_id integer
);


ALTER TABLE public.vehicles OWNER TO postgres;

--
-- Name: vehicles_vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicles_vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicles_vehicle_id_seq OWNER TO postgres;

--
-- Name: vehicles_vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vehicles_vehicle_id_seq OWNED BY public.vehicles.vehicle_id;


--
-- Name: authorizations authorization_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorizations ALTER COLUMN authorization_id SET DEFAULT nextval('public.authorizations_authorization_id_seq'::regclass);


--
-- Name: companies company_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies ALTER COLUMN company_id SET DEFAULT nextval('public.companies_company_id_seq'::regclass);


--
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Name: vehicles vehicle_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles ALTER COLUMN vehicle_id SET DEFAULT nextval('public.vehicles_vehicle_id_seq'::regclass);


--
-- Data for Name: authorizations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authorizations (authorization_id, group_id, user_id) FROM stdin;
\.
COPY public.authorizations (authorization_id, group_id, user_id) FROM '$$PATH$$/3358.dat';

--
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companies (company_id, company_name) FROM stdin;
\.
COPY public.companies (company_id, company_name) FROM '$$PATH$$/3360.dat';

--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, name, company_id, parent_group_group_id) FROM stdin;
\.
COPY public.groups (group_id, name, company_id, parent_group_group_id) FROM '$$PATH$$/3362.dat';

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, company_name, name, role, surname, company_id) FROM stdin;
\.
COPY public.users (user_id, company_name, name, role, surname, company_id) FROM '$$PATH$$/3364.dat';

--
-- Data for Name: vehicles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vehicles (vehicle_id, brand, chassis_number, label, model, model_year, plate_number, company_id, group_id) FROM stdin;
\.
COPY public.vehicles (vehicle_id, brand, chassis_number, label, model, model_year, plate_number, company_id, group_id) FROM '$$PATH$$/3366.dat';

--
-- Name: authorizations_authorization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authorizations_authorization_id_seq', 5, true);


--
-- Name: companies_company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.companies_company_id_seq', 1, true);


--
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_group_id_seq', 4, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);


--
-- Name: vehicles_vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehicles_vehicle_id_seq', 6, true);


--
-- Name: authorizations authorizations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorizations
    ADD CONSTRAINT authorizations_pkey PRIMARY KEY (authorization_id);


--
-- Name: companies companies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (company_id);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: vehicles vehicles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (vehicle_id);


--
-- Name: groups fkejvy31bprja3rd54iusumj9hy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkejvy31bprja3rd54iusumj9hy FOREIGN KEY (company_id) REFERENCES public.companies(company_id);


--
-- Name: vehicles fkf1jcbsvsdrnpkxnj8psxd4r5f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkf1jcbsvsdrnpkxnj8psxd4r5f FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- Name: users fkin8gn4o1hpiwe6qe4ey7ykwq7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkin8gn4o1hpiwe6qe4ey7ykwq7 FOREIGN KEY (company_id) REFERENCES public.companies(company_id);


--
-- Name: authorizations fkmikhd5jkryqpsjnxb9eo6m3cx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorizations
    ADD CONSTRAINT fkmikhd5jkryqpsjnxb9eo6m3cx FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: authorizations fkn9iqxqeb5yxotix26bdc5526r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorizations
    ADD CONSTRAINT fkn9iqxqeb5yxotix26bdc5526r FOREIGN KEY (group_id) REFERENCES public.groups(group_id);


--
-- Name: groups fkrwlwolr1o5itg6qe3r5vygxro; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkrwlwolr1o5itg6qe3r5vygxro FOREIGN KEY (parent_group_group_id) REFERENCES public.groups(group_id);


--
-- Name: vehicles fkwvkljwybf9jpcarv5iclh6yo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkwvkljwybf9jpcarv5iclh6yo FOREIGN KEY (company_id) REFERENCES public.companies(company_id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          