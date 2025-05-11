CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(1000) NOT NULL,
    date_of_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_picture VARCHAR(225),
    address VARCHAR(100)
);

