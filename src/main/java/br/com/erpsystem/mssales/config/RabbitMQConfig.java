package br.com.erpsystem.mssales.config;

import br.com.erpsystem.mssales.constants.RabbitMQConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue comercialQueue(){

        return QueueBuilder.durable(RabbitMQConstants.COMERCIAL_QUEUE).build();
    }

    @Bean
    public TopicExchange comercialExchange(){
        return ExchangeBuilder.topicExchange(RabbitMQConstants.COMERCIAL_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding comercialBinding(){
        return BindingBuilder.bind(comercialQueue()).to(comercialExchange()).with(RabbitMQConstants.COMERCIAL_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate rabbitTemplateConfig(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setUseDirectReplyToContainer(false);
        return rabbitTemplate;
    }
}
