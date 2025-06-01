-- Insert USERS (must come first)
INSERT INTO users (id, name, email, created_at) VALUES
                                                    ('11111111-1111-1111-1111-111111111111', 'Alice Example', 'alice@example.com', CURRENT_TIMESTAMP),
                                                    ('22222222-2222-2222-2222-222222222222', 'Bob Tester', 'bob@example.com', CURRENT_TIMESTAMP),
                                                    ('33333333-3333-3333-3333-333333333333', 'Charlie Sample', 'charlie@example.com', CURRENT_TIMESTAMP);

-- Insert ACTIVITIES (safe now that users exist)
INSERT INTO activities (user_id, type, duration, distance, calories, logged_at, created_at) VALUES
                                                                                                ('11111111-1111-1111-1111-111111111111', 'Running', 50, 12.34, 650, '2025-05-30 07:45:00', '2025-05-30 07:45:00'),
                                                                                                ('11111111-1111-1111-1111-111111111111', 'Cycling', 30, 15.00, 450, '2025-05-31 18:30:00', '2025-05-31 18:30:00'),
                                                                                                ('22222222-2222-2222-2222-222222222222', 'Yoga', 60, NULL, 220, '2025-05-30 06:00:00', '2025-05-30 06:00:00'),
                                                                                                ('22222222-2222-2222-2222-222222222222', 'Meditation', 25, NULL, NULL, '2025-05-31 08:00:00', '2025-05-31 08:00:00'),
                                                                                                ('33333333-3333-3333-3333-333333333333', 'Swimming', 45, 1.25, 500, '2025-05-29 09:00:00', '2025-05-29 09:00:00'),
                                                                                                ('33333333-3333-3333-3333-333333333333', 'Running', 35, 7.8, 420, '2025-05-28 17:15:00', '2025-05-28 17:15:00');
