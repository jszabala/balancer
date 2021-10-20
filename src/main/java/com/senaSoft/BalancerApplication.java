package com.senaSoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class BalancerApplication {

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping
	public String balanceador() {
		return restTemplate.getForObject("http://APISERVICE/", String.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BalancerApplication.class, args);
	}

}
