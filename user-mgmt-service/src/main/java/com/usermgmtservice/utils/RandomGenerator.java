package com.usermgmtservice.utils;

import java.security.SecureRandom;

public class RandomGenerator {
	/** TODO arrays should be suffled in random order */
	private static final String[] ALPHA_NUMERIC_SPECIAL_CHARACTERS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "^", "~", "!", "@", "$", "%", "&", "(", ")" };

	private static final String[] NUMERIC = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	/**
	 * TODO methods should be checked against the Sriman code for the naming
	 * conventions and the code
	 */
	public static String generateEmailOtpCode(int length) {
		StringBuilder code = null;

		code = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int random = new SecureRandom().nextInt(ALPHA_NUMERIC_SPECIAL_CHARACTERS.length);
			code.append(ALPHA_NUMERIC_SPECIAL_CHARACTERS[random]);

		}
		return code.toString();
	}

	/*
	 * To generate Random OTP Mobile code
	 */
	public static String generateMobileOtpCode(int length) {
		StringBuilder code = null;

		code = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int random = new SecureRandom().nextInt(NUMERIC.length);
			code.append(NUMERIC[random]);

		}
		return code.toString();
	}
}
