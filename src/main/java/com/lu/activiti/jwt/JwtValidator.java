package com.lu.activiti.jwt;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class JwtValidator {
	public boolean validateJwt(String jwt) {
		String[] splitString = jwt.split("\\.");
		String base64EncodedHeader = splitString[0];
		String base64EncodedBody = splitString[1];
		String base64EncodedSignature = splitString[2];

		System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
		Base64 base64Url = new Base64(true);
		String header = new String(base64Url.decode(base64EncodedHeader));
		System.out.println("JWT Header : " + header);

		System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
		String body = new String(base64Url.decode(base64EncodedBody));
		System.out.println("JWT Body : " + body);
		return false;
	}
}
