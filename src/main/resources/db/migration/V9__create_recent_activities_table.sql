CREATE TABLE recent_activities (
                                   id BIGSERIAL PRIMARY KEY,

                                   professional_id BIGINT NOT NULL,

                                   activity_type VARCHAR(100) NOT NULL,

                                   activity_description TEXT NOT NULL,

                                   activity_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                                   CONSTRAINT fk_recent_activities_professional
                                       FOREIGN KEY (professional_id)
                                           REFERENCES professionals(id)
                                           ON DELETE CASCADE
);

CREATE INDEX idx_recent_activities_professional
    ON recent_activities(professional_id);

CREATE INDEX idx_recent_activities_date
    ON recent_activities(activity_date DESC);