package com.sebit.clientsystem.service;
import com.sebit.clientsystem.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllStudents();
    Boolean hasUserPasswordCombo(String mail, String password);
    Client findClientByMail(String mail);
    void addClient(Client client);
}
