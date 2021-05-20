package me.hughjph.deathmatchgame.gamemode;

import me.hughjph.deathmatchgame.DeathMatchGame;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

public class DeathMatchTimer {

    public static void startTimer(Lobby lobby){

        new BukkitRunnable() {

            @Override
            public void run(){
                List<Player> players = lobby.getPlayers();

                SortedMap<Player, Integer>  playerKills = lobby.getPlayerKills(true);

                Player number1 = null;
                Player number2 = null;
                Player number3 = null;

                for(Player player: lobby.getPlayers()){
                    Integer numberOfKills = playerKills.get(player);

                    if(number1 == null){
                        number1 = player;
                    } else if(number2 == null){
                        number2 = player;
                    } else if(number3 == null){
                        number3 = player;
                    }

                    if(numberOfKills > playerKills.get(number1)){
                        number3 = number2;
                        number2 = number1;
                        number1 = player;
                    } else if(numberOfKills > playerKills.get(number2)){
                        number3 = number2;
                        number2 = player;
                    } else if(numberOfKills > playerKills.get(number3)){
                        number3 = player;
                    }
                }


                for(Player player : players){

                    if (player == number1) {
                        player.sendMessage("You WIN!");
                    } else if(player == number2){
                        player.sendMessage("2nd Place!");
                    } else if(player == number3){
                        player.sendMessage("3rd Place!");
                    } else{
                        player.sendMessage("GAME OVER!");
                    }

                    player.setGameMode(GameMode.SPECTATOR);

                    DeathMatchGame.playersInGames.remove(player);
                    DeathMatchGame.playersInLobbies.put(player, lobby);
                }

            }
        }.runTaskLater(DeathMatchGame.getPlugin(DeathMatchGame.class), 20*60*5);

    }


}
