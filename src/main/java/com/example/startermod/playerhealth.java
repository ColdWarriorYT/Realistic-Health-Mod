package com.example.startermod;
import java.util.HashMap;

public class playerhealth {
    public static final HashMap<String, Integer> health = new HashMap<>();
    public static final HashMap<String, Integer> bones = new HashMap<>();
    public static final HashMap<String, String> skin = new HashMap<>();
    
    static {
        health.put("Blood", 10);
    }
}