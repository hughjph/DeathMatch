package me.hughjph.deathmatchgame;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.hughjph.deathmatchgame.gamemode.LeaderBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().setScoreboard(LeaderBoard.scoreboard);
        System.out.println("Scoreboard Shown");
        event.getPlayer().sendMessage("Scoreboard Loaded");
    }
}
