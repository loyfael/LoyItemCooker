package loyfael;

import loyfael.commands.FurnaceCommand;
import loyfael.cooking.CookingManager;
import loyfael.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener {

  private static CookingManager cookingManager;

  @Override
  public void onEnable() {
    try {
      // Vérification de LuckPerms
      if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) {
        getLogger().severe("LuckPerms n'est pas installé. Le plugin ne fonctionnera pas correctement.");
        Bukkit.getPluginManager().disablePlugin(this);
        return;
      }

      // Initialisation du gestionnaire de cuisson
      CookingManager cookingManager = new CookingManager();

      // Enregistrement des commandes et des événements
      Objects.requireNonNull(getCommand("furnace")).setExecutor(new FurnaceCommand(cookingManager));
      Bukkit.getPluginManager().registerEvents(new InventoryListener(cookingManager), this);

      getLogger().info("[ItemCooker] Plugin enabled successfully!");
    } catch (Exception e) {
      getLogger().severe("[ItemCooker] Failed to enable: " + e.getMessage());
    }
  }

  @Override
  public void onDisable() {
    getLogger().info("[ItemCooker] Plugin disabled!");
  }
}


