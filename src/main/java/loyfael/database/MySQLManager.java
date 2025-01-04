package loyfael.database;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public class MySQLManager {
  private final JavaPlugin plugin;
  private Connection connection;

  public MySQLManager(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public void initializeDatabase() throws SQLException {
    FileConfiguration config = plugin.getConfig();
    String host = config.getString("mysql.host");
    int port = config.getInt("mysql.port");
    String database = config.getString("mysql.database");
    String username = config.getString("mysql.username");
    String password = config.getString("mysql.password");

    connection = DriverManager.getConnection(
            "jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
    createTable();
  }

  private void createTable() throws SQLException {
    String query = "CREATE TABLE IF NOT EXISTS furnace_cooldowns (" +
            "uuid VARCHAR(36) NOT NULL, " +
            "timestamp BIGINT NOT NULL, " +
            "PRIMARY KEY (uuid))";
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate(query);
    }
  }

  public void addCooldown(String uuid) {
    String query = "INSERT INTO furnace_cooldowns (uuid, timestamp) VALUES (?, ?) ON DUPLICATE KEY UPDATE timestamp = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      long currentTime = System.currentTimeMillis();
      statement.setString(1, uuid);
      statement.setLong(2, currentTime);
      statement.setLong(3, currentTime);
      statement.executeUpdate();
    } catch (SQLException e) {
      plugin.getLogger().severe("Failed to add cooldown: " + e.getMessage());
    }
  }

  public boolean isOnCooldown(String uuid) {
    FileConfiguration config = plugin.getConfig();
    int cooldown = config.getInt("timer.cooldown");
    String query = "SELECT timestamp FROM furnace_cooldowns WHERE uuid = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, uuid);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          long lastUsed = resultSet.getLong("timestamp");
          return System.currentTimeMillis() - lastUsed < (long) cooldown * 60 * 1000;
        }
      }
    } catch (SQLException e) {
      plugin.getLogger().severe("Failed to check cooldown: " + e.getMessage());
    }
    return false;
  }

  public long getRemainingCooldown(String uuid) {
    FileConfiguration config = plugin.getConfig();
    int cooldown = config.getInt("timer.cooldown");
    String query = "SELECT timestamp FROM furnace_cooldowns WHERE uuid = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, uuid);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          long lastUsed = resultSet.getLong("timestamp");
          return (long) cooldown * 60 * 1000 - (System.currentTimeMillis() - lastUsed);
        }
      }
    } catch (SQLException e) {
      plugin.getLogger().severe("Impossible d'obtenir le temps restant: " + e.getMessage());
    }
    return 0;
  }

  public void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) connection.close();
  }
}
