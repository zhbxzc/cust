package com.bincai.cloud.cust.domain.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.Sampler;
/*import org.springframework.cloud.sleuth.Sampler;*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
* @ClassName: UserApplication 
* @Description: 启动客户微服务 
* @author 张海滨
* @date 2016年8月9日 下午2:07:23 
*
 */
@MapperScan("com.bincai.cloud.cust.domain.dao")
@ComponentScan("com.bincai.cloud.cust.domain")
@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CustApplication
{
	@Bean
	Sampler sampler()
	{
		return span -> true;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(CustApplication.class, args);
	}
}
