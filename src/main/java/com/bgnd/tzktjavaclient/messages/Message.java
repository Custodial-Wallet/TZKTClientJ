package com.bgnd.tzktjavaclient.messages;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Message<T> {

    // Variables

    protected Integer type;
    protected Integer state;
    protected ArrayList<T> data;

}
