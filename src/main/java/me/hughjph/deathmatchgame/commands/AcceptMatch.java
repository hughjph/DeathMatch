package me.hughjph.deathmatchgame.commands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AcceptMatch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(DeathMatchGame.invitedPlayers.containsKey(player)){

            Lobby lobby = DeathMatchGame.invitedPlayers.get(player);
            lobby.addPlayer(player);
            DeathMatchGame.playersInLobbies.put(player, lobby);
            player.sendMessage("You have been added to the lobby!");

        } else{
            player.sendMessage("You have not been invited to any games!");
        }



        return true;
    }
}
