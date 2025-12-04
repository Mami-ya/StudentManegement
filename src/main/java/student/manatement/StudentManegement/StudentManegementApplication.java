package student.manatement.StudentManegement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController

public class StudentManegementApplication {

  @Autowired
  private StudentRepository repository;

  private Map<String, String> student = new HashMap<>();

  public static void main(String[] args) {
    SpringApplication.run(StudentManegementApplication.class, args);
  }


  @GetMapping("/studentList")
  public List<Student> getStudent() {
    return repository.search();
//   public List<Student> getStudent() {
//   List<Student> studentList = new ArrayList<>();
//   studentList = repository.search();
//   return studentList;
    }
  @GetMapping("/studentsCourses")
  public  List<StudentsCourses> getStudentCourses() {
    return repository.findAll();

  }
}



//　GETは取得する、リクエストの結果を受け取る。デフォルト。
//　POSTは情報を与える、渡す。データーの登録、更新。

