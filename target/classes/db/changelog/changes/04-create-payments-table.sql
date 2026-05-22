CREATE TABLE IF NOT EXISTS payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    rental_id BIGINT NOT NULL,
    session_url TEXT NOT NULL,
    session_id VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (rental_id) REFERENCES rentals(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
