CREATE TABLE account (
    account_id SERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    account_limit REAL NOT NULL,
    per_transaction_limit REAL NOT NULL,
    last_account_limit REAL,
    last_per_transaction_limit REAL,
    account_limit_update_time TIMESTAMP,
    per_transaction_limit_update_time TIMESTAMP
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE limit_offer (
    id SERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    limit_type VARCHAR(255),
    new_limit REAL NOT NULL,
    status VARCHAR(255),
    offer_activation_time TIMESTAMP,
    offer_expiry_time TIMESTAMP
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO postgres;
GRANT ALL PRIVILEGES ON SCHEMA public TO postgres;