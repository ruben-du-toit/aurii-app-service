-- Make sure UUID extension is enabled (PostgreSQL)
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Optionally insert matching account records
INSERT INTO users (id, name, email) VALUES
                                        ('11111111-1111-1111-1111-111111111111', 'User 1', 'user1@example.com'),
                                        ('22222222-2222-2222-2222-222222222222', 'User 2', 'user2@example.com'),
                                        ('33333333-3333-3333-3333-333333333333', 'User 3', 'user3@example.com');

INSERT INTO accounts (id, user_id, plan, status) VALUES
                                                     (random_uuid(), '11111111-1111-1111-1111-111111111111', 'Free', 'Active'),
                                                     (random_uuid(), '22222222-2222-2222-2222-222222222222', 'Premium', 'Active'),
                                                     (random_uuid(), '33333333-3333-3333-3333-333333333333', 'Free', 'Inactive');

-- Optionally insert matching calendars
INSERT INTO calendars (id, user_id, title) VALUES
                                               (random_uuid(), '11111111-1111-1111-1111-111111111111', 'User 1 Calendar'),
                                               (random_uuid(), '22222222-2222-2222-2222-222222222222', 'User 2 Calendar'),
                                               (random_uuid(), '33333333-3333-3333-3333-333333333333', 'User 3 Calendar');

-- Optionally insert matching data providers
INSERT INTO data_providers (id, user_id, provider_name) VALUES
                                                            (random_uuid(), '11111111-1111-1111-1111-111111111111', 'Strava'),
                                                            (random_uuid(), '22222222-2222-2222-2222-222222222222', 'Garmin'),
                                                            (random_uuid(), '33333333-3333-3333-3333-333333333333', 'ManualEntry');

-- Seed activity data for each user
INSERT INTO activities (
    id,
    user_id,
    plan_id,
    title,
    description,
    category,
    type,
    sport_type,
    workout_type,
    status,
    scheduled_date,
    completed_date,
    created_at,
    updated_at,
    timezone,
    is_manual,
    has_heart_rate_data,
    tags,
    details_schema,
    duration_minutes,
    elapsed_minutes,
    distance_km,
    elevation_gain,
    avg_speed,
    calories_burned,
    perceived_exertion,
    heart_rate_avg,
    cadence,
    stride_length,
    total_sets,
    total_reps,
    pages_read,
    activity_achieved
) VALUES
-- Alice - Zone 2 run
('a1111111-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', NULL,
 'Easy Endurance Run', 'Zone 2 morning run around the park.', 'Physical', 'Run', 'Run', 'Base Endurance',
 'Completed', '2025-05-30 07:00:00', '2025-05-30 07:45:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GMT+02:00',
 FALSE, TRUE, 'Zone2,Morning,Park', 'RunningMetrics',
 45, 45, 10.2, 50, 9.5, 420, 6, 145, 162, 1.25, NULL, NULL, NULL, TRUE),

-- Alice - Easy recovery cycle
('a2222222-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', NULL,
 'Recovery Ride', 'Low-intensity 30-minute spin.', 'Physical', 'Ride', 'Cycling', 'Recovery',
 'Completed', '2025-05-31 18:00:00', '2025-05-31 18:30:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GMT+02:00',
 FALSE, FALSE, 'Recovery,Evening,Cycling', 'CyclingMetrics',
 30, 30, 15.0, 80, 10.0, 450, 3, NULL, NULL, NULL, NULL, NULL, NULL, TRUE),

-- Bob - Yoga session
('b1111111-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222', NULL,
 'Sunrise Yoga', '60-minute vinyasa flow.', 'Physical', 'Yoga', NULL, NULL,
 'Completed', '2025-05-30 06:00:00', '2025-05-30 07:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GMT+02:00',
 TRUE, FALSE, 'Yoga,Morning,Balance', 'GenericMetrics',
 60, 60, NULL, NULL, NULL, 220, NULL, NULL, NULL, NULL, NULL, NULL, NULL, TRUE),

-- Bob - Meditation session
('b2222222-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222', NULL,
 'Mindful Meditation', 'Focused breathing and body scan.', 'Cognitive', 'Meditation', NULL, NULL,
 'Completed', '2025-05-31 08:00:00', '2025-05-31 08:25:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GMT+02:00',
 TRUE, FALSE, 'Calm,Morning,Focus', 'MindMetrics',
 25, 25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, TRUE),

-- Charlie - Swim session
('c1111111-cccc-cccc-cccc-cccccccccccc', '33333333-3333-3333-3333-333333333333', NULL,
 'Endurance Swim', '45-minute pool session with intervals.', 'Physical', 'Swim', 'Swimming', 'Endurance',
 'Completed', '2025-05-29 09:00:00', '2025-05-29 09:45:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GMT+02:00',
 FALSE, TRUE, 'Swim,Endurance,Morning', 'SwimMetrics',
 45, 45, 1.25, 20, 2.5, 500, 7, 130, NULL, NULL, NULL, NULL, NULL, TRUE),

-- Charlie - Progression Run
('c2222222-cccc-cccc-cccc-cccccccccccc', '33333333-3333-3333-3333-333333333333', NULL,
 'Progression Run', 'Pushed final 10 minutes to threshold pace.', 'Physical', 'Run', 'Run', 'Threshold',
 'Completed', '2025-05-28 17:00:00', '2025-05-28 17:35:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GMT+02:00',
 TRUE, TRUE, 'Threshold,Evening,Progression', 'RunningMetrics',
 35, 35, 7.8, 40, 10.5, 420, 8, 155, 170, 1.30, NULL, NULL, NULL, TRUE);
