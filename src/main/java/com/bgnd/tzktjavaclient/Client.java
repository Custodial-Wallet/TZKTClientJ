package com.bgnd.tzktjavaclient;

import com.bgnd.tzktjavaclient.callbacks.ClosedCallback;
import com.bgnd.tzktjavaclient.handlers.OperationHandler;
import com.bgnd.tzktjavaclient.handlers.TransactionHandler;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import io.reactivex.rxjava3.core.Completable;
import org.json.JSONObject;

import java.util.function.Consumer;
import java.util.logging.Logger;

public final class Client {

    // Constants

    private static final String OPERATIONS_HANDLER_TARGET = "operations";
    private static final String OPERATIONS_SUBSCRIPTION = "SubscribeToOperations";
    private static final Logger log = Logger.getLogger(Client.class.getName());

    // Variables

    private final HubConnection connection;

    // Constructor

    public Client(String url) {
        connection = HubConnectionBuilder.create(url).build();
    }

    // Public

    public void subscribeToAllTransactions() {
        final JSONObject args = new JSONObject();
        args.put("types", "transaction");

        connection.send(OPERATIONS_SUBSCRIPTION, args);
        log.info("Started to listen all transactions in Tezos blockchain");
    }

    public Completable startConnection() {
        return connection.start();
    }

    public void addOnClosedCommand(Consumer<Client> command) {
        final ClosedCallback callback = new ClosedCallback(this, command);

        connection.onClosed(callback);
    }

    public void addTransactionHandler(TransactionHandler handler) {
        addOperationHandler(handler);
    }

    // Private

    private void addOperationHandler(OperationHandler<?, ?> handler) {
        final Class<?> actionClass = handler.getActionClass();

        connection.on(OPERATIONS_HANDLER_TARGET, handler, actionClass);
    }
}
