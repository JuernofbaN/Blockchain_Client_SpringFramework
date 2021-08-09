package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String listClients(Model model){
        model.addAttribute("clients", clientService.getAllStudents());
        return "clients";
    }
}
