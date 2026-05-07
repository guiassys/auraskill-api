CREATE TABLE skills (
                        id BIGSERIAL PRIMARY KEY,

                        name VARCHAR(255) NOT NULL UNIQUE,

                        skill_type VARCHAR(50) NOT NULL,

                        created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                        updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_skills_name
    ON skills(name);