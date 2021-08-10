package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/sendForm")
@Controller
public class SendFormController {
    private ClientService clientService;

    public SendFormController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public String getView(HttpSession session){
        return "sendForm";
    }

}
