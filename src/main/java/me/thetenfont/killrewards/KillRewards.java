package me.thetenfont.killrewards;

import lombok.AccessLevel;
import lombok.Getter;
import me.thetenfont.killrewards.commands.CmdKillRewards;
import me.thetenfont.killrewards.events.EvtPlayerKill;
import me.thetenfont.killrewards.managers.KillsManager;
import me.thetenfont.killrewards.managers.RewardsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Getter
public final class KillRewards extends JavaPlugin {
    KillsManager killsManager;
    RewardsManager rewardsManager;
    @Getter(AccessLevel.NONE)
    File killsConfigFile;
    FileConfiguration killsConfig;

    public void saveKillsConfig() {
        try {
            killsConfig.save(killsConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        killsConfigFile = new File(getDataFolder(), "kills_registry.yml");
        killsConfig = YamlConfiguration.loadConfiguration(killsConfigFile);

        saveDefaultConfig();
        saveKillsConfig();

        killsManager = new KillsManager(this);
        rewardsManager = new RewardsManager(this);
        Objects.requireNonNull(getCommand("killrewards")).setExecutor(new CmdKillRewards(this));
        Bukkit.getPluginManager().registerEvents(new EvtPlayerKill(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
