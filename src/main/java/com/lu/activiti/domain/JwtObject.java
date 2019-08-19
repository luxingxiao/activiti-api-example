/**
 * 
 */
package com.lu.activiti.domain;

import lombok.Data;

/**
 * @author luxingxiao
 *
 */
@Data
public class JwtObject {
	private String jti;
	private String exp;
	private String iat;
	private String preferred_username;
}
