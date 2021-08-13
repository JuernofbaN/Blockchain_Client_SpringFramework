package com.sebit.clientsystem.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class ConnectionThreadController extends Thread{
    BufferedWriter bw;
    Socket s;
    BufferedReader br;
    String data;
    public ConnectionThreadController( BufferedWriter bw, BufferedReader br, String data){
        this.bw = bw;
        this.br = br;
        this.data = data;
    }

    @Override
    public void run() {
        super.run();
        try {
            bw.write(data);
            bw.flush();
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
