-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

-- Create the applications table
CREATE TABLE IF NOT EXISTS applications (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    company TEXT NOT NULL,
    stage TEXT NOT NULL,
    salary INTEGER NOT NULL,
    date_applied TEXT NOT NULL,
    deadline TEXT,
    role_type TEXT,
    priority INTEGER DEFAULT 0,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Insert sample data into users table
INSERT INTO users (username, email, password) VALUES
('testuser', 'test@example.com', 'testpassword');

-- Insert sample data into applications table
INSERT INTO applications (title, company, stage, salary, date_applied, deadline, role_type, priority, user_id) VALUES
('Software Engineer', 'TechCorp', 'Applied', 70000, '2023-12-01', '2023-12-31', 'Full-time', 1, 1),
('Data Analyst', 'DataCorp', 'Interview', 60000, '2023-11-15', NULL, 'Full-time', 0, 1);