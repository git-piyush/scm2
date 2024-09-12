package com.scm.employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "employee-service",
    url = "${application.config.employee-url}"
)
public interface EmployeeClient {

  @GetMapping("/hello")
  String helloWorld();
}
