package me.hughjph.deathmatchgame.commands.PartySubCommands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Invite {


    public static boolean invite(Player player, String[] players) {

        players = Arrays.copyOfRange(players, 1, players.length);


        if(players.length == 0){
            player.sendMessage("You need to have at least one player invited");
        }

        Lobby lobby = DeathMatchGame.playersInLobbies.get(player);

        if(!(lobby.getInvitePerm(player))) {
            player.sendMessage(ChatColor.RED + "You do not have permission to invite players!");
        }

        List<Player> invitedPlayers = new ArrayList<>();


        for(String name : players){
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
                        DeathMatchGame.invitedPlayers.remove(invitedPlayer);
                    }
                }
                player.sendMessage("Pending invites have expired");
            }

        }.runTaskLater(DeathMatchGame.getPlugin(DeathMatchGame.class), 20*60);


        return true;
    }
}
