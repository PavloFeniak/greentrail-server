CREATE TABLE trek_problems (
   id SERIAL PRIMARY KEY,
   title VARCHAR(100) NOT NULL,
   description VARCHAR(500) NOT NULL ,
   latitude DOUBLE PRECISION NOT NULL,
   longitude DOUBLE PRECISION NOT NULL,
   is_fixable BOOLEAN DEFAULT TRUE,
   created_by INT NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   status VARCHAR(20) DEFAULT 'open' -- open, in_progress, resolved
);