package com.backend.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.challenge.entity.Portfolio;
import com.backend.challenge.resource.PortfolioRequest;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {

	Optional<Portfolio> findByUserId(String userId);
	
	@Query("UPDATE Portfolio SET "
			+ " description = :#{#request.description} "
			+ " , experienceSummary = :#{#request.experienceSummary} "
			+ " , title = :#{#request.title} "
			+ " , twitterUser = :#{#request.twitterUser} "
			+ " , names = :#{#request.user.name} "
			+ " , lastNames = :#{#request.user.lastName} "
			+ " , imageUrl = :#{#request.user.imageUrl} "
			+ " , address = :#{#request.user.address} "
			+ " , email = :#{#request.user.email} "
			+ " , phone = :#{#request.user.phone} "
			+ " , zipCode = :#{#request.user.zipCode} "
			+ " WHERE userId = :userId ")
	@Modifying
	int update(@Param("userId")String userId, @Param("request") PortfolioRequest request);
}
