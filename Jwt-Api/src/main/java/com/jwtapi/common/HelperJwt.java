package com.jwtapi.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class HelperJwt {

	private HelperJwt() {
		throw new IllegalStateException("Utility class");
	}

	public static void setCookie(HttpServletResponse response, String value, String name, long cookieDuration) {
		Cookie cookie = new Cookie(value, name);
		cookie.setHttpOnly(true);
		cookie.setMaxAge((int) cookieDuration);
		cookie.setPath("/");

		response.addCookie(cookie);
	}
}
