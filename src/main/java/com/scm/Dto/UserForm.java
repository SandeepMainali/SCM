package com.scm.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
        @NotBlank(message = "Username is required")
        @Size(min = 3, message = "Min 3 Characters are required")
        private String name;

        @Email(message = "Invalid Email Address")
        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 4, message = "Min 4 Characters are required")
        private String password;

        @NotBlank(message = "PhoneNumber is required")
        @Size(min = 10, max = 13, message = "Invalid PhoneNumber")
        private String phoneNumber;

        @NotBlank(message = "About is required")
        private String about;
}
