package student.manatement.StudentManegement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import student.manatement.StudentManegement.exception.TestException;

@RestController
public class TestController {

  /**
   * 例外を発生させるメゾットです
   */
  @GetMapping("/exceptionTest")
  public String exceptionTest() throws TestException {
    throw new TestException("テスト用に例外を発生させています");
  }
}

