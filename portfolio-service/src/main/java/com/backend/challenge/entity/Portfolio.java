package com.backend.challenge.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Portfolio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idportfolio;
	private String description;
	private String experienceSummary;
	private String imageUrl;
	private String lastNames;
	private String names;
	private String title;
	private String twitterUser;
	private String userId;
	private String address;
	private String email;
	private String phone;
	private String zipCode;
}
