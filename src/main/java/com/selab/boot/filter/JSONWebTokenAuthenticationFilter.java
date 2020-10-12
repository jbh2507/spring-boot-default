package com.selab.boot.filter;

import com.selab.boot.util.JSONWebTokenManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JSONWebTokenAuthenticationFilter extends OncePerRequestFilter {

	@Setter
	@Getter
	private String validSubject = "someString";

	@Setter(onMethod_ = @Autowired)
	private JSONWebTokenManager tokenManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jws = request.getHeader("jwt");

		if(jws == null) {
			sendError(request, response, "JWT is not exist");

            return;
		}

		boolean isValid = tokenManager.isValid(jws, validSubject);

		if(!isValid) {
			sendError(request, response, "JWT is not valid");

            return;
        }
		
		filterChain.doFilter(request, response);
		
	}

	private void sendError(HttpServletRequest request, HttpServletResponse response, String msg)
			throws IOException{

		String requestURI = request.getRequestURI();

		log.warn(msg+" requestURI: "+requestURI);

		response.sendError(HttpServletResponse.SC_FORBIDDEN, msg);

	}

}
