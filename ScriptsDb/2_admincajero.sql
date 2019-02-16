CREATE TABLE admincajero (
    id integer NOT NULL DEFAULT nextval('sequenceAdminCajero'),
    cantidad integer,
    denominacion integer,
    CONSTRAINT admincajero_pkey PRIMARY KEY (id)
);

 CREATE SEQUENCE sequenceAdminCajero
 INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

 
ALTER TABLE sequenceAdminCajero
  OWNER TO postgres;
GRANT ALL ON SEQUENCE  sequenceAdminCajero   TO postgres;
