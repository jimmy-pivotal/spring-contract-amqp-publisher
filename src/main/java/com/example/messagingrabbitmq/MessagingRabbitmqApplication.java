package com.example.messagingrabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;

@SpringBootApplication
public class MessagingRabbitmqApplication {

	static final String topicExchangeName = "spring-boot-exchange";

	@Bean
	public MessageConverter messageConverter() {
		final Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter(
				new ObjectMapper());
		jsonMessageConverter.setCreateMessageIds(true);
		final ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter(
				jsonMessageConverter);
		messageConverter.addDelegate(CONTENT_TYPE_JSON, jsonMessageConverter);
		return messageConverter;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
										 MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MessagingRabbitmqApplication.class, args).close();
	}

}
