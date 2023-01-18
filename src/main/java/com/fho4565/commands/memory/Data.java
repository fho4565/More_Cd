package com.fho4565.commands.memory;

import java.io.Serializable;
import java.util.HashMap;

public class Data implements Serializable {
    HashMap<String,String> data = new HashMap<>();
    public String getData(String key){
        return this.data.get(key);
    }
}
