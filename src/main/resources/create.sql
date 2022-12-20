CREATE TABLE IF NOT EXISTS Patients (
                                        id bigint NOT NULL,
                                        name VARCHAR(255),
    contactNumber VARCHAR(20),
    address VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (id))


CREATE TABLE IF NOT EXISTS Physicians (
                                          id bigint NOT NULL,
                                          name VARCHAR(255),
    contactNumber VARCHAR(20),
    email VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (id))


CREATE TABLE IF NOT EXISTS Appointments (
                                            id bigint NOT NULL,
                                            date Date,
                                            contactNumber VARCHAR(20),
    diagnosis VARCHAR(400),
    patient_id bigint NOT NULL,
    physician_id bigint NOT NULL,
    PRIMARY KEY (id))

ALTER TABLE Appointments
    ADD CONSTRAINT fk_appointments_physician FOREIGN KEY (
                                                          physician_id) REFERENCES Physicians (
                                                                                               id);

ALTER TABLE Appointments
    ADD CONSTRAINT fk_appointments_patients FOREIGN KEY (patient_id) REFERENCES Patients (id);

COMMIT;
