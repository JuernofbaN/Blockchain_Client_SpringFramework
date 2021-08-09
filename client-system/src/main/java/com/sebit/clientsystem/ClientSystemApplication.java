package com.sebit.clientsystem;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ClientSystemApplication.class, args);
    }
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        Client client2 = new Client("Gunes", "Yasamkent", "gunes@hotmail.com","password123");
        clientRepository.save(client2);
        Client client3 = new Client("Talha", "Kecioren", "talha@hotmail.com","pw1234");
        clientRepository.save(client3);
    }
}
