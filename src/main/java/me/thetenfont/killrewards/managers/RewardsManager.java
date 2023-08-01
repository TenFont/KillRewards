package me.thetenfont.killrewards.managers;

import lombok.RequiredArgsConstructor;
import me.thetenfont.killrewards.KillRewards;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class RewardsManager {
    private final KillRewards killRewards;
    Pattern rewardPattern = Pattern.compile("(\\w+) *([=%<>]) *(\\w+)");

    public void checkRewards(Player player, int kills, int killstreak) {
        rewards : for (Map<?, ?> map : killRewards.getConfig().getMapList("kill_rewards")) {
            ArrayList<String> conditions = (ArrayList<String>) map.get("conditions");
            for (String condition : conditions)
                if (!doesConditionMatch(condition, kills, killstreak)) continue rewards;
            String command = (String) map.get("command");
            command = command.replaceAll("%player%", player.getName());
            command = command.replaceAll("%kills%", String.valueOf(kills));
            command = command.replaceAll("%killstreak%", String.valueOf(killstreak));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }

    private boolean doesConditionMatch(String condition, int kills, int killstreak) {
        Matcher matcher = rewardPattern.matcher(condition);
        if (!matcher.find()) return false;
        String expressionString = matcher.group(1);
        if (!expressionString.equals("kills") && !expressionString.equals("killstreak")) return false;
        int expression = switch (expressionString) {
            case "kills" -> kills;
            case "killstreak" -> killstreak;
            default -> 0;
        };
        String valueString = matcher.group(3);
        if (!valueString.matches("\\d+")) return false;
        int value = Integer.parseInt(valueString);
        String operation = matcher.group(2);
        return switch (operation) {
            case "=" -> expression == value;
            case "%" -> expression % value == 0;
            case ">" -> expression > value;
            case "<" -> expression < value;
            default -> false;
        };
    }
}