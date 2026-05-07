CREATE TABLE work_experiences (
                                  id BIGSERIAL PRIMARY KEY,

                                  professional_id BIGINT NOT NULL,

                                  company VARCHAR(255) NOT NULL,

                                  position VARCHAR(255) NOT NULL,

                                  start_date DATE NOT NULL,

                                  end_date DATE,

                                  activities_description TEXT,

                                  project_highlight TEXT,

                                  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                                  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                                  CONSTRAINT fk_work_experiences_professional
                                      FOREIGN KEY (professional_id)
                                          REFERENCES professionals(id)
                                          ON DELETE CASCADE
);

CREATE INDEX idx_work_experiences_professional
    ON work_experiences(professional_id);