package com.example.backendbp.service;

import com.example.backendbp.config.RabbitMQConfig;
import com.example.backendbp.entity.Cliente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendClienteMessage(Cliente cliente) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, cliente);
    }
}
