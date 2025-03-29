package io.quagmire.playercount.player;

import io.quagmire.playercount.PlayerCountPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerManager implements Listener {
  private final PlayerCountPlugin plugin;

  private int capacity;
  private List<String> bypassPlayers;

  public PlayerManager(PlayerCountPlugin plugin) {
    this.plugin = plugin;
  }

  public void reload() {
    plugin.getConfigurationManager().reload("players");

    YamlConfiguration config = plugin.getConfigurationManager().getConfiguration("players");
    if (config == null) return;

    config.options().copyDefaults(true);
    config.addDefault("capacity", 50);
    config.addDefault("bypass-players", List.of("Quagmire"));

    plugin.getConfigurationManager().save("players", config);

    capacity = config.getInt("capacity", 0);
    bypassPlayers = config.getStringList("bypass-players");
  }

  @EventHandler
  private void onPlayerJoin(AsyncPlayerPreLoginEvent event) {
    if (bypassPlayers.contains(event.getName())) return;
    if (plugin.getServer().getOnlinePlayers().size() >= capacity) {
      event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, "The server is full.");
    }
  }
}
