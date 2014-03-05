package com.hensun.springTest.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.DigestUtils;

@SuppressWarnings("deprecation")
public class IPTokenBasedRememberMeServices extends
		TokenBasedRememberMeServices {
	private ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

	@Override
	protected String makeTokenSignature(long tokenExpireTime, String userName,
			String password) {
		return DigestUtils.md5DigestAsHex((userName + ":" + tokenExpireTime
				+ ":" + password + ":" + getUserIPAddress(getContext()))
				.getBytes());
	}

	@Override
	public void onLoginSuccess(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			Authentication authentication) {
		try {
			setContext(httpServletRequest);
			super.onLoginSuccess(httpServletRequest, httpServletResponse,
					authentication);
		} catch (Exception e) {
			setContext(null);
		}
	}

	@Override
	protected UserDetails processAutoLoginCookie(String[] tokens,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		int length = tokens.length;
		String ipAddressToken = tokens[length - 1];
		if (!getUserIPAddress(httpServletRequest).equalsIgnoreCase(
				ipAddressToken)) {
			throw new InvalidCookieException(
					"Cookie IP address did not contain a matching IP(contained:"
							+ ipAddressToken + ")");
		}

		return super.processAutoLoginCookie(tokens, httpServletRequest,
				httpServletResponse);
	}

	@Override
	protected void setCookie(String[] tokens, int maxAge,
			HttpServletRequest request, HttpServletResponse response) {
		String[] tokensWithIPAddress = Arrays.copyOf(tokens, tokens.length + 1);
		tokensWithIPAddress[tokensWithIPAddress.length - 1] = getUserIPAddress(getContext());
		super.setCookie(tokensWithIPAddress, maxAge, request, response);
	}

	public String getUserIPAddress(HttpServletRequest httpServletRequest) {
		String ipStr = httpServletRequest.getRemoteAddr();
		return ipStr;
	}

	public HttpServletRequest getContext() {
		return requestHolder.get();
	}

	public void setContext(HttpServletRequest requestContext) {
		this.requestHolder.set(requestContext);
	}

}
