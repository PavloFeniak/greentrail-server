CREATE TABLE media_files (
     id SERIAL PRIMARY KEY,
     url TEXT NOT NULL,
     file_name VARCHAR(255) NOT NULL,
     mime_type VARCHAR(100),
     uploaded_by INT NOT NULL,
     uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     related_type VARCHAR(50),
     related_id INT
);
