package com.ssm.util;

import java.util.Random;

public class CardUtil {
	public static String[] createNumAndPwd() {
		String[] strs = new String[] { "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		String number = "";
		String password = "";
		for (int i = 0; i < 12; i++) {
			if (i < 6) {
				password += strs[new Random().nextInt(62)];
			}
			number += strs[new Random().nextInt(62)];
		}
		return new String[] { number, password };
	}
}
