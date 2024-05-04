CREATE TABLE IF NOT EXISTS weather
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(10),
    city VARCHAR(150),
    temperature DOUBLE,
    humidity INTEGER,
    description VARCHAR(200),
    wind_speed DOUBLE
);
ALTER TABLE weather ALTER COLUMN id RESTART WITH 1;

DELETE FROM weather WHERE TRUE;

INSERT INTO weather (country, city, temperature, humidity, description, wind_speed)
VALUES
    ('US', 'New York', 22.0, 65, 'Sunny', 3.5),
    ('CA', 'Toronto', 18.0, 58, 'Partly cloudy', 5.0),
    ('GB', 'London', 15.5, 77, 'Light rain', 7.2),
    ('FR', 'Paris', 21.0, 60, 'Mostly sunny', 2.5),
    ('JP', 'Tokyo', 25.0, 80, 'Clear night', 1.5);
