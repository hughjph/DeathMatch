package me.hughjph.deathmatchgame.commands;

import me.hughjph.deathmatchgame.DeathMatchGame;
import me.hughjph.deathmatchgame.gamemode.LeaderBoard;
import me.hughjph.deathmatchgame.gamemode.Lobby;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        lobby.setLobbyIndex(DeathMatchGame.worldIndex);
        DeathMatchGame.worldIndex++;

        creator.environment(World.Environment.NORMAL);

        World world = creator.createWorld();

        world.getWorldBorder().setCenter(0, 0);
        world.getWorldBorder().setSize(200);
        world.getWorldBorder().setDamageAmount(20);

        world.getSpawnLocation();

        ItemStack infinityBow = new ItemStack(Material.BOW);
        infinityBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        for(Player lobbyPlayer : lobby.getPlayers()){
            lobbyPlayer.teleport(world.getSpawnLocation());
            lobbyPlayer.setGameMode(GameMode.SURVIVAL);
            DeathMatchGame.playersInLobbies.remove(lobbyPlayer);
            DeathMatchGame.playersInGames.put(lobbyPlayer, lobby);
            DeathMatchGame.playerInventories.put(player, player.getInventory());
            player.getInventory().clear();

            player.setMaxHealth(20);
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD), infinityBow, new ItemStack(Material.ARROW, 64));
            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));

        }

        lobby.setDeathMatchArena(world);
        LeaderBoard.createDeathMatchScoredboard(lobby);
        startTimer(lobby);

        return false;
    }
}
