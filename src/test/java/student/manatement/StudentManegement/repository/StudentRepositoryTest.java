package student.manatement.StudentManegement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生IDで受講生情報が取得できること() {
    String studentId = "1";

    Student actual = sut.searchStudentId(studentId);

    assertThat(actual).isNotNull();
    assertThat(actual.getStudentId()).isEqualTo(studentId);
    assertThat(actual.getName()).isEqualTo("山田太郎");
  }

  @Test
  void 存在しない受講生IDで検索した場合空で返ってくること() {
    Student actual = sut.searchStudentId("9999");

    assertThat(actual).isNull();
  }

  @Test
  void 受講生コースの全件検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();

    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生IDに紐づく受講生コースが取得できること() {
    String studentId = "1";

    List<StudentCourse> actual = sut.searchStudentCourse(studentId);

    assertThat(actual).isNotEmpty();
  }

  @Test
  void 存在しない受講生IDを検索した場合空のリストが返ってくること() {
    List<StudentCourse> actual = sut.searchStudentCourse("9999");

    assertThat(actual).isEmpty();
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("山田太郎");
    student.setNameKana("ヤマダタロウ");
    student.setNickName("たろう");
    student.setEmail("taro@example.com");
    student.setArea("横浜");
    student.setAge(25);
    student.setGender("男性");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生コースの登録がおこなえること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setCoursesId("1");
    studentCourse.setCourseName("Word初級");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourse("1");

    assertThat(actual).isNotEmpty();
    assertThat(actual.get(0).getCourseName()).isEqualTo("Word初級");
  }

  @Test
  void 受講生情報を更新できること() {
    Student student = sut.searchStudentId("1");
    student.setName("山口");

    sut.updateStudent(student);

    Student actual = sut.searchStudentId("1");
    assertThat(actual.getName()).isEqualTo("山口");
  }

  @Test
  void 存在しない受講生IDを指定しても例外は発生しないこと() {
    Student student = new Student();
    student.setStudentId("9999");
    student.setName("テスト");

    sut.updateStudent(student);
  }

  @Test
  void 受講生コースのコース名が更新できること() {
    List<StudentCourse> studentCourseList = sut.searchStudentCourse("1");

    StudentCourse studentCourse = studentCourseList.get(0);
    studentCourse.setCourseName("java");

    sut.updateStudentCourse(studentCourse);

    StudentCourse actual = sut.searchStudentCourse("1").get(0);

    assertThat(actual.getCourseName()).isEqualTo("java");
  }

  @Test
  void 存在しない受講生コースIDを指定しても例外が発生しないこと() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCoursesId("9999");
    studentCourse.setCourseName("テスト");

    sut.updateStudentCourse(studentCourse);
  }
}
