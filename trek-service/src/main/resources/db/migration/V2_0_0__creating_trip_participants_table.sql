CREATE TABLE trek_participants (
   id SERIAL PRIMARY KEY,
   trip_id INTEGER NOT NULL REFERENCES trek(id) ON DELETE CASCADE,
   user_id INT NOT NULL,
   joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   status VARCHAR(20) DEFAULT 'pending' -- pending, approved, rejected
);