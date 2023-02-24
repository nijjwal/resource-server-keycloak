package com.nijjwal.userstoragespi;

import java.util.HashMap;

public class HashMapUserStore {

    private HashMap<String, User> hashMapStorage;

    public HashMapUserStore(){
        this.hashMapStorage = new HashMap<>(3);
        this.hashMapStorage.put("kevin", new User("kevin", "banana@123"));
        this.hashMapStorage.put("stuart", new User("kevin", "banana@123"));
    }

    public User getUser(String username){
        return this.hashMapStorage.get(username);
    }
}
