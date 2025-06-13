-- USERS TABLE
CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(50),
                       surname VARCHAR(50),
                       password VARCHAR(100),
                       role VARCHAR(50),
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
                            id UUID DEFAULT random_uuid() PRIMARY KEY,
                            user_id UUID NOT NULL,
                            plan_id UUID,
                            title VARCHAR(255),
                            description TEXT,
                            category VARCHAR(50),
                            type VARCHAR(50),
                            sport_type VARCHAR(50),
                            workout_type VARCHAR(100),
                            status VARCHAR(50),
                            is_manual BOOLEAN DEFAULT FALSE,
                            has_heart_rate_data BOOLEAN DEFAULT FALSE,
                            scheduled_date TIMESTAMP,
                            completed_date TIMESTAMP,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            timezone VARCHAR(50),
                            tags TEXT,
                            details_schema VARCHAR(100),
                            duration_minutes INTEGER,
                            elapsed_minutes INTEGER,
                            distance_km DECIMAL(10,2),
                            elevation_gain INTEGER,
                            avg_speed DECIMAL(10,2),
                            calories_burned INTEGER,
                            perceived_exertion INTEGER,
                            heart_rate_avg INTEGER,
                            cadence INTEGER,
                            stride_length DECIMAL(5,2),
                            total_sets INTEGER,
                            total_reps INTEGER,
                            pages_read INTEGER,
                            activity_achieved BOOLEAN,

    -- ✅ Foreign key constraint defined at the end
                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
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
