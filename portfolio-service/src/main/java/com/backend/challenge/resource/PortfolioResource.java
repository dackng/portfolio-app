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
@ApiModel(value = "PortfolioResource")
public class PortfolioResource {
	
	@ApiModelProperty(value = "user")
	private UserResource user;
	
	@ApiModelProperty(value = "description")
	private String description;
	
	@ApiModelProperty(value = "experience summary")
	private String experienceSummary;
	
	@ApiModelProperty(value = "title")
	private String title;
	
	@ApiModelProperty(value = "twitter user")
	private String twitterUser;
}
