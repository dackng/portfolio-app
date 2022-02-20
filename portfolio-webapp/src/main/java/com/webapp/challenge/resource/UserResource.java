package com.webapp.challenge.resource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResource {
	private String name;
	private String lastName;
	private String imageUrl;
	private String address;
	private String email;
	private String phone;	
	private String zipCode;
}
