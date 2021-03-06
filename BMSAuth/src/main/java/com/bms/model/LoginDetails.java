package com.bms.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetails {
	
	@NotBlank(message = "Username is mandatory ")
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "user name should contain only alphabets and digits")
	private String username;
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "minimum 8 Characters required")
	private String password;
	private String authToken;
}
