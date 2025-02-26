package loyfael.listeners;

import loyfael.utils.CookingManager;
import loyfael.utils.LuckPermsUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
  private static final String INVENTORY_TITLE = "Cooker";
  private final CookingManager cookingManager;

  public InventoryListener(CookingManager cookingManager) {
    this.cookingManager = cookingManager;
  }

  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    if (!(event.getPlayer() instanceof Player player)) return;

    if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

    Inventory inventory = event.getInventory();
    boolean itemsCooked = cookingManager.cookItems(player, inventory);

    if (itemsCooked) {
      // Applique un cooldown de 15 minutes si des items ont été cuits
      LuckPermsUtils.setCooldown(player, "itemcooker.use", 15);
      player.sendMessage("§aRafraichissement appliqué : vous devez désormais attendre 15 minutes avant d'utiliser à nouveau le four.");
    }
  }
}
