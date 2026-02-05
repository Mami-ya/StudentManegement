package student.manatement.StudentManegement.controller.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentCourse;
import student.manatement.StudentManegement.domain.StudentDetail;

class StudentConverterTest {

  private StudentConverter converter;

  @BeforeEach
  void before() {
    converter = new StudentConverter();
  }

  @Test
  void 受講生情報と受講生コース情報が紐づいていること() {
    Student student1 = new Student();
    student1.setStudentId("1");

    Student student2 = new Student();
    student2.setStudentId("2");

    List<Student> studentList = List.of(student1, student2);

    StudentCourse course1 = new StudentCourse();
    course1.setStudentId("1");
    course1.setCourseName("Word初級");

    StudentCourse course2 = new StudentCourse();
    course2.setStudentId("2");
    course2.setCourseName("Excel中級");

    List<StudentCourse> studentCourseList = List.of(course1, course2);

    List<StudentDetail> result = converter.convertStudentDetails(studentList, studentCourseList);

    assertEquals(2, result.size());

    StudentDetail studentDetail = result.get(0);
    assertEquals("1", studentDetail.getStudent().getStudentId());
    assertEquals(1, studentDetail.getStudentCourseList().size());
    assertEquals("Word初級", studentDetail.getStudentCourseList().get(0).getCourseName());

    StudentDetail studentDetail2 = result.get(1);
    assertEquals("2", studentDetail2.getStudent().getStudentId());
    assertEquals(1, studentDetail2.getStudentCourseList().size());
    assertEquals("Excel中級", studentDetail2.getStudentCourseList().get(0).getCourseName());
  }

  @Test
  void コース情報が存在しない学生の場合せも空のリストが作られる事() {
    Student student = new Student();
    student.setStudentId("3");

    List<Student> studentList = List.of(student);

    List<StudentCourse> studentCourseList = List.of();

    List<StudentDetail> result = converter.convertStudentDetails(studentList, studentCourseList);

    assertEquals(1, result.size());
    assertEquals(0, result.get(0).getStudentCourseList().size());


  }
}