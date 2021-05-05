package me.hughjph.deathmatchgame;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("Welcome to the Death Match Server");
    }
}
