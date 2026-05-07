CREATE TABLE projects (
                          id BIGSERIAL PRIMARY KEY,

                          professional_id BIGINT NOT NULL,

                          project_name VARCHAR(255) NOT NULL,

                          project_description TEXT,

                          start_date DATE,

                          end_date DATE,

                          project_url TEXT,

                          created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                          updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                          CONSTRAINT fk_projects_professional
                              FOREIGN KEY (professional_id)
                                  REFERENCES professionals(id)
                                  ON DELETE CASCADE
);

CREATE INDEX idx_projects_professional
    ON projects(professional_id);