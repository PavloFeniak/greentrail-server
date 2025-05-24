ALTER TABLE media_files
ALTER COLUMN uploaded_by TYPE VARCHAR(255)
USING uploaded_by::VARCHAR;