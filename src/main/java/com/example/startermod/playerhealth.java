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
    private static final Map<String, Integer> BLOOD_LOSS_PER_SECOND = Map.of(
            "Cuts", 2,
            "Lacerations", 5,
            "Bruises", 0,
            "Burns", 0,
            "Abrasions", 1,
            "Sprains", 0,
            "Fractures", 0
    );

    private static final Map<UUID, PlayerHealthData> playersById = new HashMap<>();

    // Each UUID gets its own health data the first time it is requested.
    public static PlayerHealthData getHealth(Player player) {
        return playersById.computeIfAbsent(player.getUUID(), id -> new PlayerHealthData());
    }

    public static class PlayerHealthData {
        private int blood = 100;
        private final Map<String, Map<String, Boolean>> bodyParts = new HashMap<>();

        private PlayerHealthData() {
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

        private int calculateBloodLossPerSecond() {
            int bloodLoss = 0;
            for (Map<String, Boolean> injuries : bodyParts.values()) {
                for (Map.Entry<String, Boolean> injury : injuries.entrySet()) {
                    if (injury.getValue()) {
                        bloodLoss += BLOOD_LOSS_PER_SECOND.getOrDefault(injury.getKey(), 0);
                    }
                }
            }
            return bloodLoss;
        }

        public void setBlood(int blood) {
            this.blood = Math.max(0, Math.min(100, blood));
        }

        public void applyBloodLossOnTick(Player player) {
            if (player.level().isClientSide || player.tickCount % 20 != 0 || !player.isAlive()) {
                return;
            }

            setBlood(blood - calculateBloodLossPerSecond());
            if (blood == 0) {
                player.hurt(player.damageSources().generic(), Float.MAX_VALUE);
            }
        }

        public Map<String, Boolean> getInjuries(String bodyPart) {
            return bodyParts.get(bodyPart);
        }

    }
}
