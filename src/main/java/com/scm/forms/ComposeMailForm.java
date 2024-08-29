package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComposeMailForm {

    @NotBlank(message = "To Email is required")
    private String email;

    private String subject;

    private String message;

}
