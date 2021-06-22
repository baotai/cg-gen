package com.cg.apollo;

import com.bestpay.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig(value = "technology.school", order = 11)
public class AnotherAppConfig {
}
