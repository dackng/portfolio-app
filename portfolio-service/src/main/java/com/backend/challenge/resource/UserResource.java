package com.backend.challenge.resource;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "UserResource")
public class UserResource {

	@ApiModelProperty(value = "name")
	private String name;
	
	@ApiModelProperty(value = "last name")
	private String lastName;
	
	@ApiModelProperty(value = "image url")
	private String imageUrl;
	
	@ApiModelProperty(value = "address")
	private String address;
	
	@ApiModelProperty(value = "email")
	private String email;
	
	@ApiModelProperty(value = "phone")
	private String phone;
	
	@ApiModelProperty(value = "zip code")
	private String zipCode;
}
