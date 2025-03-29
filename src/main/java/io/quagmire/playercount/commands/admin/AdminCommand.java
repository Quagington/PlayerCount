package io.quagmire.playercount.commands.admin;

import io.quagmire.playercount.PlayerCountPlugin;
import io.quagmire.playercount.commands.PlayerCountCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class AdminCommand extends PlayerCountCommand {
  public AdminCommand(PlayerCountPlugin plugin, Command command, String[] args, CommandSender sender) {
    super(plugin, command, args, sender, plugin.getAdminCommandRegistry());
  }
}
