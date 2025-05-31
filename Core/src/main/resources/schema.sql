CREATE TABLE activities (
                            id SERIAL PRIMARY KEY,

                            user_id UUID NOT NULL,               -- UUID for userId (align with Java UUID type)
                            type VARCHAR(255) NOT NULL,          -- simple string for type (no FK)

                            duration INTEGER NOT NULL,            -- duration (minutes)
                            distance DECIMAL(10, 2),              -- distance in km (nullable)
                            calories INTEGER,                     -- calories burned (nullable)

                            logged_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
