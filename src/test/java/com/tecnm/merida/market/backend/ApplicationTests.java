package com.tecnm.merida.market.backend;

import com.tecnm.merida.market.backend.domain.dto.LoginRequestDTO;
import com.tecnm.merida.market.backend.domain.dto.LoginResponseDTO;
import com.tecnm.merida.market.backend.web.controller.AuthController;
import com.tecnm.merida.market.backend.web.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthController authController;

	@Test
	void contextLoads() {
		String generated = passwordEncoder.encode("1234");
		assertTrue(passwordEncoder.matches("1234", generated));

		String token = jwtUtil.generateToken("lionel@messi.com");
		assertNotNull(token);
		assertTrue(jwtUtil.validateToken(token));
		assertEquals("lionel@messi.com", jwtUtil.extractUsername(token));
	}

	@Test
	void loginSuccessAndFailure() {
		LoginRequestDTO validReq = new LoginRequestDTO();
		validReq.setEmail("lionel@messi.com");
		validReq.setPassword("1234");

		ResponseEntity<LoginResponseDTO> okResp = authController.login(validReq);
		assertEquals(HttpStatus.OK, okResp.getStatusCode());
		assertNotNull(okResp.getBody());
		assertTrue(jwtUtil.validateToken(okResp.getBody().getToken()));

		LoginRequestDTO invalidReq = new LoginRequestDTO();
		invalidReq.setEmail("lionel@messi.com");
		invalidReq.setPassword("wrong");

		ResponseEntity<LoginResponseDTO> unauthResp = authController.login(invalidReq);
		assertEquals(HttpStatus.UNAUTHORIZED, unauthResp.getStatusCode());
	}

}
