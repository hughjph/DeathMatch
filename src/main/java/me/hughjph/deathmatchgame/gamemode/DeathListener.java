package me.hughjph.deathmatchgame.gamemode;

import me.hughjph.deathmatchgame.DeathMatchGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event){
        if(!DeathMatchGame.playersInGames.containsKey(event.getEntity())) return;

        Player deadPlayer = event.getEntity();
        Player killer = deadPlayer.getKiller();





    }


}
