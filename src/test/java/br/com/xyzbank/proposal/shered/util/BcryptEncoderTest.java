package br.com.xyzbank.proposal.shered.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class BcryptEncoderTest {
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	private String password = "password";

	@Test
	@DisplayName("should return password if it is already hashed")
	public void test1() {
		var hashedPass = encoder.encode(password);

		var result = BcryptEncoder.encodeIfPlainText(hashedPass);

		assertEquals(hashedPass, result);
		assertEquals(true, encoder.matches(password, hashedPass));
		assertEquals(true, encoder.matches(password, result));
	}

	@Test
	@DisplayName("should return hash of password if his be plain text")
	public void test2() {
		var result = BcryptEncoder.encodeIfPlainText(password);

		assertNotEquals(password, result);

		assertEquals(true, encoder.matches(password, result));
	}
}
