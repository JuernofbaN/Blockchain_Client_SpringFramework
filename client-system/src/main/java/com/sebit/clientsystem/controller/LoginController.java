package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.repository.ClientRepository;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private ClientService clientService;

    public LoginController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public String getView(Model model){
    model.addAttribute("mail", "");
        return "login";
    }

    @PostMapping
    public void post(String mail){
        //clientService.hasUserPasswordCombo(client.getMail(), client.getPassword());

        System.out.println(mail);
    }
}
