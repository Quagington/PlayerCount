package io.quagmire.playercount.commands;

import io.quagmire.core.commands.CoreCommand;
import io.quagmire.core.commands.CoreCommandRegistry;
import io.quagmire.playercount.PlayerCountPlugin;
import io.quagmire.playercount.messages.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Map;

public abstract class PlayerCountCommand extends CoreCommand<PlayerCountPlugin> {
  public PlayerCountCommand(PlayerCountPlugin plugin, Command command, String[] args, CommandSender sender, CoreCommandRegistry<PlayerCountPlugin> registry) {
    super(plugin, command, args, sender, registry);
  }

  protected void messageSender(Message message) {
    super.messageSender(message.name(), true);
  }

  protected void messageSender(Message message, Map<String, String> renders) {
    super.messageSender(message.name(), true, renders);
  }
}
