package student.manatement.StudentManegement.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentCourse;
import student.manatement.StudentManegement.domain.StudentDetail;

class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡して受講生詳細のリストが作成できる事() {
    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setCoursesId("1");
    studentCourse.setCourseName("Word初級");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);

  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡したときに紐ずかない受講生コースは除外されること() {
    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("2");
    studentCourse.setCoursesId("1");
    studentCourse.setCourseName("Word初級");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEmpty();


  }

  private static Student createStudent() {
    Student student = new Student();
    student.setStudentId("1");
    student.setName("山田太郎");
    student.setNameKana("ヤマダタロウ");
    student.setNickName("たろう");
    student.setEmail("taro@example.com");
    student.setArea("横浜");
    student.setAge(25);
    student.setGender("男性");
    student.setRemark("");
    student.setDeleted(false);
    return student;
  }
}