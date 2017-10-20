package com.client.commande.ClientCommandeGestion.filter;

import com.client.commande.ClientCommandeGestion.config.security.TokenProvider;
import com.client.commande.ClientCommandeGestion.controller.utils.Constants;
import org.springframework.web.filter.GenericFilterBean;

import java.util.logging.Logger;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security principal if a
 * header corresponding to a valid user is found.
 */
public class JWTFilter extends GenericFilterBean {

	public final static String AUTHORIZATION_HEADER = "Authorization";
	private final org.slf4j.Logger log = LoggerFactory.getLogger(JWTFilter.class);

	private TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			String jwt = resolveToken(httpServletRequest);
			if (jwt != null) {
				if (this.tokenProvider.validateToken(jwt)) {
					Authentication authentication = this.tokenProvider.getAuthentication(jwt);
					if (authentication != null) {
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (ExpiredJwtException eje) {
			log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
			((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
