package me.hughjph.deathmatchgame.commands.PartySubCommands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Leave {

    public static void leaveParty(Player player){
        if(DeathMatchGame.playersInGames.containsKey(player)){
            player.sendMessage("Leaving game!");
            Lobby lobby = DeathMatchGame.playersInGames.get(player);
            lobby.removePlayer(player);
            DeathMatchGame.playersInGames.remove(player);
            player.teleport(Bukkit.getWorld(DeathMatchGame.mainWorld).getSpawnLocation());
            for(Player gamePlayer : lobby.getPlayers()){
                gamePlayer.sendMessage(player.getName() + " has left the game!");
            }
        } else if(DeathMatchGame.playersInLobbies.containsKey(player)){
            player.sendMessage("Leaving party!");
            Lobby lobby = DeathMatchGame.playersInLobbies.get(player);
            lobby.removePlayer(player);
            DeathMatchGame.playersInLobbies.remove(player);
        }
    }

}
