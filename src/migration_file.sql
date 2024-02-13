CREATE TABLE chat_history (
                              id BIGSERIAL PRIMARY KEY,
                              username VARCHAR(255) NOT NULL,
                              user_input TEXT NOT NULL,
                              server_response TEXT NOT NULL,
                              timestamp TIMESTAMP NOT NULL
);