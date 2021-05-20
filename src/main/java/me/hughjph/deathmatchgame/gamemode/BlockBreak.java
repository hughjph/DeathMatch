package me.hughjph.deathmatchgame.gamemode;

import me.hughjph.deathmatchgame.DeathMatchGame;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(DeathMatchGame.playersInGames.containsKey(event.getPlayer())){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You can't break blocks during a game!");
        }

    }

}
