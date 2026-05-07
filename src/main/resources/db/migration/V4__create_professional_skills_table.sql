CREATE TABLE professional_skills (
                                     professional_id BIGINT NOT NULL,

                                     skill_id BIGINT NOT NULL,

                                     proficiency_level VARCHAR(50),

                                     years_of_experience INTEGER,

                                     created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                                     PRIMARY KEY (professional_id, skill_id),

                                     CONSTRAINT fk_professional_skills_professional
                                         FOREIGN KEY (professional_id)
                                             REFERENCES professionals(id)
                                             ON DELETE CASCADE,

                                     CONSTRAINT fk_professional_skills_skill
                                         FOREIGN KEY (skill_id)
                                             REFERENCES skills(id)
                                             ON DELETE CASCADE
);

CREATE INDEX idx_professional_skills_professional
    ON professional_skills(professional_id);

CREATE INDEX idx_professional_skills_skill
    ON professional_skills(skill_id);