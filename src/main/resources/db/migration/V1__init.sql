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
                                     alphaId BIGINT NOT NULL,
                                     name    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_bravo_alpha
    FOREIGN KEY (alphaId) REFERENCES alpha(id) ON DELETE CASCADE
    );

-- Charlie table (child of Bravo)
CREATE TABLE IF NOT EXISTS charlie (
                                       id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                       bravoId BIGINT NOT NULL,
                                       name    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_charlie_bravo
    FOREIGN KEY (bravoId) REFERENCES bravo(id) ON DELETE CASCADE
    );

-- Delta table (child of Bravo)
CREATE TABLE IF NOT EXISTS delta (
                                     id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                     bravoId BIGINT NOT NULL,
                                     name    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_delta_bravo
    FOREIGN KEY (bravoId) REFERENCES bravo(id) ON DELETE CASCADE
    );
