package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.entity.Transaction;
import com.sebit.clientsystem.service.ServerConnectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/transactions")
@Controller
public class TransactionsController {
    ServerConnectionService serverConnectionService;

    public TransactionsController(ServerConnectionService serverConnectionService){
        this.serverConnectionService = serverConnectionService;
    }

    @GetMapping
    public String getView(HttpSession session, Model model){
        Client client = (Client) session.getAttribute("client");
        serverConnectionService.sendMessage("getTransactions:" + client.getMail());
        String receivedMessage = serverConnectionService.getReceivedMessage();
        String[] inputStrings = receivedMessage.split("/");


        List<Transaction> transactionList = new ArrayList<Transaction>();
        for(int i = 0; i < inputStrings.length - 1; i += 4){
            Transaction temp = new Transaction(inputStrings[i], inputStrings[i+1], inputStrings[i+2], inputStrings[i+3]);
            transactionList.add(temp);
        }
        model.addAttribute("transactions", transactionList);

        return "transactions";
    }
}
