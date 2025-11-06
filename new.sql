CREATE DATABASE IF NOT EXISTS bmi_localization CHARACTER SET utf8mb4 COLLATE
utf8mb4_unicode_ci;
USE bmi_localization;
-- Stores BMI calculation results
CREATE TABLE IF NOT EXISTS bmi_results (
 id INT AUTO_INCREMENT PRIMARY KEY,
 weight DOUBLE NOT NULL,
 height DOUBLE NOT NULL,
 bmi DOUBLE NOT NULL,
 language VARCHAR(10),
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Stores localized text for UI
CREATE TABLE IF NOT EXISTS localization_strings (
 id INT AUTO_INCREMENT PRIMARY KEY,
 `key` VARCHAR(100) NOT NULL,
 value VARCHAR(255) NOT NULL,
 language VARCHAR(10) NOT NULL
);
