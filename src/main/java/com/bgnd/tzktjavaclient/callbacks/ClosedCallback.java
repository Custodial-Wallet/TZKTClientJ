package com.bgnd.tzktjavaclient.callbacks;

import com.bgnd.tzktjavaclient.Client;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.OnClosedCallback;

import java.util.function.BiConsumer;
import java.util.logging.Logger;


public class ClosedCallback implements OnClosedCallback {

    // Constants

    private static final Logger log = Logger.getLogger(ClosedCallback.class.getName());

    // Variables

    protected final HubConnection connection;
    protected final Client client;
    protected BiConsumer<HubConnection, Client> command;

    // Constructor

    public ClosedCallback(HubConnection connection, Client client, BiConsumer<HubConnection, Client> command) {
        this.connection = connection;
        this.client = client;
        this.command = command;
    }

    @Override
    public void invoke(Exception e) {
        log.info(e.getMessage());
        command.accept(connection, client);
    }
}
