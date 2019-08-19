package com.lu.activiti.jwt;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lu.activiti.domain.JwtObject;

/**
 * 
 * @author luxingxiao
 *
 */
@Service
public class JwtParser {
	public JwtObject parseJwt(String jwt) {
		String[] splitString = jwt.split("\\.");
		String base64EncodedBody = splitString[1];

		Base64 base64Url = new Base64(true);

		String body = new String(base64Url.decode(base64EncodedBody));
		return JSONObject.parseObject(body, JwtObject.class);
	}
}
