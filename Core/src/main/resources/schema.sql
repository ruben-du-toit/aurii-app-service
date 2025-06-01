-- USERS TABLE
CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(100),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ACCOUNTS TABLE (One-to-One with User)
CREATE TABLE accounts (
                          id UUID PRIMARY KEY,
                          user_id UUID UNIQUE NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          plan VARCHAR(50),
                          status VARCHAR(50),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- CALENDARS TABLE (One-to-One with User)
CREATE TABLE calendars (
                           id UUID PRIMARY KEY,
                           user_id UUID UNIQUE NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                           title VARCHAR(100),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ACTIVITIES TABLE (as per your Java entity)
CREATE TABLE activities (
                            id SERIAL PRIMARY KEY,

                            user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                            type VARCHAR(255) NOT NULL,

                            duration INTEGER NOT NULL,
                            distance DECIMAL(10, 2),
                            calories INTEGER,

                            logged_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Optionally link activity to a calendar (if desired later)
-- ALTER TABLE activities ADD COLUMN calendar_id UUID REFERENCES calendars(id) ON DELETE SET NULL;

-- DATA PROVIDERS TABLE (One-to-One with User)
CREATE TABLE data_providers (
                                id UUID PRIMARY KEY,
                                user_id UUID UNIQUE NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                provider_name VARCHAR(100),
                                linked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
