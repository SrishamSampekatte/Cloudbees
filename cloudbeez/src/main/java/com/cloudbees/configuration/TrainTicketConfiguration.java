package com.cloudbees.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class TrainTicketConfiguration {

	public TrainTicketConfiguration() {
		System.out.println("This  Train congifuration");
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean
	public ViewResolver viewResolver() {

		return new InternalResourceViewResolver("/", ".jsp");
	}

}
