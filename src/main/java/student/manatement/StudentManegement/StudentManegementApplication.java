package student.manatement.StudentManegement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;
import student.manatement.StudentManegement.repository.StudentRepository;


@SpringBootApplication
public class StudentManegementApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentManegementApplication.class, args);
  }

  }

