package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/home")
@Controller
public class HomeController {
    private ClientService clientService;

    public HomeController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public String getView(HttpSession session){
        return "home";
    }

    @PostMapping("/entry")
    public String processEntry(HttpSession session){
        System.out.println("posttayim");
        Client client = null;
        if(session.getAttribute("client") != null){
            client = (Client) session.getAttribute("client");
            client = clientService.findClientByMail(client.getMail());
            System.out.println("Home page: " + client);
            session.setAttribute("client", client);
        }
        return  "forward:/home/main";
    }

    @PostMapping("/main")
    public String processMain(HttpSession session){
        System.out.println("Maindeyim: " + (Client) session.getAttribute("client"));
        return "home";
    }

}
