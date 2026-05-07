CREATE TABLE certifications (
                                id BIGSERIAL PRIMARY KEY,

                                professional_id BIGINT NOT NULL,

                                certification_name VARCHAR(255) NOT NULL,

                                institution VARCHAR(255),

                                issue_date DATE,

                                description TEXT,

                                created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                                updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                                CONSTRAINT fk_certifications_professional
                                    FOREIGN KEY (professional_id)
                                        REFERENCES professionals(id)
                                        ON DELETE CASCADE
);

CREATE INDEX idx_certifications_professional
    ON certifications(professional_id);