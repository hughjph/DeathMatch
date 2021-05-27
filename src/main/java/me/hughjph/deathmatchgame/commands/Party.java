package me.hughjph.deathmatchgame.commands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.hughjph.deathmatchgame.commands.PartySubCommands.Help.showHelp;
import static me.hughjph.deathmatchgame.commands.PartySubCommands.Invite.invite;
import static me.hughjph.deathmatchgame.commands.PartySubCommands.Leave.leaveParty;
import static me.hughjph.deathmatchgame.commands.PartySubCommands.List.showList;


public class Party implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(! (sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(!(DeathMatchGame.playersInLobbies.containsKey(player) || DeathMatchGame.playersInGames.containsKey(player))){
            player.sendMessage("You are not in a party!");
        }


        if(args.length == 0){
            showHelp(player);
            return true;
        }

        String subCommand = args[0];

        if(subCommand.equalsIgnoreCase("list")){
            showList(player);
        } else if (subCommand.equalsIgnoreCase("invite")){
            invite(player, args);
        } else if (subCommand.equalsIgnoreCase("leave")){
            leaveParty(player);
        }

        return true;
    }


}
