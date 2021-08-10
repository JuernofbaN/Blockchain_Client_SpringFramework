package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/register")
@Controller
public class RegisterController {
    private ClientService clientService;

    public RegisterController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public String getView(){
        return "register";
    }

    @PostMapping
    public String processRegister(@ModelAttribute(name = "newClient") Client newClient, Model model){
        System.out.println("register: " + newClient);

        if( clientService.findClientByMail(newClient.getMail()) != null){
            model.addAttribute("invalidRegister", true);
            return "register";
        }else{
            clientService.addClient(newClient);
            return "redirect:/login";
        }
    }
}
