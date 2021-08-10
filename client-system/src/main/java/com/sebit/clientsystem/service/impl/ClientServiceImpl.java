package com.sebit.clientsystem.service.impl;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.repository.ClientRepository;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllStudents() {
        return clientRepository.findAll();
    }

    @Override
    public Boolean hasUserPasswordCombo(String mail, String password) {
        List<Client> clients = clientRepository.findAll();

        for(Client client : clients){
            if(client.getMail().equals(mail) && client.getPassword().equals(password))
                return true;
        }

        return false;
    }

    @Override
    public Client findClientByMail(String mail) {
        List<Client> clients = clientRepository.findAll();

        for(Client client : clients){
            if(client.getMail().equals(mail))
                return client;
        }
        return null;
    }

    @Override
    public void addClient(Client client) {
        System.out.println("addClient: " + client);
        client.setWallet(client.getSHA256Hash(client.getMail()));
        clientRepository.save(client);
    }
}
