CREATE TABLE messages (
      id SERIAL PRIMARY KEY,
      chat_id INT NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
      sender_id INT NOT NULL,
      content TEXT NOT NULL,
      sent_at TIMESTAMP DEFAULT NOW()
);