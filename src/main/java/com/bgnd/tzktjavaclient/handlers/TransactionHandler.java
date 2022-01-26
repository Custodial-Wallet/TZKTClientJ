package com.bgnd.tzktjavaclient.handlers;


import com.bgnd.tzktjavaclient.messages.TransactionMessage;
import com.bgnd.tzktjavaclient.model.Tx;

public abstract class TransactionHandler extends OperationHandler<Tx, TransactionMessage> {

    // Constructor

    public TransactionHandler() {
        super(TransactionMessage.class);
    }
}
