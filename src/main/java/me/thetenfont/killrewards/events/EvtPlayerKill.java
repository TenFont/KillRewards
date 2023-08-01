package me.thetenfont.killrewards.events;

import lombok.RequiredArgsConstructor;
import me.thetenfont.killrewards.KillRewards;
import me.thetenfont.killrewards.managers.KillsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@RequiredArgsConstructor
public class EvtPlayerKill implements Listener {
    final KillRewards killRewards;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        KillsManager killsManager = killRewards.getKillsManager();

        Player player = event.getPlayer();
        killsManager.setKillStreak(player, 0);
        if (player.getKiller() != null) {
            Player killer = player.getKiller();
            int kills = killsManager.getKills(killer) + 1;
            killsManager.setKills(killer, kills);
            int killstreak = killsManager.getKillStreak(killer) + 1;
            killsManager.setKillStreak(killer, killstreak);
            killRewards.getRewardsManager().checkRewards(killer, kills, killstreak);
        }
    }
}
