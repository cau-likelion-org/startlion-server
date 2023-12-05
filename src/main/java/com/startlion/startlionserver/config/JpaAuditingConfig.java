package com.startlion.startlionserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화 -> baseTimeEntity 사용 가능
public class JpaAuditingConfig {
}
