package me.thetenfont.killrewards.managers;

import lombok.RequiredArgsConstructor;
import me.thetenfont.killrewards.KillRewards;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class KillsManager {
    private final KillRewards killRewards;

    public int getKills(Player player) {
        return killRewards.getKillsConfig().getInt("kills." + player.getUniqueId());
    }

    public void setKills(Player player, int newKills) {
        killRewards.getKillsConfig().set("kills." + player.getUniqueId(), newKills);
        killRewards.saveKillsConfig();
    }

    public int getKillStreak(Player player) {
        return killRewards.getKillsConfig().getInt("killstreak." + player.getUniqueId());
    }

    public void setKillStreak(Player player, int newKillStreak) {
        killRewards.getKillsConfig().set("killstreak." + player.getUniqueId(), newKillStreak);
        killRewards.saveKillsConfig();
    }
}
