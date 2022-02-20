package com.backend.challenge.resource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.backend.challenge.validation.UserValidator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "UserRequest")
public class UserRequest {

	@ApiModelProperty(value = "name")
	@NotNull(message = "Name should not be null")
	@NotBlank(message = "Name should not be blank")
	private String name;
	
	@ApiModelProperty(value = "last name")
	@NotNull(message = "Last name should not be null")
	@NotBlank(message = "Last name should not be blank")
	private String lastName;
	
	@ApiModelProperty(value = "image url")
	private String imageUrl;
	
	@ApiModelProperty(value = "address")
	@NotNull(message = "Address should not be null")
	@NotBlank(message = "Address should not be blank")
	private String address;
	
	@ApiModelProperty(value = "email")
	@NotNull(message = "Email should not be null")
	@NotBlank(message = "Email should not be blank")
	@Email(regexp = UserValidator.EMAIL_REGEX, message = "Email should have this format test@domain.com")
	private String email;
	
	@ApiModelProperty(value = "phone")
	@NotNull(message = "Phone should not be null")
	@NotBlank(message = "Phone should not be blank")
	private String phone;
	
	@ApiModelProperty(value = "zip code")
	@NotNull(message = "Zip code should not be null")
	@NotBlank(message = "Zip code should not be blank")
	private String zipCode;
}
