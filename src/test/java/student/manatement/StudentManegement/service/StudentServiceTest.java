package student.manatement.StudentManegement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import student.manatement.StudentManegement.controller.converter.StudentConverter;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentCourse;
import student.manatement.StudentManegement.domain.StudentDetail;
import student.manatement.StudentManegement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;
  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
    //事前準備
    //StudentService sut = new StudentService(repository, converter); @Beforeに組み込み済み
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    List<StudentDetail> actual = sut.searchStudentList();
    //実行
    //List<StudentDetail> expected = new ArrayList<>();
    //検証
    //Assertions.assertEquals(expected, actual);

    //sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生詳細の検索＿studentIdと紐づく受講生コース情報が適切に呼び出せていること() {
    String studentId = "S001";

    Student student = new Student();
    student.setStudentId(studentId);

    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.searchStudentId(studentId)).thenReturn(student);
    when(repository.searchStudentCourse(studentId)).thenReturn(studentCourseList);

    StudentDetail actual = sut.searchStudent(studentId);

    verify(repository, times(1)).searchStudentId(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);

    assertEquals(studentId, actual.getStudent().getStudentId());
  }

  @Test
  void 受講生詳細登録＿リポジトリの処理が適切に呼び出されていること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    studentCourseList.add(studentCourse);

    StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

    StudentDetail actual = sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);

    assertSame(studentDetail, actual);
  }

  @Test
  void 受講生詳細の登録＿初期化処理が行われていること() {
    String studentId = "S001";
    Student student = new Student();
    student.setStudentId(studentId);
    StudentCourse studentCourse = new StudentCourse();

    sut.initStudentsCourse(studentCourse, student.getStudentId());

    assertEquals(studentId,studentCourse.getStudentId());
    assertEquals(LocalDateTime.now().getHour(), studentCourse. getStartDate().getHour());
    assertEquals(LocalDateTime.now().plusYears(1).getYear(), studentCourse. getEndDate().getYear());
  }
  @Test
  void 受講生詳細の更新＿リポジトリの処理が適切に呼び出されていること() {
    Student student = new Student();
    student.setStudentId("S001");

    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

    sut.updateStudent(studentDetail);

    verify(repository,times(1)).updateStudent(student);
    verify(repository,times(1)).updateStudentCourse(studentCourse);
    }
}

