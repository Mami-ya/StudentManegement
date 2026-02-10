INSERT INTO student
(name, name_kana, nickname, email, area, age, gender, remark, is_deleted)
VALUES
('山田太郎', 'ヤマダタロウ', 'たろう', 'taro@example.com', '横浜市', 25, '男性', NULL, 0),
('鈴木花子', 'スズキハナコ', 'はなこ', 'hana@example.com', '大阪市', 28, '女性', NULL, 0),
('高橋一郎', 'タカハシイチロウ', 'いち', 'ichi@example.com', '福岡市', 22, '男性', NULL, 0),
('佐藤光', 'サトウヒカリ', 'ひかり', 'hikari@example.com', '名古屋市', 30, '女性', NULL, 0),
('田中浩一', 'タナカコウイチ', 'こう', 'kou@example.com', '札幌市', 26, '男性', NULL, 0);

INSERT INTO students_courses
(student_id, course_name, start_date, end_date)
VALUES
(1, 'Word初級', '2025-01-10 10:00:00', '2025-02-10 10:00:00'),
(2, 'Excel中級', '2025-01-15 10:00:00', '2025-03-01 10:00:00'),
(3, 'パソコン基礎', '2025-02-01 10:00:00', '2025-02-28 10:00:00'),
(4, 'Excel初級', '2025-01-20 10:00:00', '2025-03-05 10:00:00'),
(5, 'Javaプログラミング入門', '2025-01-25 10:00:00', '2025-04-01 10:00:00');