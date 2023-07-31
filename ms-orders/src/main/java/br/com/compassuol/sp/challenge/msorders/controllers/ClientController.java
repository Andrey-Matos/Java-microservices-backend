
package br.com.compassuol.sp.challenge.msorders.controllers;

import br.com.compassuol.sp.challenge.msorders.config.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;

@RestController
public class ClientController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String checkProduct (Integer message) {
         byte[] convertedId = BigInteger.valueOf(message).toByteArray();

        Message newMessage = MessageBuilder.withBody(convertedId).build();
        Message result = rabbitTemplate.sendAndReceive(RabbitMqConfig.RPC_EXCHANGE, RabbitMqConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            String correlationId = newMessage.getMessageProperties().getCorrelationId();

            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        return response;
    }
}