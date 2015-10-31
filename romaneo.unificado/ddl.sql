--
-- TOC entry 172 (class 1259 OID 422983)
-- Name: acondicionador; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE acondicionador (
    id integer NOT NULL,
    apellido character varying(50) NOT NULL,
    direccion character varying(150) NOT NULL,
    dni integer NOT NULL,
    email character varying(70),
    nombre character varying(50),
    telefono character varying(30),
    id_localidad integer NOT NULL
);


ALTER TABLE acondicionador OWNER TO romanito_usr;

--
-- TOC entry 173 (class 1259 OID 422986)
-- Name: acondicionador_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE acondicionador_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE acondicionador_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 173
-- Name: acondicionador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE acondicionador_id_seq OWNED BY acondicionador.id;


--
-- TOC entry 174 (class 1259 OID 422988)
-- Name: contacto; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE contacto (
    id bigint NOT NULL,
    email character varying(255),
    telefono character varying(255)
);


ALTER TABLE contacto OWNER TO romanito_usr;

--
-- TOC entry 175 (class 1259 OID 422994)
-- Name: contacto_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE contacto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacto_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 175
-- Name: contacto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE contacto_id_seq OWNED BY contacto.id;


--
-- TOC entry 176 (class 1259 OID 422996)
-- Name: contratista; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE contratista (
    id integer NOT NULL,
    nombre character varying(50)
);


ALTER TABLE contratista OWNER TO romanito_usr;

--
-- TOC entry 177 (class 1259 OID 422999)
-- Name: contratista_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE contratista_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contratista_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 177
-- Name: contratista_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE contratista_id_seq OWNED BY contratista.id;


--
-- TOC entry 178 (class 1259 OID 423001)
-- Name: establecimiento; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE establecimiento (
    id integer NOT NULL,
    nombre character varying(255),
    productor_id integer
);


ALTER TABLE establecimiento OWNER TO romanito_usr;

--
-- TOC entry 179 (class 1259 OID 423004)
-- Name: establecimiento_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE establecimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE establecimiento_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 179
-- Name: establecimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE establecimiento_id_seq OWNED BY establecimiento.id;


--
-- TOC entry 180 (class 1259 OID 423006)
-- Name: localidad; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE localidad (
    id integer NOT NULL,
    cod_postal character varying(255),
    nombre_localidad character varying(255),
    id_partido integer
);


ALTER TABLE localidad OWNER TO romanito_usr;

--
-- TOC entry 181 (class 1259 OID 423012)
-- Name: localidad_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE localidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE localidad_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 181
-- Name: localidad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE localidad_id_seq OWNED BY localidad.id;


--
-- TOC entry 182 (class 1259 OID 423014)
-- Name: logging; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE logging (
    id integer NOT NULL,
    event character varying(255),
    id_object integer NOT NULL,
    log_datetime timestamp without time zone NOT NULL,
    name_object character varying(255) NOT NULL,
    observations text,
    state_from character varying(255) NOT NULL,
    state_to character varying(255) NOT NULL,
    type_object character varying(255) NOT NULL,
    user_id integer
);


ALTER TABLE logging OWNER TO romanito_usr;

--
-- TOC entry 183 (class 1259 OID 423020)
-- Name: logging_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE logging_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE logging_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 183
-- Name: logging_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE logging_id_seq OWNED BY logging.id;


--
-- TOC entry 184 (class 1259 OID 423022)
-- Name: message_types; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE message_types (
    id integer NOT NULL,
    data_class character varying(150) NOT NULL,
    name character varying(45) NOT NULL,
    priority integer,
    procedure character varying(255),
    retries integer NOT NULL,
    sychronised character varying(1) NOT NULL,
    timeout integer NOT NULL
);


ALTER TABLE message_types OWNER TO romanito_usr;

--
-- TOC entry 185 (class 1259 OID 423025)
-- Name: message_types_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE message_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE message_types_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 185
-- Name: message_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE message_types_id_seq OWNED BY message_types.id;


--
-- TOC entry 186 (class 1259 OID 423027)
-- Name: messages; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE messages (
    id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    data character varying(60),
    processed timestamp without time zone,
    cod_message integer NOT NULL
);


ALTER TABLE messages OWNER TO romanito_usr;

--
-- TOC entry 187 (class 1259 OID 423030)
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE messages_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 187
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE messages_id_seq OWNED BY messages.id;


--
-- TOC entry 188 (class 1259 OID 423032)
-- Name: partido; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE partido (
    id integer NOT NULL,
    nombre character varying(255),
    provincia integer
);


ALTER TABLE partido OWNER TO romanito_usr;

--
-- TOC entry 189 (class 1259 OID 423035)
-- Name: partido_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE partido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE partido_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 189
-- Name: partido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE partido_id_seq OWNED BY partido.id;


--
-- TOC entry 190 (class 1259 OID 423037)
-- Name: productor; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE productor (
    id integer NOT NULL,
    nombre character varying(255)
);


ALTER TABLE productor OWNER TO romanito_usr;

--
-- TOC entry 191 (class 1259 OID 423040)
-- Name: productor_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE productor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE productor_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 191
-- Name: productor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE productor_id_seq OWNED BY productor.id;


--
-- TOC entry 192 (class 1259 OID 423042)
-- Name: provincia; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE provincia (
    id integer NOT NULL,
    provincia character varying(255)
);


ALTER TABLE provincia OWNER TO romanito_usr;

--
-- TOC entry 193 (class 1259 OID 423045)
-- Name: provincia_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE provincia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE provincia_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 193
-- Name: provincia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE provincia_id_seq OWNED BY provincia.id;


--
-- TOC entry 194 (class 1259 OID 423047)
-- Name: rol; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE rol (
    id integer NOT NULL,
    esta_activo character(1) NOT NULL,
    creado_por integer NOT NULL,
    descripcion character varying(255),
    fecha_creacion timestamp without time zone NOT NULL,
    nombre character varying(60) NOT NULL
);


ALTER TABLE rol OWNER TO romanito_usr;

--
-- TOC entry 195 (class 1259 OID 423050)
-- Name: rol_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rol_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 195
-- Name: rol_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE rol_id_seq OWNED BY rol.id;


--
-- TOC entry 196 (class 1259 OID 423052)
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE ubicacion (
    id bigint NOT NULL
);


ALTER TABLE ubicacion OWNER TO romanito_usr;

--
-- TOC entry 197 (class 1259 OID 423055)
-- Name: ubicacion_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE ubicacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ubicacion_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 197
-- Name: ubicacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE ubicacion_id_seq OWNED BY ubicacion.id;


--
-- TOC entry 198 (class 1259 OID 423057)
-- Name: usuario; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE usuario (
    id integer NOT NULL,
    acceso_sistema character(1) NOT NULL,
    esta_activo character(1) NOT NULL,
    creado_por integer NOT NULL,
    descripcion character varying(255),
    email character varying(60),
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone NOT NULL,
    fecha_nacimiento timestamp without time zone,
    modificado_por integer NOT NULL,
    nombre character varying(60) NOT NULL,
    password character varying(40),
    telefono character varying(40)
);


ALTER TABLE usuario OWNER TO romanito_usr;

--
-- TOC entry 199 (class 1259 OID 423060)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: romanito_usr
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_seq OWNER TO romanito_usr;

--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romanito_usr
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 200 (class 1259 OID 423062)
-- Name: usuario_rol; Type: TABLE; Schema: public; Owner: romanito_usr
--

CREATE TABLE usuario_rol (
    id_rol integer NOT NULL,
    id_usuario integer NOT NULL,
    esta_activo character(1) NOT NULL,
    creado_por integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL
);


ALTER TABLE usuario_rol OWNER TO romanito_usr;

--
-- TOC entry 1966 (class 2604 OID 423065)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY acondicionador ALTER COLUMN id SET DEFAULT nextval('acondicionador_id_seq'::regclass);


--
-- TOC entry 1967 (class 2604 OID 423066)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY contacto ALTER COLUMN id SET DEFAULT nextval('contacto_id_seq'::regclass);


--
-- TOC entry 1968 (class 2604 OID 423067)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY contratista ALTER COLUMN id SET DEFAULT nextval('contratista_id_seq'::regclass);


--
-- TOC entry 1969 (class 2604 OID 423068)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY establecimiento ALTER COLUMN id SET DEFAULT nextval('establecimiento_id_seq'::regclass);


--
-- TOC entry 1970 (class 2604 OID 423069)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY localidad ALTER COLUMN id SET DEFAULT nextval('localidad_id_seq'::regclass);


--
-- TOC entry 1971 (class 2604 OID 423070)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY logging ALTER COLUMN id SET DEFAULT nextval('logging_id_seq'::regclass);


--
-- TOC entry 1972 (class 2604 OID 423071)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY message_types ALTER COLUMN id SET DEFAULT nextval('message_types_id_seq'::regclass);


--
-- TOC entry 1973 (class 2604 OID 423072)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY messages ALTER COLUMN id SET DEFAULT nextval('messages_id_seq'::regclass);


--
-- TOC entry 1974 (class 2604 OID 423073)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY partido ALTER COLUMN id SET DEFAULT nextval('partido_id_seq'::regclass);


--
-- TOC entry 1975 (class 2604 OID 423074)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY productor ALTER COLUMN id SET DEFAULT nextval('productor_id_seq'::regclass);


--
-- TOC entry 1976 (class 2604 OID 423075)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY provincia ALTER COLUMN id SET DEFAULT nextval('provincia_id_seq'::regclass);


--
-- TOC entry 1977 (class 2604 OID 423076)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY rol ALTER COLUMN id SET DEFAULT nextval('rol_id_seq'::regclass);


--
-- TOC entry 1978 (class 2604 OID 423077)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY ubicacion ALTER COLUMN id SET DEFAULT nextval('ubicacion_id_seq'::regclass);


--
-- TOC entry 1979 (class 2604 OID 423078)
-- Name: id; Type: DEFAULT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 2126 (class 0 OID 422983)
-- Dependencies: 172
-- Data for Name: acondicionador; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 173
-- Name: acondicionador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('acondicionador_id_seq', 1, false);


--
-- TOC entry 2128 (class 0 OID 422988)
-- Dependencies: 174
-- Data for Name: contacto; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 175
-- Name: contacto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('contacto_id_seq', 1, false);


--
-- TOC entry 2130 (class 0 OID 422996)
-- Dependencies: 176
-- Data for Name: contratista; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 177
-- Name: contratista_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('contratista_id_seq', 1, false);


--
-- TOC entry 2132 (class 0 OID 423001)
-- Dependencies: 178
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 179
-- Name: establecimiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('establecimiento_id_seq', 1, false);


--
-- TOC entry 2134 (class 0 OID 423006)
-- Dependencies: 180
-- Data for Name: localidad; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 181
-- Name: localidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('localidad_id_seq', 1, false);


--
-- TOC entry 2136 (class 0 OID 423014)
-- Dependencies: 182
-- Data for Name: logging; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 183
-- Name: logging_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('logging_id_seq', 1, false);


--
-- TOC entry 2138 (class 0 OID 423022)
-- Dependencies: 184
-- Data for Name: message_types; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 185
-- Name: message_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('message_types_id_seq', 1, false);


--
-- TOC entry 2140 (class 0 OID 423027)
-- Dependencies: 186
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 187
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('messages_id_seq', 1, false);


--
-- TOC entry 2142 (class 0 OID 423032)
-- Dependencies: 188
-- Data for Name: partido; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 189
-- Name: partido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('partido_id_seq', 1, false);


--
-- TOC entry 2144 (class 0 OID 423037)
-- Dependencies: 190
-- Data for Name: productor; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 191
-- Name: productor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('productor_id_seq', 1, false);


--
-- TOC entry 2146 (class 0 OID 423042)
-- Dependencies: 192
-- Data for Name: provincia; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 193
-- Name: provincia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('provincia_id_seq', 1, false);


--
-- TOC entry 2148 (class 0 OID 423047)
-- Dependencies: 194
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 195
-- Name: rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('rol_id_seq', 1, false);


--
-- TOC entry 2150 (class 0 OID 423052)
-- Dependencies: 196
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 197
-- Name: ubicacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('ubicacion_id_seq', 1, false);


--
-- TOC entry 2152 (class 0 OID 423057)
-- Dependencies: 198
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 199
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romanito_usr
--

SELECT pg_catalog.setval('usuario_id_seq', 1, false);


--
-- TOC entry 2154 (class 0 OID 423062)
-- Dependencies: 200
-- Data for Name: usuario_rol; Type: TABLE DATA; Schema: public; Owner: romanito_usr
--



--
-- TOC entry 1981 (class 2606 OID 423080)
-- Name: acondicionador_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY acondicionador
    ADD CONSTRAINT acondicionador_pkey PRIMARY KEY (id);


--
-- TOC entry 1983 (class 2606 OID 423082)
-- Name: contacto_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY contacto
    ADD CONSTRAINT contacto_pkey PRIMARY KEY (id);


--
-- TOC entry 1985 (class 2606 OID 423084)
-- Name: contratista_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY contratista
    ADD CONSTRAINT contratista_pkey PRIMARY KEY (id);


--
-- TOC entry 1987 (class 2606 OID 423086)
-- Name: establecimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_pkey PRIMARY KEY (id);


--
-- TOC entry 1989 (class 2606 OID 423088)
-- Name: localidad_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY localidad
    ADD CONSTRAINT localidad_pkey PRIMARY KEY (id);


--
-- TOC entry 1991 (class 2606 OID 423090)
-- Name: logging_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY logging
    ADD CONSTRAINT logging_pkey PRIMARY KEY (id);


--
-- TOC entry 1993 (class 2606 OID 423092)
-- Name: message_types_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY message_types
    ADD CONSTRAINT message_types_pkey PRIMARY KEY (id);


--
-- TOC entry 1995 (class 2606 OID 423094)
-- Name: messages_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- TOC entry 1997 (class 2606 OID 423096)
-- Name: partido_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY partido
    ADD CONSTRAINT partido_pkey PRIMARY KEY (id);


--
-- TOC entry 1999 (class 2606 OID 423098)
-- Name: productor_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY productor
    ADD CONSTRAINT productor_pkey PRIMARY KEY (id);


--
-- TOC entry 2001 (class 2606 OID 423100)
-- Name: provincia_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY (id);


--
-- TOC entry 2003 (class 2606 OID 423102)
-- Name: rol_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id);


--
-- TOC entry 2005 (class 2606 OID 423104)
-- Name: ubicacion_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY ubicacion
    ADD CONSTRAINT ubicacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2007 (class 2606 OID 423106)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2009 (class 2606 OID 423108)
-- Name: usuario_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (id_rol, id_usuario);


--
-- TOC entry 2011 (class 2606 OID 423109)
-- Name: fk13e328468d1b7ca9; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT fk13e328468d1b7ca9 FOREIGN KEY (productor_id) REFERENCES productor(id);


--
-- TOC entry 2015 (class 2606 OID 423114)
-- Name: fk3118953ea720f5e9; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY usuario_rol
    ADD CONSTRAINT fk3118953ea720f5e9 FOREIGN KEY (id_rol) REFERENCES rol(id);


--
-- TOC entry 2016 (class 2606 OID 423119)
-- Name: fk3118953eecd74b27; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY usuario_rol
    ADD CONSTRAINT fk3118953eecd74b27 FOREIGN KEY (id_usuario) REFERENCES usuario(id);


--
-- TOC entry 2012 (class 2606 OID 423124)
-- Name: fkb83370699e26f88d; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY localidad
    ADD CONSTRAINT fkb83370699e26f88d FOREIGN KEY (id_partido) REFERENCES partido(id);


--
-- TOC entry 2014 (class 2606 OID 423129)
-- Name: fkd0bcc9e14d363389; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY partido
    ADD CONSTRAINT fkd0bcc9e14d363389 FOREIGN KEY (provincia) REFERENCES provincia(id);


--
-- TOC entry 2013 (class 2606 OID 423134)
-- Name: fke475014cbcea4490; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT fke475014cbcea4490 FOREIGN KEY (cod_message) REFERENCES message_types(id);


--
-- TOC entry 2010 (class 2606 OID 423139)
-- Name: fkea6f51f74e3edd; Type: FK CONSTRAINT; Schema: public; Owner: romanito_usr
--

ALTER TABLE ONLY acondicionador
    ADD CONSTRAINT fkea6f51f74e3edd FOREIGN KEY (id_localidad) REFERENCES localidad(id);


-- Completed on 2015-10-31 12:29:14

--
-- PostgreSQL database dump complete
--

