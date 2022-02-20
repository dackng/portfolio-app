package com.backend.challenge.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel(value = "PortfolioRequest")
public class PortfolioRequest {
	
	@ApiModelProperty(value = "user")
	@NotNull(message = "User should not be null")
	@Valid
	private UserRequest user;
	
	@ApiModelProperty(value = "description")
	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;
	
	@ApiModelProperty(value = "experience summary")
	@NotNull(message = "Experience summary should not be null")
	@NotBlank(message = "Experience summary should not be blank")
	private String experienceSummary;
	
	@ApiModelProperty(value = "title")
	@NotNull(message = "Title should not be null")
	@NotBlank(message = "Title should not be blank")
	private String title;
	
	@ApiModelProperty(value = "twitter user")
	@NotNull(message = "Twitter user should not be null")
	@NotBlank(message = "Twitter user should not be blank")
	private String twitterUser;
}
