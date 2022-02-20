package com.backend.challenge.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.challenge.resource.PortfolioRequest;
import com.backend.challenge.resource.PortfolioResource;
import com.backend.challenge.service.PortfolioService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/portfolio")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT})
@RequiredArgsConstructor
@Api(tags = { "PortfolioController" }, value = "/api/v1/portfolio", produces = "application/json")
public class PortfolioController {

	private final PortfolioService portfolioService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<PortfolioResource> getByUser(
			@PathVariable @Valid @NotNull String userId){
		return ResponseEntity.ok(portfolioService.getByUser(userId));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Void> update(@PathVariable @Valid @NotNull String userId
			, @Valid @RequestBody PortfolioRequest request){
		portfolioService.update(userId, request);
		return ResponseEntity.noContent().build();
	}
}
