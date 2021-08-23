package com.sebit.clientsystem.service;

public interface ServerConnectionService {
    boolean sendMessage(String message);
    String getReceivedMessage();
    void setReceivedMessage(String message);
}
