DROP TABLE doctor_specialties IF EXISTS;
DROP TABLE doctors IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE diseases IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE users IF EXISTS;


CREATE TABLE doctors (
                      id         INTEGER IDENTITY PRIMARY KEY,
                      first_name VARCHAR(30),
                      last_name  VARCHAR(30)
);
CREATE INDEX doctor_last_name ON doctors (last_name);

CREATE TABLE specialties (
                             id   INTEGER IDENTITY PRIMARY KEY,
                             name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE doctor_specialties (
                                 doctor_id       INTEGER NOT NULL,
                                 specialty_id INTEGER NOT NULL
);
ALTER TABLE doctor_specialties ADD CONSTRAINT fk_doctor_specialties_vets FOREIGN KEY (doctor_id) REFERENCES doctors (id);
ALTER TABLE doctor_specialties ADD CONSTRAINT fk_doctor_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
                       id   INTEGER IDENTITY PRIMARY KEY,
                       name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE users (
                        id         INTEGER IDENTITY PRIMARY KEY,
                        first_name VARCHAR(30),
                        last_name  VARCHAR_IGNORECASE(30),
                        address    VARCHAR(255),
                        city       VARCHAR(80),
                        telephone  VARCHAR(20)
);
CREATE INDEX users_last_name ON users (last_name);

CREATE TABLE diseases (
                      id         INTEGER IDENTITY PRIMARY KEY,
                      name       VARCHAR(30),
                      birth_date DATE,
                      type_id    INTEGER NOT NULL,
                      user_id   INTEGER NOT NULL
);
ALTER TABLE diseases ADD CONSTRAINT fk_diseases_owners FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE diseases ADD CONSTRAINT fk_diseases_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX diseases_name ON diseases (name);

CREATE TABLE visits (
                        id          INTEGER IDENTITY PRIMARY KEY,
                        disease_id      INTEGER NOT NULL,
                        visit_date  DATE,
                        description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (disease_id) REFERENCES diseases (id);
CREATE INDEX visits_disease_id ON visits (disease_id);
