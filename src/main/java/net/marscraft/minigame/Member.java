package net.marscraft.minigame;

import net.minestom.server.entity.Player;

public class Member {

    private Player player;
    private int kills;

    public Member(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        this.kills = getKills() + 1;
    }
}
