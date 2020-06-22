DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users(
	user_id serial PRIMARY KEY UNIQUE,
	name varchar(25) NOT NULL,
	hash_pass integer NOT NULL
);