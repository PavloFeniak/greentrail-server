CREATE TABLE user_participation (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    trip_id INT NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);