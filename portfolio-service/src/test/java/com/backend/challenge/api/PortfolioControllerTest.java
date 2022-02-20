package com.backend.challenge.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.backend.challenge.resource.PortfolioRequest;
import com.backend.challenge.service.PortfolioService;
import com.backend.challenge.util.FileTestUtil;
import com.backend.challenge.util.PortfolioTestMockUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PortfolioController.class)
public class PortfolioControllerTest {
	
	private static final String BASE_ENDPOINT = "/api/v1/portfolio";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PortfolioService portfolioService;
	
    private ObjectMapper objectMapper;
	
    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

	@Test
	public void givenUserId_whenGetPortfolio_thenReturnResourceOk() throws Exception {
        String jsonExpected = FileTestUtil.readFile("/json/findPortfolio.json");
		String userId = "555";
		
        given(portfolioService.getByUser(userId))
        	.willReturn(PortfolioTestMockUtil.getPortfolioResourceMock());

		mockMvc.perform(get(BASE_ENDPOINT + "/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonExpected))
				.andExpect(status().isOk());
	}
	
	@Test
	public void givenUserId_whenUpdatePortfolio_thenNotReturnContent() throws Exception {
        PortfolioRequest request = PortfolioTestMockUtil.getPortfolioRequestMock();
		String userId = "555";
		
		mockMvc.perform(put(BASE_ENDPOINT + "/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void givenInvalidEmail_whenUpdatePortfolio_thenReturnBadRequest() throws Exception {
        PortfolioRequest request = PortfolioTestMockUtil.getPortfolioRequestMock();
		String userId = "555";
		request.getUser().setEmail("test@@gmail.com");
		
		mockMvc.perform(put(BASE_ENDPOINT + "/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest());
	}
}
