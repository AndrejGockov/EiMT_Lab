CREATE TABLE country
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    continent VARCHAR(100) NOT NULL
);

CREATE TABLE host
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    name       VARCHAR(100) NOT NULL,
    surname    VARCHAR(100) NOT NULL,
    country_id BIGINT       NOT NULL,
    CONSTRAINT fk_host_country FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE accommodation
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    name       VARCHAR(255) NOT NULL,
    category   VARCHAR(50)  NOT NULL, -- ROOM, HOUSE, FLAT, APARTMENT, HOTEL, MOTEL
    condition  VARCHAR(20)  NOT NULL, -- GOOD, BAD
    rented     BOOLEAN      NOT NULL DEFAULT FALSE,
    num_rooms  INTEGER      NOT NULL,
    host_id    BIGINT       NOT NULL,
    CONSTRAINT fk_accommodation_host FOREIGN KEY (host_id) REFERENCES host (id)
);