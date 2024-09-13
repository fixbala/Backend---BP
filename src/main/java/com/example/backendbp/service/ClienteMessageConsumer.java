package com.example.backendbp.service;

import com.example.backendbp.config.RabbitMQConfig;
import com.example.backendbp.entity.Cliente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteMessageConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveClienteMessage(Cliente cliente) {
        // Aquí puedes definir qué hacer con el mensaje recibido
        System.out.println("Mensaje recibido: " + cliente);
    }
}
