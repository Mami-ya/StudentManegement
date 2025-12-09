package student.manatement.StudentManegement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;
import student.manatement.StudentManegement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

 @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.searchStudentList();
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
