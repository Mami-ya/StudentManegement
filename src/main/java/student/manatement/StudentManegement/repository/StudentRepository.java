package student.manatement.StudentManegement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;

/**
 * 受講生情報を扱うリポジトリ。
 * <p>
 * 全件検索や 単一条件での検索。コース情報の検索が行えるクラスです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return　全件検索した受講生情報の一覧。
   */
  @Select("SELECT * FROM student ")
  List<Student> search();

  @Select("SELECT * FROM student WHERE student_id = #{studentId}")
  Student searchStudentId(String studentId);

  //コースを全件取得
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  //1人分のコースを取得
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);

  @Insert("""
      INSERT INTO student(name, name_kana, nickname, email, area, age, gender, remark, is_deleted)
      VALUES(#{name}, #{nameKana}, #{nickName}, #{email}, #{area}, #{age}, #{gender}, #{remark}, false)""")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(student_id, course_name, start_date, end_date) "
      + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudentCourses(StudentsCourses studentsCourses);


  //学生情報更新
 @Update("""
      UPDATE student SET name = #{name}, name_kana = #{nameKana}, nickname = #{nickName}, email = #{email},
       area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, is_deleted = #{isDeleted} WHERE student_id = #{studentId}""")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE courses_id = #{coursesId}" )
  void updateStudentsCourses(StudentsCourses studentsCourses);
}