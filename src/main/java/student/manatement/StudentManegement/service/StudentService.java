package student.manatement.StudentManegement.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.data.StudentsCourses;
import student.manatement.StudentManegement.domain.StudentDetail;
import student.manatement.StudentManegement.repository.StudentRepository;

@Slf4j
@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public StudentDetail searchStudent(String studentId) {
    //学生情報1件取得
    Student student = repository.searchStudentId(studentId);

    //取ってきた学生のコース一覧を取得
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getStudentId());

    //学生情報とコースをまとめる
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourses(studentsCourses);

    return studentDetail;
  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return repository.searchStudentsCoursesList();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      studentsCourses.setStudentId(studentDetail.getStudent().getStudentId());
      studentsCourses.setStartDate(LocalDateTime.now());
      studentsCourses.setEndDate(LocalDateTime.now().plusYears(1));
      repository.registerStudentCourses(studentsCourses);
    }
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
           repository.updateStudentsCourses(studentsCourses);
    }
  }
}

