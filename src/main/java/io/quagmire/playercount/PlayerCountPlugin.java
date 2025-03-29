package io.quagmire.playercount;

import io.quagmire.core.CorePlugin;
import io.quagmire.core.chat.ChatToolkit;
import io.quagmire.core.commands.CoreCommandExecutor;
import io.quagmire.core.commands.CoreCommandRegistry;
import io.quagmire.core.commands.CoreCommandTabCompleter;
import io.quagmire.core.configuration.ConfigurationManager;
import io.quagmire.core.folia.impl.PlatformScheduler;
import io.quagmire.core.messages.MessagesManager;
import io.quagmire.playercount.commands.admin.core.AdminHelpCommand;
import io.quagmire.playercount.commands.admin.core.AdminUnknownCommand;
import io.quagmire.playercount.commands.admin.reload.AdminReloadCommand;
import io.quagmire.playercount.commands.player.core.PlayerHelpCommand;
import io.quagmire.playercount.commands.player.core.PlayerUnknownCommand;
import io.quagmire.playercount.messages.Message;
import io.quagmire.playercount.player.PlayerManager;
import lombok.Getter;
import org.bukkit.event.HandlerList;

import java.util.Objects;

public class PlayerCountPlugin extends CorePlugin {
  @Getter private PlatformScheduler scheduler;

  @Getter private final ChatToolkit chatToolkit;
  @Getter private final ConfigurationManager configurationManager;

  @Getter private CoreCommandRegistry<PlayerCountPlugin> commandRegistry;
  @Getter private CoreCommandRegistry<PlayerCountPlugin> adminCommandRegistry;
  @Getter private final MessagesManager messagesManager;

  @Getter private final PlayerManager playerManager;

  public PlayerCountPlugin() {
    chatToolkit = new ChatToolkit(this);

    configurationManager = new ConfigurationManager(this);
    commandRegistry = new CoreCommandRegistry<>(this, PlayerCountPlugin.class);
    adminCommandRegistry = new CoreCommandRegistry<>(this, PlayerCountPlugin.class);

    messagesManager = new MessagesManager(this);
    playerManager = new PlayerManager(this);
  }

  @Override
  public void onEnable() {
    /* If the plugin data folder does not exist, create it */
    if (!getDataFolder().exists()) getDataFolder().mkdir();

    scheduler = folia.getScheduler();

    setupConfigurations();

    messagesManager.initialize(Message.getInitializers());
    playerManager.reload();

    setupCommands();
    setupAdminCommands();

    getServer().getPluginManager().registerEvents(playerManager, this);
  }

  private void setupConfigurations() {
    configurationManager.register("players");
    configurationManager.reloadAll();
  }

  private void setupCommands() {
    commandRegistry = new CoreCommandRegistry<>(this, PlayerCountPlugin.class);
    commandRegistry.setAlias("playercount");
    commandRegistry.setPermissionPrefix("playercount");
    commandRegistry.setDisplayName("PlayerCount");

    commandRegistry.register(PlayerHelpCommand.class);
    commandRegistry.register(PlayerUnknownCommand.class);

    commandRegistry.setDefaultCommand("help");
    commandRegistry.setFallbackCommand("unknown");

    Objects.requireNonNull(getCommand("playercount")).setExecutor(new CoreCommandExecutor<>(this));
    Objects.requireNonNull(getCommand("playercount")).setTabCompleter(new CoreCommandTabCompleter<>(this));
  }

  private void setupAdminCommands() {
    adminCommandRegistry = new CoreCommandRegistry<>(this, PlayerCountPlugin.class);
    adminCommandRegistry.setAlias("playercountadmin");
    adminCommandRegistry.setPermissionPrefix("playercount");
    adminCommandRegistry.setDisplayName("PlayerCount Administrator");

    adminCommandRegistry.register(AdminHelpCommand.class);
    adminCommandRegistry.register(AdminUnknownCommand.class);
    adminCommandRegistry.register(AdminReloadCommand.class);

    adminCommandRegistry.setDefaultCommand("help");
    adminCommandRegistry.setFallbackCommand("unknown");

    Objects.requireNonNull(getCommand("playercountadmin")).setExecutor(new CoreCommandExecutor<>(this, adminCommandRegistry));
    Objects.requireNonNull(getCommand("playercountadmin")).setTabCompleter(new CoreCommandTabCompleter<>(this, adminCommandRegistry));
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(playerManager);
    HandlerList.unregisterAll(this);
    scheduler.cancelAllTasks();
  }
}