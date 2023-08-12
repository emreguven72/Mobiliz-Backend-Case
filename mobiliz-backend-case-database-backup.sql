toc.dat                                                                                             0000600 0004000 0002000 00000035116 14465420736 0014460 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP           8                {            mobiliz-backend-casse    15.3    15.3 1    ,           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         -           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         .           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         /           1262    32951    mobiliz-backend-casse    DATABASE     �   CREATE DATABASE "mobiliz-backend-casse" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';
 '   DROP DATABASE "mobiliz-backend-casse";
                postgres    false         �            1259    33101 	   companies    TABLE     l   CREATE TABLE public.companies (
    company_id integer NOT NULL,
    company_name character varying(255)
);
    DROP TABLE public.companies;
       public         heap    postgres    false         �            1259    33100    companies_company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.companies_company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.companies_company_id_seq;
       public          postgres    false    215         0           0    0    companies_company_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.companies_company_id_seq OWNED BY public.companies.company_id;
          public          postgres    false    214         �            1259    33108    fleets    TABLE     �   CREATE TABLE public.fleets (
    fleet_id integer NOT NULL,
    name character varying(255),
    company_id integer,
    region_id integer
);
    DROP TABLE public.fleets;
       public         heap    postgres    false         �            1259    33107    fleets_fleet_id_seq    SEQUENCE     �   CREATE SEQUENCE public.fleets_fleet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.fleets_fleet_id_seq;
       public          postgres    false    217         1           0    0    fleets_fleet_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.fleets_fleet_id_seq OWNED BY public.fleets.fleet_id;
          public          postgres    false    216         �            1259    33115    groups    TABLE     �   CREATE TABLE public.groups (
    group_id integer NOT NULL,
    name character varying(255),
    company_id integer,
    fleet_id integer,
    parent_group_group_id integer
);
    DROP TABLE public.groups;
       public         heap    postgres    false         �            1259    33114    groups_group_id_seq    SEQUENCE     �   CREATE SEQUENCE public.groups_group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.groups_group_id_seq;
       public          postgres    false    219         2           0    0    groups_group_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.groups_group_id_seq OWNED BY public.groups.group_id;
          public          postgres    false    218         �            1259    33122    regions    TABLE     y   CREATE TABLE public.regions (
    region_id integer NOT NULL,
    name character varying(255),
    company_id integer
);
    DROP TABLE public.regions;
       public         heap    postgres    false         �            1259    33121    regions_region_id_seq    SEQUENCE     �   CREATE SEQUENCE public.regions_region_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.regions_region_id_seq;
       public          postgres    false    221         3           0    0    regions_region_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.regions_region_id_seq OWNED BY public.regions.region_id;
          public          postgres    false    220         �            1259    33129    vehicles    TABLE     o  CREATE TABLE public.vehicles (
    vehicle_id integer NOT NULL,
    brand character varying(255),
    chassis_number character varying(255),
    label character varying(255),
    model character varying(255),
    model_year integer,
    plate_number character varying(255),
    company_id integer,
    fleet_id integer,
    group_id integer,
    region_id integer
);
    DROP TABLE public.vehicles;
       public         heap    postgres    false         �            1259    33128    vehicles_vehicle_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vehicles_vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vehicles_vehicle_id_seq;
       public          postgres    false    223         4           0    0    vehicles_vehicle_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vehicles_vehicle_id_seq OWNED BY public.vehicles.vehicle_id;
          public          postgres    false    222         y           2604    33104    companies company_id    DEFAULT     |   ALTER TABLE ONLY public.companies ALTER COLUMN company_id SET DEFAULT nextval('public.companies_company_id_seq'::regclass);
 C   ALTER TABLE public.companies ALTER COLUMN company_id DROP DEFAULT;
       public          postgres    false    215    214    215         z           2604    33111    fleets fleet_id    DEFAULT     r   ALTER TABLE ONLY public.fleets ALTER COLUMN fleet_id SET DEFAULT nextval('public.fleets_fleet_id_seq'::regclass);
 >   ALTER TABLE public.fleets ALTER COLUMN fleet_id DROP DEFAULT;
       public          postgres    false    216    217    217         {           2604    33118    groups group_id    DEFAULT     r   ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);
 >   ALTER TABLE public.groups ALTER COLUMN group_id DROP DEFAULT;
       public          postgres    false    219    218    219         |           2604    33125    regions region_id    DEFAULT     v   ALTER TABLE ONLY public.regions ALTER COLUMN region_id SET DEFAULT nextval('public.regions_region_id_seq'::regclass);
 @   ALTER TABLE public.regions ALTER COLUMN region_id DROP DEFAULT;
       public          postgres    false    220    221    221         }           2604    33132    vehicles vehicle_id    DEFAULT     z   ALTER TABLE ONLY public.vehicles ALTER COLUMN vehicle_id SET DEFAULT nextval('public.vehicles_vehicle_id_seq'::regclass);
 B   ALTER TABLE public.vehicles ALTER COLUMN vehicle_id DROP DEFAULT;
       public          postgres    false    223    222    223         !          0    33101 	   companies 
   TABLE DATA           =   COPY public.companies (company_id, company_name) FROM stdin;
    public          postgres    false    215       3361.dat #          0    33108    fleets 
   TABLE DATA           G   COPY public.fleets (fleet_id, name, company_id, region_id) FROM stdin;
    public          postgres    false    217       3363.dat %          0    33115    groups 
   TABLE DATA           ]   COPY public.groups (group_id, name, company_id, fleet_id, parent_group_group_id) FROM stdin;
    public          postgres    false    219       3365.dat '          0    33122    regions 
   TABLE DATA           >   COPY public.regions (region_id, name, company_id) FROM stdin;
    public          postgres    false    221       3367.dat )          0    33129    vehicles 
   TABLE DATA           �   COPY public.vehicles (vehicle_id, brand, chassis_number, label, model, model_year, plate_number, company_id, fleet_id, group_id, region_id) FROM stdin;
    public          postgres    false    223       3369.dat 5           0    0    companies_company_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.companies_company_id_seq', 2, true);
          public          postgres    false    214         6           0    0    fleets_fleet_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.fleets_fleet_id_seq', 4, true);
          public          postgres    false    216         7           0    0    groups_group_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.groups_group_id_seq', 5, true);
          public          postgres    false    218         8           0    0    regions_region_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.regions_region_id_seq', 6, true);
          public          postgres    false    220         9           0    0    vehicles_vehicle_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.vehicles_vehicle_id_seq', 13, true);
          public          postgres    false    222                    2606    33106    companies companies_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (company_id);
 B   ALTER TABLE ONLY public.companies DROP CONSTRAINT companies_pkey;
       public            postgres    false    215         �           2606    33113    fleets fleets_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.fleets
    ADD CONSTRAINT fleets_pkey PRIMARY KEY (fleet_id);
 <   ALTER TABLE ONLY public.fleets DROP CONSTRAINT fleets_pkey;
       public            postgres    false    217         �           2606    33120    groups groups_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);
 <   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_pkey;
       public            postgres    false    219         �           2606    33127    regions regions_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (region_id);
 >   ALTER TABLE ONLY public.regions DROP CONSTRAINT regions_pkey;
       public            postgres    false    221         �           2606    33136    vehicles vehicles_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (vehicle_id);
 @   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT vehicles_pkey;
       public            postgres    false    223         �           2606    33142 "   fleets fkashsfq9a8afcsjnd2a12gmdna    FK CONSTRAINT     �   ALTER TABLE ONLY public.fleets
    ADD CONSTRAINT fkashsfq9a8afcsjnd2a12gmdna FOREIGN KEY (region_id) REFERENCES public.regions(region_id);
 L   ALTER TABLE ONLY public.fleets DROP CONSTRAINT fkashsfq9a8afcsjnd2a12gmdna;
       public          postgres    false    221    217    3205         �           2606    33152 "   groups fkcjjblpjkorq1nuolbsavfw42l    FK CONSTRAINT     �   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkcjjblpjkorq1nuolbsavfw42l FOREIGN KEY (fleet_id) REFERENCES public.fleets(fleet_id);
 L   ALTER TABLE ONLY public.groups DROP CONSTRAINT fkcjjblpjkorq1nuolbsavfw42l;
       public          postgres    false    3201    217    219         �           2606    33147 "   groups fkejvy31bprja3rd54iusumj9hy    FK CONSTRAINT     �   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkejvy31bprja3rd54iusumj9hy FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 L   ALTER TABLE ONLY public.groups DROP CONSTRAINT fkejvy31bprja3rd54iusumj9hy;
       public          postgres    false    3199    219    215         �           2606    33177 $   vehicles fkf1jcbsvsdrnpkxnj8psxd4r5f    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkf1jcbsvsdrnpkxnj8psxd4r5f FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
 N   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT fkf1jcbsvsdrnpkxnj8psxd4r5f;
       public          postgres    false    219    223    3203         �           2606    33182 $   vehicles fklgh4tkf559sqrugoo09af9txe    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fklgh4tkf559sqrugoo09af9txe FOREIGN KEY (region_id) REFERENCES public.regions(region_id);
 N   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT fklgh4tkf559sqrugoo09af9txe;
       public          postgres    false    221    223    3205         �           2606    33137 "   fleets fkmsh44xjme71yvkgbsfsk6dyp3    FK CONSTRAINT     �   ALTER TABLE ONLY public.fleets
    ADD CONSTRAINT fkmsh44xjme71yvkgbsfsk6dyp3 FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 L   ALTER TABLE ONLY public.fleets DROP CONSTRAINT fkmsh44xjme71yvkgbsfsk6dyp3;
       public          postgres    false    3199    217    215         �           2606    33172 $   vehicles fkpinycwbn3qfgcru9hw0wlymgx    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkpinycwbn3qfgcru9hw0wlymgx FOREIGN KEY (fleet_id) REFERENCES public.fleets(fleet_id);
 N   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT fkpinycwbn3qfgcru9hw0wlymgx;
       public          postgres    false    217    223    3201         �           2606    33157 "   groups fkrwlwolr1o5itg6qe3r5vygxro    FK CONSTRAINT     �   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkrwlwolr1o5itg6qe3r5vygxro FOREIGN KEY (parent_group_group_id) REFERENCES public.groups(group_id);
 L   ALTER TABLE ONLY public.groups DROP CONSTRAINT fkrwlwolr1o5itg6qe3r5vygxro;
       public          postgres    false    219    219    3203         �           2606    33162 #   regions fktjx8wf4a66o2hnlbadg7xohp8    FK CONSTRAINT     �   ALTER TABLE ONLY public.regions
    ADD CONSTRAINT fktjx8wf4a66o2hnlbadg7xohp8 FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 M   ALTER TABLE ONLY public.regions DROP CONSTRAINT fktjx8wf4a66o2hnlbadg7xohp8;
       public          postgres    false    215    3199    221         �           2606    33167 #   vehicles fkwvkljwybf9jpcarv5iclh6yo    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkwvkljwybf9jpcarv5iclh6yo FOREIGN KEY (company_id) REFERENCES public.companies(company_id);
 M   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT fkwvkljwybf9jpcarv5iclh6yo;
       public          postgres    false    3199    223    215                                                                                                                                                                                                                                                                                                                                                                                                                                                          3361.dat                                                                                            0000600 0004000 0002000 00000000041 14465420736 0014254 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Mulakat A.S.
2	Diger A.S.
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               3363.dat                                                                                            0000600 0004000 0002000 00000000120 14465420736 0014254 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Istanbul Filo	1	1
2	Edirne Filo	1	1
3	Istanbul Filo	2	4
4	Izmir Filo	1	3
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                3365.dat                                                                                            0000600 0004000 0002000 00000000156 14465420736 0014267 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Avrupa Grubu	1	1	\N
2	Asya Grubu	1	1	\N
4	Avrupa Kurye	2	3	\N
3	Avrupa Grubu	2	3	4
5	Asya Grubu	2	3	\N
\.


                                                                                                                                                                                                                                                                                                                                                                                                                  3367.dat                                                                                            0000600 0004000 0002000 00000000117 14465420736 0014266 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Marmara	1
2	Dogu Anadolu	1
3	Ege	1
4	Marmara	2
5	Ege	2
6	Dogu Anadolu	2
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                 3369.dat                                                                                            0000600 0004000 0002000 00000001057 14465420736 0014274 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Mercedes	XABA347AC	Istanbul Avrupa 1	Benz	2019	12ABC345	1	1	1	1
4	Toyota	WBATA123BA	Istanbul8	Corolla	2018	34ACZ123	1	2	\N	1
6	Toyota	WBASD123BA	Istanbul1	Corolla	2018	34MBZ123	2	3	5	4
8	Toyota	WBASW123BA	Istanbul5	Corolla	2018	34MBA123	1	1	\N	1
9	Toyota	WBASW123BA	Istanbul5	Corolla	2018	28BMO123	1	\N	\N	\N
10	Toyota	WBASW123BA	Istanbul5	Corolla	2018	34EMR123	1	1	2	1
7	Toyota	WBASW123BA	Istanbul5	Corolla	2018	34KRM123	1	4	\N	3
11	Toyota	WBATW123BA	Izmir5	Corolla	2018	35MST123	1	4	\N	3
13	Toyota	WBATN123BA	Istanbul3	Corolla	2018	34TYR123	2	3	\N	4
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 restore.sql                                                                                         0000600 0004000 0002000 00000027211 14465420736 0015402 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
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
-- Name: fleets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fleets (
    fleet_id integer NOT NULL,
    name character varying(255),
    company_id integer,
    region_id integer
);


ALTER TABLE public.fleets OWNER TO postgres;

--
-- Name: fleets_fleet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fleets_fleet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fleets_fleet_id_seq OWNER TO postgres;

--
-- Name: fleets_fleet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fleets_fleet_id_seq OWNED BY public.fleets.fleet_id;


--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    group_id integer NOT NULL,
    name character varying(255),
    company_id integer,
    fleet_id integer,
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
-- Name: regions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.regions (
    region_id integer NOT NULL,
    name character varying(255),
    company_id integer
);


ALTER TABLE public.regions OWNER TO postgres;

--
-- Name: regions_region_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.regions_region_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.regions_region_id_seq OWNER TO postgres;

--
-- Name: regions_region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.regions_region_id_seq OWNED BY public.regions.region_id;


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
    fleet_id integer,
    group_id integer,
    region_id integer
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
-- Name: companies company_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies ALTER COLUMN company_id SET DEFAULT nextval('public.companies_company_id_seq'::regclass);


--
-- Name: fleets fleet_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fleets ALTER COLUMN fleet_id SET DEFAULT nextval('public.fleets_fleet_id_seq'::regclass);


--
-- Name: groups group_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


--
-- Name: regions region_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regions ALTER COLUMN region_id SET DEFAULT nextval('public.regions_region_id_seq'::regclass);


--
-- Name: vehicles vehicle_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles ALTER COLUMN vehicle_id SET DEFAULT nextval('public.vehicles_vehicle_id_seq'::regclass);


--
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companies (company_id, company_name) FROM stdin;
\.
COPY public.companies (company_id, company_name) FROM '$$PATH$$/3361.dat';

--
-- Data for Name: fleets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fleets (fleet_id, name, company_id, region_id) FROM stdin;
\.
COPY public.fleets (fleet_id, name, company_id, region_id) FROM '$$PATH$$/3363.dat';

--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (group_id, name, company_id, fleet_id, parent_group_group_id) FROM stdin;
\.
COPY public.groups (group_id, name, company_id, fleet_id, parent_group_group_id) FROM '$$PATH$$/3365.dat';

--
-- Data for Name: regions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.regions (region_id, name, company_id) FROM stdin;
\.
COPY public.regions (region_id, name, company_id) FROM '$$PATH$$/3367.dat';

--
-- Data for Name: vehicles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vehicles (vehicle_id, brand, chassis_number, label, model, model_year, plate_number, company_id, fleet_id, group_id, region_id) FROM stdin;
\.
COPY public.vehicles (vehicle_id, brand, chassis_number, label, model, model_year, plate_number, company_id, fleet_id, group_id, region_id) FROM '$$PATH$$/3369.dat';

--
-- Name: companies_company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.companies_company_id_seq', 2, true);


--
-- Name: fleets_fleet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fleets_fleet_id_seq', 4, true);


--
-- Name: groups_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups_group_id_seq', 5, true);


--
-- Name: regions_region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.regions_region_id_seq', 6, true);


--
-- Name: vehicles_vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehicles_vehicle_id_seq', 13, true);


--
-- Name: companies companies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (company_id);


--
-- Name: fleets fleets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fleets
    ADD CONSTRAINT fleets_pkey PRIMARY KEY (fleet_id);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);


--
-- Name: regions regions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (region_id);


--
-- Name: vehicles vehicles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (vehicle_id);


--
-- Name: fleets fkashsfq9a8afcsjnd2a12gmdna; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fleets
    ADD CONSTRAINT fkashsfq9a8afcsjnd2a12gmdna FOREIGN KEY (region_id) REFERENCES public.regions(region_id);


--
-- Name: groups fkcjjblpjkorq1nuolbsavfw42l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkcjjblpjkorq1nuolbsavfw42l FOREIGN KEY (fleet_id) REFERENCES public.fleets(fleet_id);


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
-- Name: vehicles fklgh4tkf559sqrugoo09af9txe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fklgh4tkf559sqrugoo09af9txe FOREIGN KEY (region_id) REFERENCES public.regions(region_id);


--
-- Name: fleets fkmsh44xjme71yvkgbsfsk6dyp3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fleets
    ADD CONSTRAINT fkmsh44xjme71yvkgbsfsk6dyp3 FOREIGN KEY (company_id) REFERENCES public.companies(company_id);


--
-- Name: vehicles fkpinycwbn3qfgcru9hw0wlymgx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkpinycwbn3qfgcru9hw0wlymgx FOREIGN KEY (fleet_id) REFERENCES public.fleets(fleet_id);


--
-- Name: groups fkrwlwolr1o5itg6qe3r5vygxro; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fkrwlwolr1o5itg6qe3r5vygxro FOREIGN KEY (parent_group_group_id) REFERENCES public.groups(group_id);


--
-- Name: regions fktjx8wf4a66o2hnlbadg7xohp8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regions
    ADD CONSTRAINT fktjx8wf4a66o2hnlbadg7xohp8 FOREIGN KEY (company_id) REFERENCES public.companies(company_id);


--
-- Name: vehicles fkwvkljwybf9jpcarv5iclh6yo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT fkwvkljwybf9jpcarv5iclh6yo FOREIGN KEY (company_id) REFERENCES public.companies(company_id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       