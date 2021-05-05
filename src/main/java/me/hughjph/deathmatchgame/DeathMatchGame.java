package me.hughjph.deathmatchgame;

import me.hughjph.deathmatchgame.commands.AcceptMatch;
import me.hughjph.deathmatchgame.commands.InviteToDeathMatch;
import me.hughjph.deathmatchgame.commands.StartDeathMatch;
import me.hughjph.deathmatchgame.commands.StartDeathMatchLobby;
import me.hughjph.deathmatchgame.gamemode.LeaderBoard;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class DeathMatchGame extends JavaPlugin {

    public static HashMap<Player, Lobby> lobbies = new HashMap<>();
    public static HashMap<Player, Lobby> invitedPlayers = new HashMap<>();

    @Override
    public void onEnable() {

        System.out.println("Starting Death Match plugin!");
        LeaderBoard.loadScoreboard();

        getServer().getPluginManager().registerEvents(new Listener(), this);


        registerCommands();

    }



    public void registerCommands(){

        getCommand("startlobby").setExecutor(new StartDeathMatchLobby());
        getCommand("startgame").setExecutor(new StartDeathMatchLobby());
        getCommand("join").setExecutor(new AcceptMatch());
        getCommand("invite").setExecutor(new InviteToDeathMatch());

    }



}
