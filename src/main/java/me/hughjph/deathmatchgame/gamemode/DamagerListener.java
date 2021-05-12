package me.hughjph.deathmatchgame.gamemode;

import me.hughjph.deathmatchgame.DeathMatchGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamagerListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if(!DeathMatchGame.playersInGames.containsKey(player)) return;

        if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
            event.setCancelled(true);
        }

    }


}
