package me.thetenfont.killrewards.commands;

import lombok.RequiredArgsConstructor;
import me.thetenfont.killrewards.KillRewards;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class CmdKillRewards implements CommandExecutor {
    final KillRewards killRewards;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("§cInvalid usage. Correct usage is §e/killrewards reload");
        }
        else if (args[0].equals("reload")) {
            killRewards.reloadConfig();
            commandSender.sendMessage("§aSuccessfully reloaded the configuration file.");
            commandSender.playSound(Sound.sound(Key.key("block.note_block.pling"), Sound.Source.BLOCK, 1f, 1f));
        }
        return true;
    }
}
