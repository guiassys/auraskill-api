CREATE TABLE achievements (
                              id BIGSERIAL PRIMARY KEY,

                              professional_id BIGINT NOT NULL,

                              achievement_name VARCHAR(255) NOT NULL,

                              achievement_description TEXT,

                              achievement_date DATE,

                              organizer VARCHAR(255),

                              created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                              updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                              CONSTRAINT fk_achievements_professional
                                  FOREIGN KEY (professional_id)
                                      REFERENCES professionals(id)
                                      ON DELETE CASCADE
);

CREATE INDEX idx_achievements_professional
    ON achievements(professional_id);