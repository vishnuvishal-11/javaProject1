package rabbitconfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    public static final String QUEUE = "rabbit_queue";
    public static final String QUEUE2 = "direct_queue";
    public static final String EXCHANGE = "rabbit_exchange";
    public static final String ROUTING_KEY = "rabbit_routingKey";

@Bean
public Queue queue() {
    return new Queue(QUEUE);
}
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("amq.topic");
    }

    @Bean
    public Binding binding(Queue queue , TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding binding1a(TopicExchange topic,
                             Queue queue2) {
        return BindingBuilder.bind(queue2)
                .to(topic)
                .with("rabbit");
    }
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
       // return  new MessagingMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
