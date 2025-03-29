# PublicPlayerCount

PublicPlayerCount is a lightweight Minecraft plugin that allows server administrators to control the maximum player capacity and define bypass players who can join even when the server is full.

![Version](https://img.shields.io/badge/version-1.0.3-blue.svg)
![API Version](https://img.shields.io/badge/api--version-1.21-yellow.svg)
![Folia Support](https://img.shields.io/badge/folia--support-yes-brightgreen.svg)

## Features

- Set a configurable maximum player capacity
- Define whitelist of players who can bypass the capacity limit
- Simple configuration
- Full Folia compatibility
- Integration with Quagmire Core

## Requirements

- Java 21 or higher
- Paper/Spigot 1.21+
- Quagmire Core

## Installation

1. Download the latest version of PublicPlayerCount
2. Place the JAR file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin using the `players.yml` file

## Usage

### Configuration

The plugin uses a simple configuration file `players.yml` where you can define:

```yaml
# Maximum number of players allowed on the server
capacity: 50

# List of players who can bypass the capacity limit
bypass-players:
  - Quagmire
```

### Commands

- `/playercount` - Base command for players
- `/playercountadmin` - Base command for administrators
- `/playercountadmin reload` - Reloads the plugin configuration

## Permissions

- `playercount.player` - Permission to use the plugin as a player
- `playercount.admin` - Permission to use the plugin as an administrator
- `playercount.reload` - Permission to reload the plugin

## Developer API

For developers looking to integrate with PublicPlayerCount, you can access the plugin instance and player manager:

```java
PlayerCountPlugin plugin = (PlayerCountPlugin) Bukkit.getPluginManager().getPlugin("PlayerCount");
PlayerManager playerManager = plugin.getPlayerManager();
```

## Support

If you encounter any issues or have questions, feel free to create an issue on our GitHub repository.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

We welcome contributions to the project! If you'd like to contribute:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -am 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Credits

- **Author**: Quagmire (me@quagmire.dev) 