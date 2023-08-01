# Kill Rewards
**Kill rewards** is an easy to use plugin that allows server admins to execute certain actions when a player reaches a certain **killstreak** or **number of kills**.

## Download
You can download the latest version of **Kill Rewards** [here](https://github.com/TenFont/KillRewards/releases/latest).

## API Usage
```java
// Obtaining an instance
KillRewards killRewards = (KillRewards) Bukkit.getPluginManager().getPlugin("KillRewards");

// Getting the number of kills a player has
Player player = ...;
int kills = killRewards.getKillsManager().getKills(player);

// Getting the kill streak of a player
Player player = ...;
int kills = killRewards.getKillsManager().getKillStreak(player);
```