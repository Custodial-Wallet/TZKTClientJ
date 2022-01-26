package com.bgnd.tzktjavaclient.callbacks;

import com.bgnd.tzktjavaclient.Client;
import com.microsoft.signalr.OnClosedCallback;

import java.util.function.Consumer;
import java.util.logging.Logger;


public class ClosedCallback implements OnClosedCallback {

    // Constants

    private static final Logger log = Logger.getLogger(ClosedCallback.class.getName());

    // Variables

    protected final Client client;
    protected Consumer<Client> command;

    // Constructor

    public ClosedCallback(Client client, Consumer<Client> command) {
        this.client = client;
        this.command = command;
    }

    @Override
    public void invoke(Exception e) {
        log.info("Connection was closed! Trying to reconnect ...");
        command.accept(client);
    }
}
