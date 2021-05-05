package me.hughjph.deathmatchgame.commands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InviteToDeathMatch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!(DeathMatchGame.lobbies.containsKey((Player) sender))) return false;

        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage("You need to have at least one player invited");
        }

        Lobby lobby = DeathMatchGame.lobbies.get(player);

        List<Player> invitedPlayers = new ArrayList<>();


        for(String name : args){
            Player invitedPlayer = Bukkit.getPlayerExact(name);
            if(invitedPlayer != null){

                if(!DeathMatchGame.invitedPlayers.containsKey(invitedPlayer)) {
                    invitedPlayer.sendMessage("You have been invited to join " + player.getName() + "'s death match, type '/join' to join");
                    DeathMatchGame.invitedPlayers.put(invitedPlayer, lobby);
                    invitedPlayers.add(invitedPlayer);
                }else{
                    player.sendMessage("Player: " + invitedPlayer.getName() + " has already been invited to a game!");
                }
            } else{
                player.sendMessage("Player: " + name + " does not exist!");
            }
        }

        new BukkitRunnable(){

            @Override
            public void run(){
                for(Player invitedPlayer: invitedPlayers){
                    if(DeathMatchGame.invitedPlayers.containsKey(invitedPlayer)){
                        invitedPlayer.sendMessage("Invite has expired");
                    }
                }
                player.sendMessage("Pending invites have expired");
            }

        }.runTaskLater(DeathMatchGame.getPlugin(DeathMatchGame.class), 20*60);


        return true;
    }
}
