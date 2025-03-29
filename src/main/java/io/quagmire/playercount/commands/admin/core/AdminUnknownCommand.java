package io.quagmire.playercount.commands.admin.core;

import io.quagmire.core.commands.CoreCommandRegistry;
import io.quagmire.core.commands.unknown.UnknownCommand;
import io.quagmire.playercount.PlayerCountPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AdminUnknownCommand extends UnknownCommand<PlayerCountPlugin> {
  public AdminUnknownCommand(PlayerCountPlugin plugin, Command command, String[] args, CommandSender sender) {
    super(plugin, command, args, sender, plugin.getAdminCommandRegistry());
  }

  @Override
  protected String getMessage() {
    CoreCommandRegistry<PlayerCountPlugin> registry = plugin.getCommandRegistry();
    String primaryColor = registry.getPrimaryColor();
    String secondaryColor = registry.getSecondaryColor();
    return primaryColor + "Unknown command provided. " + secondaryColor + "Use " + primaryColor + "/" + registry.getAlias() + " help" + secondaryColor + " for a list of commands.";
  }
}
