CREATE TABLE Regions
(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(50) NOT NULL,
    postal_codes       TEXT        NOT NULL,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP
);

CREATE TABLE Articles
(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(100) NOT NULL,
    category           VARCHAR(50)  NOT NULL,
    description        TEXT,
    region_id          SERIAL       NOT NULL REFERENCES Regions (id),
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP
);

CREATE TABLE Contracts
(
    id                 SERIAL PRIMARY KEY,
    customer_id        VARCHAR(50) NOT NULL,
    region_id          SERIAL      NOT NULL REFERENCES Regions (id),
    category           VARCHAR(50) NOT NULL,
    weekly_limit       INT         NOT NULL,
    start_date         DATE        NOT NULL,
    end_date           DATE,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP
);

CREATE TABLE Purchases
(
    article_id         SERIAL      NOT NULL REFERENCES Articles (id),
    contract_id        SERIAL      NOT NULL REFERENCES Contracts (id),
    category           VARCHAR(50) NOT NULL,
    price              DECIMAL     NOT NULL,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP,
    PRIMARY KEY (article_id, contract_id)
);
