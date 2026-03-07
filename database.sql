CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

CREATE TABLE students (
    cne VARCHAR(20) PRIMARY KEY,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100),
    birth_date DATE
);

INSERT INTO students VALUES
('CNE001', 'Ahmed', 'Alami', 'ahmed.alami@example.com', '2000-06-15'),
('CNE002', 'Fatima', 'Benali', 'fatima.benali@example.com', '2001-09-22'),
('CNE003', 'Youssef', 'Idrissi', 'youssef.idrissi@example.com', '1999-03-10'),
('CNE004', 'Salma', 'Tazi', 'salma.tazi@example.com', '2000-12-05'),
('CNE005', 'Omar', 'Fassi', 'omar.fassi@example.com', '2001-04-18');
