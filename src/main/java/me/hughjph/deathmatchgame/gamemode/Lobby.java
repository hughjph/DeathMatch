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


    public String getPlayerList() {
        String output = "";

        for(Player player : players){
            output += player.getName();
            if(player == leader){
                output += "(L)";
            }
            output += ", ";
        }

        return output.substring(0, output.length() - 2);
    }

}
