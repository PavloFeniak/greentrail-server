CREATE TABLE chats (
   id SERIAL PRIMARY KEY,
   trip_id INT NOT NULL,
   created_at TIMESTAMP DEFAULT NOW()
);
