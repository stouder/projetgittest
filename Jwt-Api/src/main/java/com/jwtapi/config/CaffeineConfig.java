package com.jwtapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CaffeineConfig {

	@Value("${spring.cache.caffeine.spec}")
	private String cacheSpec;

	@Value("${spring.cache.cache-names}")
	private String cacheName;

	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager(cacheName);
		cacheManager.setCaffeine(caffeineCacheBuilder());

		return cacheManager;
	}

	Caffeine<Object, Object> caffeineCacheBuilder() {
		return Caffeine.from(cacheSpec).recordStats();
	}

}
