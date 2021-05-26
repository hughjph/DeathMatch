package me.hughjph.deathmatchgame.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;
import java.util.Set;

public class LeaderBoard {

    public static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    public static Scoreboard scoreboard;


    public static void loadScoreboard(){
        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("DeathMatch Scorboard", "test1", "DEATHMATCH");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score = objective.getScore(" AKLSJDSA ");
        score.setScore(3);
        Score score2 = objective.getScore(Bukkit.getOnlinePlayers().size() + "");
        score2.setScore(2);
        Score score3 = objective.getScore("NANMES");
        score3.setScore(1);
        Score score4 = objective.getScore("ldm;asld");
        score4.setScore(0);
    }


    public static void createDeathMatchScoredboard(Lobby lobby){

        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("deathmatch" + lobby.lobbyIndex, "kill people", "DeathMatch");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<Player> players = lobby.getPlayers();

        lobby.scoreboard = scoreboard;

        for(Player player : players) {
            Score score = objective.getScore(player.getName());
            score.setScore(lobby.playerKills.get(player));
            player.setScoreboard(scoreboard);
        }

    }

    public static void updateDeathMatchScoreboard(Lobby lobby, Player player){

        Scoreboard scoreboard = lobby.scoreboard;

        Objective objective = scoreboard.getObjective("deathmatch"+ lobby.lobbyIndex);

        Score score = objective.getScore(player.getName());
        score.setScore(lobby.playerKills.get(player));

    }


}
