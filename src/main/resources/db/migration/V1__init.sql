-- language: sql
-- file: 'src/main/resources/db/migration/V1__init.sql'

-- Alpha table
CREATE TABLE IF NOT EXISTS alpha (
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Bravo table (child of Alpha via idColumn = "alphaId")
CREATE TABLE IF NOT EXISTS bravo (
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    alpha_id BIGINT NOT NULL,
    name    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_bravo_alpha
    FOREIGN KEY (alpha_id) REFERENCES alpha(id) ON DELETE CASCADE
);

-- Charlie table (child of Bravo)
CREATE TABLE IF NOT EXISTS charlie (
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bravo_id BIGINT NOT NULL,
    name    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_charlie_bravo
    FOREIGN KEY (bravo_id) REFERENCES bravo(id) ON DELETE CASCADE
);

-- Delta table (child of Bravo)
CREATE TABLE IF NOT EXISTS delta (
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bravo_id BIGINT NOT NULL,
    name    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_delta_bravo
    FOREIGN KEY (bravo_id) REFERENCES bravo(id) ON DELETE CASCADE
);
