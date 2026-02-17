CREATE TABLE IF NOT EXISTS student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    name_kana VARCHAR(100) NOT NULL,
    nickname VARCHAR(100),
    email VARCHAR(200) NOT NULL,
    area VARCHAR(100),
    age INT,
    gender VARCHAR(50),
    remark TEXT,
    is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
 (
    courses_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP);