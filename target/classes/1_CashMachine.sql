-- Database: "CashMachine"

-- DROP DATABASE "CashMachine";
create user machine;
alter user machine with  password 'machine123';

CREATE DATABASE "CashMachine"
  WITH OWNER = machine
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;

-- grant all privileges on database CashMachine to machine ;

      






