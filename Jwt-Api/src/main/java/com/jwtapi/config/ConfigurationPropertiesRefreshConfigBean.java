package com.jwtapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@RefreshScope
public class ConfigurationPropertiesRefreshConfigBean {

	@Value("${execution.time.aspect.date.format}")
	private String formatDate;

	@Value("${spring.cache.caffeine.spec}")
	private String cacheSpec;

	@Value("${spring.cache.cache-names}")
	private String cacheName;

	@Value("${app.cookie.expiration}")
	private long expirationcookie;

	@Value("${app.token.secret}")
	private String secret;

	@Value("${app.token.expiration}")
	private long expirationjwt;

}
