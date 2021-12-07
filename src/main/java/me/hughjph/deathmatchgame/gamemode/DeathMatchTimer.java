package me.hughjph.deathmatchgame.gamemode;

import me.hughjph.deathmatchgame.DeathMatchGame;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class DeathMatchTimer {

    public static void startTimer(Lobby lobby){

        new BukkitRunnable() {

            @Override
            public void run(){
                List<Player> players = lobby.getPlayers();

                HashMap<Player, Integer> playerKills = lobby.getPlayerKills();

                List<Player> playerArray = new ArrayList<>();

                List<Player> addedPlayers = new ArrayList<>();

                for(int i = 0; i < lobby.getPlayerKills().size(); i++){

                    Player newPlayer = null;

                    for(Player player : players){
                        if(!addedPlayers.contains(player)){
                            newPlayer = player;
                            if(lobby.getPlayerKills().get(player) > lobby.getPlayerKills().get(newPlayer)){
                                newPlayer = player;
                            }
                        }
                    }

                    addedPlayers.add(newPlayer);
                    playerArray.set(i, newPlayer);

                }

                for(Player player : players){

                    int position = playerArray.indexOf(player);
                    player.sendMessage("Your position was number " + position);

                    player.setGameMode(GameMode.SPECTATOR);

                    DeathMatchGame.playersInGames.remove(player);
                    DeathMatchGame.playersInLobbies.put(player, lobby);
                }

                startEndTimer(lobby);

            }
        }.runTaskLater(DeathMatchGame.getPlugin(DeathMatchGame.class), 20*5);

    }


    public static void startEndTimer(Lobby lobby){

        new BukkitRunnable(){
            @Override
            public void run(){
                List<Player> players = lobby.getPlayers();

                World mainWorld = Bukkit.getWorld(DeathMatchGame.mainWorld);

                for(Player player : players){
                    player.teleport(mainWorld.getSpawnLocation());
                    player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                    PlayerInventory inv = DeathMatchGame.playerInventories.get(player);

                    player.getInventory().clear();
                    player.getInventory().setContents(inv.getArmorContents());
                    player.getInventory().setContents(inv.getContents());
                    player.getInventory().setExtraContents(inv.getExtraContents());

                    DeathMatchGame.playerInventories.remove(player);
                }

                lobby.getDeathMatchArena().getWorldFolder().delete();
            }
        }.runTaskLater(DeathMatchGame.getPlugin(DeathMatchGame.class), 20*5);
    }
}
