--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2021-02-11 18:28:53

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

DROP DATABASE cinema;
--
-- TOC entry 2856 (class 1262 OID 29338)
-- Name: cinema; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE cinema WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE cinema OWNER TO postgres;

\connect cinema

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
-- TOC entry 202 (class 1259 OID 29339)
-- Name: films; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.films (
    id bigint NOT NULL,
    title character varying NOT NULL,
    length time without time zone NOT NULL
);


ALTER TABLE public.films OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 29345)
-- Name: films_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.films_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.films_id_seq OWNER TO postgres;

--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 203
-- Name: films_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.films_id_seq OWNED BY public.films.id;


--
-- TOC entry 204 (class 1259 OID 29347)
-- Name: seances; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seances (
    id bigint NOT NULL,
    film_id bigint NOT NULL,
    session_start timestamp without time zone NOT NULL,
    ticket_price money NOT NULL
);


ALTER TABLE public.seances OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 29350)
-- Name: session_film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.session_film_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.session_film_id_seq OWNER TO postgres;

--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 205
-- Name: session_film_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.session_film_id_seq OWNED BY public.seances.film_id;


--
-- TOC entry 206 (class 1259 OID 29352)
-- Name: session_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.session_id_seq OWNER TO postgres;

--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 206
-- Name: session_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.session_id_seq OWNED BY public.seances.id;


--
-- TOC entry 207 (class 1259 OID 29354)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id bigint NOT NULL,
    session_id bigint NOT NULL
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 29357)
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_id_seq OWNER TO postgres;

--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 208
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;


--
-- TOC entry 209 (class 1259 OID 29359)
-- Name: tickets_session_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_session_id_seq OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 209
-- Name: tickets_session_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_session_id_seq OWNED BY public.tickets.session_id;


--
-- TOC entry 2704 (class 2604 OID 29361)
-- Name: films id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.films ALTER COLUMN id SET DEFAULT nextval('public.films_id_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 29362)
-- Name: seances id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances ALTER COLUMN id SET DEFAULT nextval('public.session_id_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 29363)
-- Name: seances film_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances ALTER COLUMN film_id SET DEFAULT nextval('public.session_film_id_seq'::regclass);


--
-- TOC entry 2707 (class 2604 OID 29364)
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 29365)
-- Name: tickets session_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN session_id SET DEFAULT nextval('public.tickets_session_id_seq'::regclass);


--
-- TOC entry 2843 (class 0 OID 29339)
-- Dependencies: 202
-- Data for Name: films; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.films VALUES (1, 'Terminator', '01:45:00');
INSERT INTO public.films VALUES (2, 'Matrix', '02:30:00');
INSERT INTO public.films VALUES (3, 'Kin-dza-dza', '02:15:00');
INSERT INTO public.films VALUES (4, 'Titanic', '03:30:00');
INSERT INTO public.films VALUES (5, 'Pulp fiction', '02:58:00');


--
-- TOC entry 2845 (class 0 OID 29347)
-- Dependencies: 204
-- Data for Name: seances; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.seances VALUES (1, 1, '2021-02-14 09:00:00', '250,00 ?');
INSERT INTO public.seances VALUES (2, 2, '2021-02-14 10:00:00', '300,00 ?');
INSERT INTO public.seances VALUES (3, 3, '2021-02-14 15:00:00', '180,00 ?');
INSERT INTO public.seances VALUES (4, 4, '2021-02-14 17:20:00', '350,00 ?');
INSERT INTO public.seances VALUES (5, 5, '2021-02-14 21:00:00', '350,00 ?');
INSERT INTO public.seances VALUES (6, 1, '2021-02-14 12:00:00', '240,00 ?');


--
-- TOC entry 2848 (class 0 OID 29354)
-- Dependencies: 207
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tickets VALUES (1, 1);
INSERT INTO public.tickets VALUES (2, 1);
INSERT INTO public.tickets VALUES (3, 2);
INSERT INTO public.tickets VALUES (4, 2);
INSERT INTO public.tickets VALUES (5, 2);
INSERT INTO public.tickets VALUES (6, 2);
INSERT INTO public.tickets VALUES (7, 2);
INSERT INTO public.tickets VALUES (8, 2);
INSERT INTO public.tickets VALUES (9, 2);
INSERT INTO public.tickets VALUES (10, 2);
INSERT INTO public.tickets VALUES (11, 3);
INSERT INTO public.tickets VALUES (12, 3);
INSERT INTO public.tickets VALUES (13, 3);
INSERT INTO public.tickets VALUES (14, 3);
INSERT INTO public.tickets VALUES (15, 3);
INSERT INTO public.tickets VALUES (16, 3);
INSERT INTO public.tickets VALUES (17, 3);
INSERT INTO public.tickets VALUES (18, 3);
INSERT INTO public.tickets VALUES (19, 3);
INSERT INTO public.tickets VALUES (20, 4);
INSERT INTO public.tickets VALUES (21, 4);
INSERT INTO public.tickets VALUES (22, 4);
INSERT INTO public.tickets VALUES (23, 4);
INSERT INTO public.tickets VALUES (24, 4);
INSERT INTO public.tickets VALUES (25, 4);
INSERT INTO public.tickets VALUES (26, 4);
INSERT INTO public.tickets VALUES (27, 4);
INSERT INTO public.tickets VALUES (28, 4);
INSERT INTO public.tickets VALUES (29, 4);
INSERT INTO public.tickets VALUES (30, 4);
INSERT INTO public.tickets VALUES (31, 4);
INSERT INTO public.tickets VALUES (32, 4);
INSERT INTO public.tickets VALUES (33, 5);
INSERT INTO public.tickets VALUES (34, 5);
INSERT INTO public.tickets VALUES (35, 5);
INSERT INTO public.tickets VALUES (36, 5);
INSERT INTO public.tickets VALUES (37, 5);
INSERT INTO public.tickets VALUES (38, 5);
INSERT INTO public.tickets VALUES (39, 5);
INSERT INTO public.tickets VALUES (40, 5);
INSERT INTO public.tickets VALUES (41, 5);
INSERT INTO public.tickets VALUES (42, 5);
INSERT INTO public.tickets VALUES (43, 5);
INSERT INTO public.tickets VALUES (44, 5);
INSERT INTO public.tickets VALUES (45, 5);
INSERT INTO public.tickets VALUES (46, 5);
INSERT INTO public.tickets VALUES (47, 5);
INSERT INTO public.tickets VALUES (48, 5);
INSERT INTO public.tickets VALUES (49, 5);
INSERT INTO public.tickets VALUES (50, 5);
INSERT INTO public.tickets VALUES (51, 5);
INSERT INTO public.tickets VALUES (52, 5);
INSERT INTO public.tickets VALUES (53, 5);
INSERT INTO public.tickets VALUES (54, 6);
INSERT INTO public.tickets VALUES (55, 6);
INSERT INTO public.tickets VALUES (56, 6);
INSERT INTO public.tickets VALUES (57, 6);
INSERT INTO public.tickets VALUES (58, 6);
INSERT INTO public.tickets VALUES (59, 6);
INSERT INTO public.tickets VALUES (60, 6);
INSERT INTO public.tickets VALUES (61, 6);
INSERT INTO public.tickets VALUES (62, 6);
INSERT INTO public.tickets VALUES (63, 6);
INSERT INTO public.tickets VALUES (64, 6);


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 203
-- Name: films_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.films_id_seq', 1, false);


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 205
-- Name: session_film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.session_film_id_seq', 1, false);


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 206
-- Name: session_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.session_id_seq', 1, false);


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 208
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_seq', 64, true);


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 209
-- Name: tickets_session_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_session_id_seq', 1, false);


--
-- TOC entry 2710 (class 2606 OID 29370)
-- Name: films films_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.films
    ADD CONSTRAINT films_pkey PRIMARY KEY (id);


--
-- TOC entry 2712 (class 2606 OID 29372)
-- Name: seances session_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances
    ADD CONSTRAINT session_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 29374)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 2715 (class 2606 OID 29375)
-- Name: seances film_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seances
    ADD CONSTRAINT film_id_fk FOREIGN KEY (film_id) REFERENCES public.films(id);


--
-- TOC entry 2716 (class 2606 OID 29380)
-- Name: tickets session_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT session_id_fk FOREIGN KEY (session_id) REFERENCES public.seances(id);


-- Completed on 2021-02-11 18:28:53

--
-- PostgreSQL database dump complete
--

