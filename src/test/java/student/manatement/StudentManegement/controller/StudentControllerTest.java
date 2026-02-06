package student.manatement.StudentManegement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import student.manatement.StudentManegement.data.Student;
import student.manatement.StudentManegement.domain.StudentDetail;
import student.manatement.StudentManegement.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できてからのリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生検索詳細が実行できて受講生情報が返ってくること() throws Exception {
    mockMvc.perform(get("/student/1"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent("1");
  }

  @Test
  void 受講生登録が実行できる事() throws Exception {
    mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
                {
                  "student": {
                    "studentId": "1",
                    "name": "山田太郎",
                    "nameKana": "ヤマダタロウ",
                    "nickName": "たろう",
                    "email": "taro@example.com",
                    "area": "横浜",
                    "age": 20,
                    "gender": "男性"
                  },
                  "studentCourseList": []
                }
                """

        ))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }


  @Test
  void 受講生詳細の更新が実行できて成功メッセージが返ること() throws Exception {
    mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
        """
            {
              "student": {
                "studentId": "1",
                "name": "山田太郎",
                "nameKana": "ヤマダタロウ",
                "nickName": "たろう",
                "email": "taro@example.com",
                "area": "横浜",
                "age": 20,
                "gender": "男性",
                "remark": ""
              },
              "studentCourseList": [
              {
              "courses_id": "1",
              "student_id": "1",
              "course_name": "Word初級",
              "start_date": "2025-01-10 10:00:00",
              "end_date": "2025-02-10 10:00:00"
              }
              ]
            }
            """
    ))
        .andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());
  }


  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと() {
    Student student = new Student();
    student.setStudentId("1");
    student.setName("山田太郎");
    student.setNameKana("ヤマダタロウ");
    student.setNickName("たろう");
    student.setEmail("taro@example.com");
    student.setArea("横浜");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);

  }

  @Test
  void 受講生詳細の受講生IDに数字以外を用いた時に入力チェックに掛かること() {
    Student student = new Student();
    student.setStudentId("テストです");
    student.setName("山田太郎");
    student.setNameKana("ヤマダタロウ");
    student.setNickName("たろう");
    student.setEmail("taro@example.com");
    student.setArea("横浜");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");

  }
}
