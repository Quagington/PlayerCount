package io.quagmire.playercount.commands.admin.reload;

import io.quagmire.playercount.PlayerCountPlugin;
import io.quagmire.playercount.commands.admin.AdminCommand;
import io.quagmire.playercount.messages.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class AdminReloadCommand extends AdminCommand {
  public AdminReloadCommand(PlayerCountPlugin plugin, Command command, String[] args, CommandSender sender) {
    super(plugin, command, args, sender);
    setDescription("Reloads the configuration.");
    setPermission(getPermissionPrefix() + ".reload");
    setSyntax("");
  }

  @Override
  public boolean validate() {
    if (!sender.hasPermission(permission)) {
      messageSender(Message.NO_PERMISSIONS);
      return false;
    }
    return true;
  }

  @Override
  public void execute() {
    try {
      plugin.reloadConfig();
      plugin.getMessagesManager().reload();

      if (!plugin.getConfigurationManager().reloadAll()) {
        messageSender(Message.RELOAD_FAILURE);
        return;
      }

      plugin.getPlayerManager().reload();

      messageSender(Message.RELOAD_SUCCESS);
    } catch (Exception ex) {
      ex.printStackTrace();
      messageSender(Message.RELOAD_FAILURE);
    }
  }

  @Override
  public List<String> tab() {
    return Collections.emptyList();
  }

  @Override
  public String subcommand() {
    return "reload";
  }
}