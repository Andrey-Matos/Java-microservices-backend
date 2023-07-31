package br.com.compassuol.sp.challenge.msproducts.controllers;

import br.com.compassuol.sp.challenge.msproducts.config.RabbitMqConfig;
import br.com.compassuol.sp.challenge.msproducts.models.CustomMessage;
import br.com.compassuol.sp.challenge.msproducts.models.ProductEntity;
import br.com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import br.com.compassuol.sp.challenge.msproducts.services.ProductService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component
public class RpcServerController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ProductService service;

    @RabbitListener(queues = RabbitMqConfig.RPC_MESSAGE_QUEUE)
    public void process(Message message) {
        byte[] body = message.getBody();
        long id = new BigInteger(body).longValue();
        ProductEntity product = service.getProductById(id);

        Object response = product != null ? product : "invalid product";

        CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
        rabbitTemplate.convertSendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_REPLY_MESSAGE_QUEUE, response, correlationData);
    }
}