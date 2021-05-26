package me.hughjph.deathmatchgame.gamemode;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public class Lobby {

    Player leader;
    List<Player> players;
    List<Player> playersWhoCanInvite;
    SortedMap<Player, Integer> playerKills;
    World deathMatchArena;
    long lobbyIndex;
    Scoreboard scoreboard;

    public Lobby(Player player){

        players = new ArrayList<>();
        leader = player;
        players.add(player);
    }

    public SortedMap<Player, Integer> getPlayerKills(boolean ordered){
        return playerKills;
    }

    public World getDeathMatchArena(){
        return deathMatchArena;
    }
    public void setDeathMatchArena(World world) {
        deathMatchArena = world;
    }


    public void addPlayer(Player player){
        players.add(player);
    }

    public long getLobbyIndex(){
        return lobbyIndex;
    }

    public void setLobbyIndex(long index){
        lobbyIndex = index;
    }

    public void removePlayer(Player player){
        if(players.contains(player)){
            players.remove(player);
        }
    }

    public List<Player> getPlayers(){
        return players;
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
