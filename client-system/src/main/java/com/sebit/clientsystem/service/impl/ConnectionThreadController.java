package com.sebit.clientsystem.service.impl;
import com.sebit.clientsystem.service.ServerConnectionService;
import com.sebit.clientsystem.service.impl.ServerConnectionServiceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class ConnectionThreadController extends Thread{
    BufferedWriter bw;
    Socket s;
    BufferedReader br;
    String data;
    ServerConnectionService serverConnectionService;
    public ConnectionThreadController( BufferedWriter bw, BufferedReader br, String data, ServerConnectionService serverConnectionService){
        this.bw = bw;
        this.br = br;
        this.data = data;
        this.serverConnectionService = serverConnectionService;
    }

    @Override
    public void run() {
        super.run();
        try {
            bw.write(data);
            bw.flush();
            String message = br.readLine();
            System.out.println("Message received from the server : " + message);
            serverConnectionService.setReceivedMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
