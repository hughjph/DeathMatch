package me.hughjph.deathmatchgame.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

import java.util.Set;

public class LeaderBoard {

    public static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    public static Scoreboard scoreboard;


    public static void loadScoreboard(){
        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test12345", "test1", "Name");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score = objective.getScore(" AKLSJDSA ");
        score.setScore(3);
        Score score2 = objective.getScore(Bukkit.getOnlinePlayers().size() + "");
        score2.setScore(2);
        Score score3 = objective.getScore("NANMES");
        score3.setScore(1);
        Score score4 = objective.getScore("ldm;asld");
        score4.setScore(0);



        /*Objective objective = scoreboard.registerNewObjective("test12345", "test1");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("NAMEEE");*/



    }



}
