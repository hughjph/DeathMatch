package me.hughjph.deathmatchgame.commands;

import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartDeathMatchLobby implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!(sender.hasPermission("deathmatch.startdeathmatch"))) return false;

        Player player = (Player) sender;

        player.sendMessage("New death match lobby create, use /invite <player> to invite your friends");

        Lobby lobby = new Lobby(player);

        return true;
    }
}
