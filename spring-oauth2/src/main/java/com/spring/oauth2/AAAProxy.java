package com.spring.oauth2;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class AAAProxy {
	private static final Logger logger = LoggerFactory.getLogger(AAAProxy.class);
	
	private Proxy proxy;
	private RestTemplate template;

	public AAAProxy() {
		logger.info("------- In a AAAProxy() constructor --------");
		proxy = new Proxy(Type.HTTP, new InetSocketAddress("proxy.abc.net", 3001));
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setProxy(proxy);
		template = new RestTemplate(requestFactory);
	}

	public boolean isValidUser(String user, String password) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("user", user);
		map.add("password", password);

		HttpEntity<String> response = template.postForEntity("https://authentication.local/auth", map,String.class);
		//HttpEntity<String> response = template.postForEntity("https://localhost:8000/spring-oauth2/auth", map,String.class);
		HttpHeaders headers = response.getHeaders();
		List<String> cookies = headers.get("Set-Cookie");

		for (String cookie : cookies) {
			if (cookie.indexOf("Auth")!= -1)
				return true;
		}

		return false;
	}
}
