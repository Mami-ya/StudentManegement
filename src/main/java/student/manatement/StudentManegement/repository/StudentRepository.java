package student.manatement.StudentManegement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;

/**
 * 受講生情報を扱うリポジトリ。
 *
 * 全件検索や 単一条件での検索。コース情報の検索が行えるクラスです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return　全件検索した受講生情報の一覧。
   */
  @Select("SELECT * FROM student")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourses();

  @Insert("""
  INSERT INTO student
  (student_id, name, name_kana, nickname, email, area, age, gender, remark, is_deleted)
  VALUES
  (#{studentId}, #{name}, #{nameKana}, #{nickName}, #{email}, #{area}, #{age}, #{gender}, #{remark}, false)
""")

  void registerStudent(Student student);


}