package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.entity.Transaction;
import com.sebit.clientsystem.service.ClientService;
import com.sebit.clientsystem.service.ServerConnectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("/sendForm")
@Controller
public class SendFormController {
    private ClientService clientService;
    private ServerConnectionService serverConnectionService;

    public SendFormController(ClientService clientService, ServerConnectionService serverConnectionService){
        this.clientService = clientService;
        this.serverConnectionService = serverConnectionService;
    }

    @GetMapping
    public String getView(HttpSession session){
        return "sendForm";
    }
// Client client = (Client) session.getAttribute("client");
    @PostMapping("/sendMoney")
    public String processMain(HttpSession session, @ModelAttribute(name = "newTransaction") Transaction newTransaction){
       System.out.println("sendMoneydeyim: " + (Client) session.getAttribute("client"));
       sendTransaction(((Client) session.getAttribute("client")).getMail(), newTransaction.getReceiver(), newTransaction.getAmount());
       return "sendForm";
    }


    public boolean sendTransaction(String sender, String receiver, int amount){
        return serverConnectionService.sendMessage(sender + " : " + receiver + " : VCloud :" + amount);
    }
}
