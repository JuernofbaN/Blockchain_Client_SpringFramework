package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.repository.ClientRepository;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
    public String login(@ModelAttribute(name = "client") Client client, Model model, HttpSession session){
        session.setAttribute("client", client);
        System.out.println(client);
        if(clientService.hasUserPasswordCombo(client.getMail(),client.getPassword())){
            System.out.println("Böyle bir kullanıcı vardır.");
            return "forward:/home/entry";
        }
        model.addAttribute("invalidLogin", true);
        return "login";
    }
}
