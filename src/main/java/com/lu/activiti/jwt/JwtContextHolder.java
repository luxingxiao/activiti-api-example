package com.lu.activiti.jwt;
/**
 * 
 * @author luxingxiao
 *
 */
public class JwtContextHolder {
	private static final ThreadLocal<String> jwtContext = new ThreadLocal<>();

	public static void setJwt(String jwt) {
		jwtContext.set(jwt);
	}

	public static String getJwt() {
		return jwtContext.get();
	}

	public static void clear() {
		jwtContext.remove();
	}
}
