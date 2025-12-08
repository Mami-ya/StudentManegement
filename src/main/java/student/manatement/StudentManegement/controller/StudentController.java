package student.manatement.StudentManegement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import student.manatement.StudentManegement.controller.converter.StudentConverter;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;
import student.manatement.StudentManegement.domain.StudentDetail;
import student.manatement.StudentManegement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCoursesList();

    return converter.convertStudentDetails(students, studentsCourses);
//   public List<Student> getStudent() {
//   List<Student> studentList = new ArrayList<>();
//   studentList = repository.search();
//   return studentList;
  }


  @GetMapping("/studentsCourses")
  public List<StudentsCourses> getStudentCourses() {
    return service.searchStudentsCoursesList();

  }
}

//　GETは取得する、リクエストの結果を受け取る。デフォルト。
//　POSTは情報を与える、渡す。データーの登録、更新。
