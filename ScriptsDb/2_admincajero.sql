
 CREATE SEQUENCE sequenceAdminCajero
 INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE admincajero (
    id integer NOT NULL DEFAULT nextval('sequenceAdminCajero'),
    cantidad integer,
    denominacion integer,
    CONSTRAINT admincajero_pkey PRIMARY KEY (id)
);


ALTER TABLE sequenceAdminCajero
  OWNER TO machine;
GRANT ALL ON SEQUENCE  sequenceAdminCajero   TO machine;

grant all privileges on table admincajero to machine ;