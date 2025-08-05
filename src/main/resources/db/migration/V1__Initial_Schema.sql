CREATE TYPE payment_status AS ENUM ('VERIFYING', 'SUCCEEDED', 'FAILED');

CREATE TABLE payment (
    id VARCHAR(255) PRIMARY KEY,
    verification_status payment_status NOT NULL,
    amount INTEGER,
    psp_type VARCHAR(50),
    psp_payment_id VARCHAR(255),
    creation_instant TIMESTAMP WITH TIME ZONE,
    last_psp_verification_instant TIMESTAMP WITH TIME ZONE,
    verification_attempt_nb INTEGER,
    payer_email VARCHAR(255)
);

CREATE TABLE donor (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE beneficiary (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE donation (
    id SERIAL PRIMARY KEY,
    date DATE,
    payment_id VARCHAR(255) UNIQUE NOT NULL REFERENCES payment(id) ON DELETE CASCADE,
    donor_full_name VARCHAR(255),
    donor_email VARCHAR(255)
);

CREATE TABLE help (
    id SERIAL PRIMARY KEY,
    date DATE,
    payment_id VARCHAR(255) UNIQUE NOT NULL REFERENCES payment(id) ON DELETE CASCADE,
    beneficiary_full_name VARCHAR(255),
    beneficiary_email VARCHAR(255),
    description TEXT
);
