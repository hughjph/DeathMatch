package me.hughjph.deathmatchgame.gamemode;

import me.hughjph.deathmatchgame.DeathMatchGame;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event){
        if(!DeathMatchGame.playersInGames.containsKey(event.getEntity())) return;

        Player deadPlayer = event.getEntity();
        Player killer = deadPlayer.getKiller();

        LeaderBoard.updateDeathMatchScoreboard(DeathMatchGame.playersInGames.get(killer), killer);

        new BukkitRunnable(){
            int countdown = 5;

            @Override
            public void run() {
                deadPlayer.sendMessage(countdown + "");
                countdown--;

                if(countdown == 0){
                    deadPlayer.setGameMode(GameMode.ADVENTURE);
                }
            }
        }.runTaskTimer(DeathMatchGame.getPlugin(DeathMatchGame.class), 20, 5);



    }


}
