package com.sebit.clientsystem.controller;

import com.sebit.clientsystem.entity.Client;
import com.sebit.clientsystem.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.ArrayList;

@RequestMapping("/sendForm")
@Controller
public class SendFormController {
    private ClientService clientService;

    public SendFormController(ClientService clientService){
        this.clientService = clientService;
    }
    private static Socket socket;
    @GetMapping
    public String getView(HttpSession session){
        return "sendForm";
    }
// Client client = (Client) session.getAttribute("client");
    @PostMapping("/sendMoney")
    public String processMain(HttpSession session){
       System.out.println("sendMoneydeyim: " + (Client) session.getAttribute("client"));
       sendTransaction();
       return "sendForm";
    }


    public boolean sendTransaction(){
        long startTime = System.nanoTime();
        int counter = 1;
        try {
            ArrayList<String> hostIps = new ArrayList<>();
            hostIps.add("192.168.10.103");
            hostIps.add("192.168.10.213");
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
                    s = "Gunes : Turan : VCloud :" + 10;

                    String sendMessage = s + "\n";
                    for(int j = 0; j < sockets.size(); j++){
                        BufferedWriter bw = bufferedWriters.get(j);
                        System.out.println("Message sent to the server : "+sendMessage); //
                        BufferedReader br = new BufferedReader(inputStreamReaders.get(j));
                        Thread t = new ConnectionThreadController(bw, br, sendMessage);
                        t.start();
                        threads.add(t);
                    }
                    for(int k = 0; k < threads.size(); k++){
                        threads.get(k).join();
                    }

                if (s.equals( "STOP")) //verify
                    break;
                System.out.println("Enter a string");
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(totalTime);
            }
        } catch (Exception exception) { exception.printStackTrace(); }
        finally { //Closing the socket
            try {
                socket.close();
            } catch(Exception e) { e.printStackTrace(); }
        }
        return true;
    }
}
