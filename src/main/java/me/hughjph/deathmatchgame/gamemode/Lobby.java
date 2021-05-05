package me.hughjph.deathmatchgame.gamemode;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    Player leader;
    List<Player> players;



    public Lobby(Player player){

        players = new ArrayList<>();

        leader = player;
        players.add(player);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        if(players.contains(player)){
            players.remove(player);
        }
    }

}
