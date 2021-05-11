package me.hughjph.deathmatchgame.gamemode;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    Player leader;
    List<Player> players;
    List<Player> playersWhoCanInvite;


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

    public void allowInvite(Player player){
        if(!(playersWhoCanInvite.contains(player))){
            playersWhoCanInvite.add(player);
        }
    }

    public void removeInvite(Player player){
        if(playersWhoCanInvite.contains(player)){
            playersWhoCanInvite.remove(player);
        }
    }

    public boolean getInvitePerm(Player player){
        if(playersWhoCanInvite.contains(player)){
            return true;
        }
        else return false;
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
