package me.hughjph.deathmatchgame.commands.PartySubCommands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class List {

    public static void showList(Player sender){
        Lobby lobby = DeathMatchGame.playersInLobbies.get(sender);

        sender.sendMessage(ChatColor.GOLD + "Players in your party: ");
        sender.sendMessage(lobby.getPlayerList() + " ");
    }
}
