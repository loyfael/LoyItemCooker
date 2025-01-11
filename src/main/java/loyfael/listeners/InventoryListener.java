package loyfael.listeners;

import loyfael.utils.CookingManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
  private static final String INVENTORY_TITLE = "Cooker";

  private final CookingManager cookingManager;

  public InventoryListener(CookingManager cookingManager) {
    this.cookingManager = cookingManager;
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player player)) return;

    if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

    event.setCancelled(true);

    if (event.getSlot() == 26) {
      cookingManager.cookItems(player, event.getClickedInventory());
      player.closeInventory();
    }

    if (event.getCursor() != null) {
      ItemStack item = event.getCursor();
      if (!cookingManager.isCookable(item.getType())) {
        event.setCancelled(true);
        player.sendMessage("§cCet item ne peut pas être cuit !");
      }
    }
  }

  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    if (INVENTORY_TITLE.equals(event.getView().getTitle())) {
      ((Player) event.getPlayer()).sendMessage("§aMerci d'avoir utilisé le Cooker !");
    }
  }
}
