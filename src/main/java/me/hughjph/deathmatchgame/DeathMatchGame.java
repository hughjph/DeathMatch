package me.hughjph.deathmatchgame;

import me.hughjph.deathmatchgame.gamemode.LeaderBoard;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathMatchGame extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Starting Death Match plugin!");
        LeaderBoard.loadScoreboard();

        getServer().getPluginManager().registerEvents(new Listener(), this);

    }

    @Override
    public void onDisable() {

    }
}
