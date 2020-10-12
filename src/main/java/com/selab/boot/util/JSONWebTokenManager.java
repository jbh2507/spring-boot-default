package com.selab.boot.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class JSONWebTokenManager {
	
	private JwtParser jwtParser;

	private JwtBuilder jwtTokenBuilder;

	public JSONWebTokenManager(Key secretKey){
		this.jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
		this.jwtTokenBuilder = Jwts.builder().signWith(secretKey);
	}
	
	public String getJSONWebToken(String subject) {
		return jwtTokenBuilder.setSubject(subject).compact();
	}
	
	public String parseJSONWebToken(String jws) {
		return jwtParser.parseClaimsJws(jws).getBody().getSubject();
	}
	
	public boolean isValid(String jws, String subject) {
		String jwsSubject = jwtParser.parseClaimsJws(jws).getBody().getSubject();

		return jwsSubject.equals(subject);
	}
}
