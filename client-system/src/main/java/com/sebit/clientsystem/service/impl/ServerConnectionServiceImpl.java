package com.sebit.clientsystem.service.impl;

import com.sebit.clientsystem.service.ServerConnectionService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class ServerConnectionServiceImpl implements ServerConnectionService {
    private static Socket socket;
    private String receivedMessage;

    @Override
    public boolean sendMessage(String message) {
        long startTime = System.nanoTime();
        int counter = 1;
        try {
            ArrayList<String> hostIps = new ArrayList<>();
            hostIps.add("172.20.10.2"); //mainServer
            if(!(message.contains("getBalance") || message.contains("getTransactions"))){
                hostIps.add("172.20.10.6");
            }
            //String host = "192.168.10.105";//"192.168.10.48";
            int port = 5025;
            ArrayList<Socket> sockets = new ArrayList<>();
            ArrayList<OutputStream> outputStreams = new ArrayList<>();
            ArrayList<OutputStreamWriter> outputStreamWriters = new ArrayList<>();
            ArrayList<BufferedWriter> bufferedWriters = new ArrayList<>();
            ArrayList<Scanner> scanners = new ArrayList<>();
            ArrayList<InputStream> inputStreams = new ArrayList<>();
            ArrayList<InputStreamReader> inputStreamReaders = new ArrayList<>();
            System.out.println("Şimdi Deniyorum Bağlanmayı");
            //InetAddress address = InetAddress.getByName(host);
            for(int i = 0; i < hostIps.size(); i++){
                socket = new Socket(hostIps.get(i), port);
                System.out.println("Bağlandı sunucuya");

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                Scanner in = new Scanner(System.in);
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                sockets.add(socket);
                outputStreamWriters.add(osw);
                outputStreams.add(os);
                bufferedWriters.add(bw);
                scanners.add(in);
                inputStreams.add(is);
                inputStreamReaders.add(isr);
            }
            System.out.println("String kontrol");

            String s = "";
            ArrayList<Thread> threads = new ArrayList<>();
            while(!s.equals("END")) {
                //verify the syntax
                s = message;//sender + " : " + receiver + " : VCloud :" + amount;

                String sendMessage = s + "\n";
                for(int j = 0; j < sockets.size(); j++){
                    BufferedWriter bw = bufferedWriters.get(j);
                    System.out.println("Message sent to the server : "+sendMessage); //
                    BufferedReader br = new BufferedReader(inputStreamReaders.get(j));
                    Thread t = new ConnectionThreadController(bw, br, sendMessage, this);
                    t.start();
                    threads.add(t);
                }
                for(int k = 0; k < threads.size(); k++){
                    threads.get(k).join();
                }

                if (s.equals( "STOP")) //verify
                    break;
                //System.out.println("Enter a string");
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Elapsed Time: " + totalTime);
                s = "END"; //Rezillik
            }
        } catch (Exception exception) { exception.printStackTrace(); }
        finally { //Closing the socket
            try {
                socket.close();
            } catch(Exception e) { e.printStackTrace(); }
        }
        return true;
    }

    @Override
    public String getReceivedMessage() {
        return receivedMessage;
    }

    @Override
    public void setReceivedMessage(String message) {
        receivedMessage = message;
    }
}

