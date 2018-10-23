package com.example.springbootplay.configuration;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Tony Ng on 23/10/2018.
 */
@Configuration
@MapperScan("com.example.springbootplay.dao")
@ConditionalOnProperty(name = "mybatis-plus.enable-performance-interceptor", havingValue = "true")
public class MybatisPlusConfig
{
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}
}
