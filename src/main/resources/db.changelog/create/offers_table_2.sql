CREATE TABLE IF NOT EXISTS offers (
                                      id BIGINT PRIMARY KEY NOT NULL,
                                      name VARCHAR NOT NULL,
                                      description VARCHAR NOT NULL,
                                      creation_date DATE NOT NULL,
                                      status VARCHAR NOT NULL,
                                      banner VARCHAR
);