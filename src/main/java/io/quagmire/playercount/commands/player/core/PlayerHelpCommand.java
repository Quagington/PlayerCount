package io.quagmire.playercount.commands.player.core;

import io.quagmire.core.commands.help.HelpCommand;
import io.quagmire.playercount.PlayerCountPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PlayerHelpCommand extends HelpCommand<PlayerCountPlugin> {
  public PlayerHelpCommand(PlayerCountPlugin plugin, Command command, String[] args, CommandSender sender) {
    super(plugin, command, args, sender, plugin.getCommandRegistry());
  }
}
