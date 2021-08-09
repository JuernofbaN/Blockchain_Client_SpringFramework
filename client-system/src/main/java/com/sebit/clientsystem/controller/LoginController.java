package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.repository.ClientRepository;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    private ClientService clientService;

    public LoginController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public String getView(){
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute(name = "client") Client client, Model model){
        String username = client.getMail();
        String password = client.getPassword();
        System.out.println(username);
        System.out.println(password);
        System.out.println(client.toString());
        if(clientService.hasUserPasswordCombo(client.getMail(),client.getPassword())){
            System.out.println("Böyle bir kullanıcı vardır.");
            return "login";
        }
        model.addAttribute("invalidLogin", true);
        return "login";
    }
}
