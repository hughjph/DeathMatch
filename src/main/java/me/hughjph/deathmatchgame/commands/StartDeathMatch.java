package me.hughjph.deathmatchgame.commands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.hughjph.deathmatchgame.gamemode.DeathMatchTimer.startTimer;

public class StartDeathMatch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(!(DeathMatchGame.lobbies.containsKey(player))) return false;

        Lobby lobby = DeathMatchGame.lobbies.get(player);

        WorldCreator creator = new WorldCreator("DeathmatchWorld" + DeathMatchGame.worldIndex);
        DeathMatchGame.worldIndex++;

        creator.environment(World.Environment.NORMAL);

        World world = creator.createWorld();

        world.getWorldBorder().setCenter(0, 0);
        world.getWorldBorder().setSize(200);
        world.getWorldBorder().setDamageAmount(20);

        world.getSpawnLocation();

        for(Player lobbyPlayer : lobby.getPlayers()){
            lobbyPlayer.teleport(world.getSpawnLocation());
            lobbyPlayer.setGameMode(GameMode.SPECTATOR);
            DeathMatchGame.playersInLobbies.remove(lobbyPlayer);
            DeathMatchGame.playersInGames.put(lobbyPlayer, lobby);
        }

        lobby.setDeathMatchArena(world);
        startTimer(lobby);

        return false;
    }
}
