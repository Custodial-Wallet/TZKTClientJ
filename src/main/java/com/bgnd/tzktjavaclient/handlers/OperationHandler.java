package com.bgnd.tzktjavaclient.handlers;

import com.bgnd.tzktjavaclient.messages.Message;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.microsoft.signalr.Action1;

import java.util.ArrayList;

public abstract class OperationHandler<T, K extends Message<T>> implements Action1<JsonElement> {

    // Variables

    private final Class<K> type;
    private final Gson gson;

    // Constructor

    public OperationHandler(Class<K> type) {
        this.gson = new Gson();
        this.type = type;
    }

    // Override

    @Override
    public final void invoke(JsonElement s) {
        final K message = gson.fromJson(s, type);

        if (message.getType() != 1)
            return;

        final ArrayList<T> data = message.getData();
        data.forEach(this::handleData);
    }

    // Public

    public final Class<JsonElement> getActionClass() {
        return JsonElement.class;
    }

    // Protected

    protected abstract void handleData(T data);
}
