package me.hughjph.deathmatchgame.gamemode;

import org.bukkit.entity.Player;

public class PlayerStats {


    Player player;
    int Kills = 0;
    int Deaths = 0;

    public PlayerStats(Player player){

    }
    
    public Integer getKills(){
        return Kills;
    }
    
    public Integer getDeaths(){
        return Deaths;
    }
    
    public void increaseKills(){
        Kills++;
    }

    public void increaseDeaths(){
        Deaths++;
    }



}
