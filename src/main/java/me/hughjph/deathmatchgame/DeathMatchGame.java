package me.hughjph.deathmatchgame;

import me.hughjph.deathmatchgame.commands.AcceptMatch;
import me.hughjph.deathmatchgame.commands.Party;
import me.hughjph.deathmatchgame.commands.StartDeathMatch;
import me.hughjph.deathmatchgame.commands.StartDeathMatchLobby;
import me.hughjph.deathmatchgame.gamemode.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class DeathMatchGame extends JavaPlugin {

    public static long worldIndex = 0000;

    //keeps track of the lobbies and their leaders
    public static HashMap<Player, Lobby> lobbies = new HashMap<>();
    //keeps track of the players who are invited to a lobby
    public static HashMap<Player, Lobby> invitedPlayers = new HashMap<>();
    //keeps track of the players and the lobbies they're in
    public static HashMap<Player, Lobby> playersInLobbies = new HashMap<>();
    //keeps track of players that are currently in a deathmatch
    public static HashMap<Player, Lobby> playersInGames = new HashMap<>();
    //main world name
    public static String mainWorld = "world";
    //keeps track of player inventories while they are in game
    public static HashMap<Player, PlayerInventory> playerInventories = new HashMap<>();




    @Override
    public void onEnable() {

        System.out.println("Starting Death Match plugin!");
        LeaderBoard.loadScoreboard();

        getServer().getPluginManager().registerEvents(new Listener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new DamagerListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);


        this.saveDefaultConfig();

        loadConfig();
        registerCommands();

    }



    public void registerCommands(){

        getCommand("startlobby").setExecutor(new StartDeathMatchLobby());
        getCommand("startgame").setExecutor(new StartDeathMatch());
        getCommand("join").setExecutor(new AcceptMatch());
        getCommand("party").setExecutor(new Party());

    }


    public void loadConfig(){
        mainWorld = (String) this.getConfig().get("mainworld");

    }

}
