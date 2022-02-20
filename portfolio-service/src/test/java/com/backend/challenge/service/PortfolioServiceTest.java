package com.backend.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.backend.challenge.error.PortfolioNotAvailableException;
import com.backend.challenge.repository.PortfolioRepository;
import com.backend.challenge.resource.PortfolioRequest;
import com.backend.challenge.resource.PortfolioResource;
import com.backend.challenge.util.PortfolioTestMockUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class PortfolioServiceTest {
	
	@MockBean
	private PortfolioRepository portfolioRepository;
	
	
	private PortfolioService portfolioService;
	
	@Before
    public void setup() {
		portfolioService = new PortfolioService(portfolioRepository);
    }
	
	@Test
	public void givenUserId_whenGetPortfolio_thenReturnPortolio() {
		String userId = "333";
		PortfolioResource portfolioExpected = PortfolioTestMockUtil.getPortfolioResourceMock();
		PortfolioResource portfolioResource;
		
		given(portfolioRepository.findByUserId(eq(userId)))
			.willReturn(Optional.ofNullable(PortfolioTestMockUtil.getPortfolioMock(userId)));

		portfolioResource = portfolioService.getByUser(userId);
		
        assertThat(portfolioResource).usingRecursiveComparison().isEqualTo(portfolioExpected);

	}
	
	@Test
	public void givenUserIdNotExist_whenGetPortfolio_thenUnprocessableEntity() {
		String userId = "333";

		given(portfolioRepository.findByUserId(eq(userId)))
			.willReturn(Optional.empty());

		assertThatThrownBy(() -> portfolioService.getByUser(userId))
         .isInstanceOf(PortfolioNotAvailableException.class).hasMessage("Portfolio does not exist");
	}
	
	@Test
	public void givenUserIdWithPortfolioRequest__whenUpdatePortfolio_thenReturnVoid() {
		String userId = "333";
        PortfolioRequest request = PortfolioTestMockUtil.getPortfolioRequestMock();

		given(portfolioRepository.update(eq(userId), eq(request)))
			.willReturn(1);

        portfolioService.update(userId, request);

        verify(portfolioRepository, times(1)).update(any() ,any(PortfolioRequest.class));
	}
}
