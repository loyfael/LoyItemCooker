package loyfael;

import loyfael.commands.FurnaceCommand;
import loyfael.cooking.CookingManager;
import loyfael.database.MySQLManager;
import loyfael.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
    private static MySQLManager mysqlManager;

  @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            // Initialisation des gestionnaires
            mysqlManager = new MySQLManager(this);
            mysqlManager.initializeDatabase();

            CookingManager cookingManager = new CookingManager();

            // Enregistrement des commandes et événements
            Objects.requireNonNull(getCommand("furnace")).setExecutor(new FurnaceCommand(mysqlManager));
            Bukkit.getPluginManager().registerEvents(new InventoryListener(cookingManager), this);

            getLogger().info("[CookerPlugin] Plugin enabled successfully!");
        } catch (Exception e) {
            getLogger().severe("[CookerPlugin] Failed to enable: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        try {
            if (mysqlManager != null) mysqlManager.closeConnection();
            getLogger().info("[CookerPlugin] Plugin disabled!");
        } catch (Exception e) {
            getLogger().severe("[CookerPlugin] Failed to disable: " + e.getMessage());
        }
    }
}


