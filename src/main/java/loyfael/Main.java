package loyfael;

import loyfael.commands.FurnaceCommand;
import loyfael.listeners.InventoryListener;
import loyfael.utils.CookingManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
  public CookingManager cookingManager;

  @Override
  public void onEnable() {
    try {
      // Vérification de LuckPerms
      if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) {
        getLogger().severe("LuckPerms n'est pas installé. Le plugin ne fonctionnera pas correctement.");
        Bukkit.getPluginManager().disablePlugin(this);
        return;
      }

      // Initialisation
      cookingManager = new CookingManager();

      // Enregistrement des commandes et événements
      Objects.requireNonNull(getCommand("furnace")).setExecutor(new FurnaceCommand(cookingManager));
      Bukkit.getPluginManager().registerEvents(new InventoryListener(cookingManager), this);

      getLogger().info("[CookerPlugin] Plugin enabled successfully!");
    } catch (Exception e) {
      getLogger().severe("[CookerPlugin] Failed to enable: " + e.getMessage());
    }
  }

  @Override
  public void onDisable() {
    getLogger().info("[CookerPlugin] Plugin disabled!");
  }
}