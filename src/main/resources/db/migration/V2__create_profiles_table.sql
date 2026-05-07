CREATE TABLE profiles (
                          id BIGSERIAL PRIMARY KEY,

                          professional_id BIGINT NOT NULL UNIQUE,

                          professional_title VARCHAR(255),

                          professional_objective TEXT,

                          professional_experience TEXT,

                          academic_background TEXT,

                          languages TEXT,

                          technical_skills TEXT,

                          behavioral_skills TEXT,

                          created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                          updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                          CONSTRAINT fk_profiles_professional
                              FOREIGN KEY (professional_id)
                                  REFERENCES professionals(id)
                                  ON DELETE CASCADE
);