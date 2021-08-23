package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.service.ClientService;
import com.sebit.clientsystem.service.ServerConnectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/home")
@Controller
public class HomeController {
    private ClientService clientService;
    private ServerConnectionService serverConnectionService;

    public HomeController(ClientService clientService, ServerConnectionService serverConnectionService){
        this.clientService = clientService;
        this.serverConnectionService = serverConnectionService;
    }

    @GetMapping
    public String getView(HttpSession session){
        return "home";
    }

    @PostMapping("/balance")
    public String getBalance(HttpSession session){
        Client client = (Client) session.getAttribute("client");
        serverConnectionService.sendMessage("getBalance:" + client.getMail());
        String receivedMessage = serverConnectionService.getReceivedMessage();
        session.setAttribute("balance", receivedMessage);
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
            session.setAttribute("balance", "?");
        }
        return  "forward:/home/main";
    }

    @PostMapping("/main")
    public String processMain(HttpSession session){
        System.out.println("Maindeyim: " + (Client) session.getAttribute("client"));
        return "home";
    }

}
