//サンプル　説明

package student.manatement.StudentManegement.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.service.StudentService;

public class Sample_StudnetCotroller {

  private StudentService service;

  @GetMapping("/studentList")
  public List<Student> getStudent() {
    return service.searchStudentList();
  }

}
