package com.example.startermod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.entity.player.Player;

public class playerhealth {
    private static final List<String> BODY_PARTS = List.of(
            "Head", "Torso", "Left Arm", "Right Arm", "Left Leg", "Right Leg"
    );
    private static final List<String> INJURY_TYPES = List.of(
            "Cuts", "Lacerations", "Bruises", "Burns", "Abrasions", "Sprains", "Fractures"
    );

    private static final Map<UUID, PlayerHealthData> playersById = new HashMap<>();

    // Each UUID gets its own health data the first time it is requested.
    public static PlayerHealthData getHealth(Player player) {
        return playersById.computeIfAbsent(player.getUUID(), id -> new PlayerHealthData());
    }

    public static void resetHealth(Player player) {
        playersById.put(player.getUUID(), new PlayerHealthData());
    }

    public static class PlayerHealthData {
        private int blood = 100;
        private final Map<String, Map<String, Boolean>> bodyParts = new HashMap<>();

        public PlayerHealthData() {
            for (String part : BODY_PARTS) {
                Map<String, Boolean> injuries = new HashMap<>();
                for (String injury : INJURY_TYPES) {
                    injuries.put(injury, false);
                }
                bodyParts.put(part, injuries);
            }
        }

        public int getBlood() {
            return blood;
        }

        public void setBlood(int blood) {
            this.blood = Math.max(0, Math.min(100, blood));
        }

        public Map<String, Boolean> getInjuries(String bodyPart) {
            return bodyParts.get(bodyPart);
        }
    }
}
