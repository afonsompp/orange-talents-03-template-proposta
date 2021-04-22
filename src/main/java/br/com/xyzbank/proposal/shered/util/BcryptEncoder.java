package br.com.xyzbank.proposal.shered.util;

import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoder {
	private static Pattern BCRYPT_PATTERN =
			Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

	private BcryptEncoder() {

	}

	public static String encodeIfPlainText(String value) {
		var matcher = BCRYPT_PATTERN.matcher(value);
		if (matcher.matches()) {
			return value;
		}
		var encoder = new BCryptPasswordEncoder();
		return encoder.encode(value);
	}

}
