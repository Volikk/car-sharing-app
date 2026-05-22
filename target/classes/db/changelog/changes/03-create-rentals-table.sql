CREATE TABLE IF NOT EXISTS rentals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rental_date DATETIME NOT NULL,
    return_date DATETIME NOT NULL,
    actual_return_date DATETIME DEFAULT NULL,
    car_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (car_id) REFERENCES cars(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;