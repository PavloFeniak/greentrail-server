CREATE TABLE chat_users (
    id SERIAL PRIMARY KEY,
    chat_id INT NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
    user_id INT NOT NULL,
    joined_at TIMESTAMP DEFAULT NOW(),
    left_at TIMESTAMP
);