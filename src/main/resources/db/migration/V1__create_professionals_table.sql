CREATE TABLE professionals (
                               id BIGSERIAL PRIMARY KEY,

                               keycloak_user_id UUID NOT NULL UNIQUE,

                               full_name VARCHAR(255) NOT NULL,

                               email VARCHAR(255) NOT NULL UNIQUE,

                               date_of_birth DATE,

                               phone VARCHAR(30),

                               address TEXT,

                               bio TEXT,

                               profile_picture_url TEXT,

                               created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

                               updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_professionals_keycloak_user_id
    ON professionals(keycloak_user_id);

CREATE INDEX idx_professionals_email
    ON professionals(email);