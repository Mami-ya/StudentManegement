package student.manatement.StudentManegement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;


}
