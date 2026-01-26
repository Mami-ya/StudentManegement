package student.manatement.StudentManegement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   *
   * @return　受講生情報一覧（全件）
   */
  @Select("""
  SELECT student_id, name, name_kana, nickname, email, area, age, gender, remark, is_deleted AS deleted FROM student""")
  List<Student> search();

  /**
   * 受講生の検索を行います。
   * @param studentId　受講生ID
   * @return　受講生
   */
  @Select("""
  SELECT student_id, name, name_kana, nickname, email, area, age, gender, remark, is_deleted AS deleted FROM student
  WHERE student_id = #{studentId}
""")
  Student searchStudentId(String studentId);


  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return　受講生のコース情報(全件）
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentCourseList();

  /**
   *受講生IDにに紐づく受講生コース情報を検索します。
   *
   * @param studentId　受講生ID
   * @return　受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> searchStudentCourse(String studentId);

  /**
   * 受講生を新規登録します。IDに関しては自動採番を行います。
   *
   * @param student　受講生
   */
  @Insert("""
      INSERT INTO student(name, name_kana, nickname, email, area, age, gender, remark, is_deleted)
      VALUES(#{name}, #{nameKana}, #{nickName}, #{email}, #{area}, #{age}, #{gender}, #{remark}, false)""")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  /**
   * 受講生コース情報を新規登録します。IDに関しては自動採番を行います。
   * @param studentCourse　受講生コース情報
   */
  @Insert("INSERT INTO students_courses(student_id, course_name, start_date, end_date) "
      + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
  @Options(useGeneratedKeys = true, keyProperty = "coursesId")
  void registerStudentCourse(StudentCourse studentCourse);


  /**
   * 受講生を更新します。
   *
   * @param student　受講生
   */
 @Update("""
      UPDATE student SET name = #{name}, name_kana = #{nameKana}, nickname = #{nickName}, email = #{email},
       area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, is_deleted = #{deleted} WHERE student_id = #{studentId}""")
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース名を更新します。
   *
   * @param studentCourse　受講生コース情報
   */
  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE courses_id = #{coursesId}" )
  void updateStudentCourse(StudentCourse studentCourse);
}