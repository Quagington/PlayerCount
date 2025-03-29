package io.quagmire.playercount.commands.player;

import io.quagmire.playercount.PlayerCountPlugin;
import io.quagmire.playercount.commands.PlayerCountCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class PlayerCommand extends PlayerCountCommand {
  public PlayerCommand(PlayerCountPlugin plugin, Command command, String[] args, CommandSender sender) {
    super(plugin, command, args, sender, plugin.getCommandRegistry());
  }
}
