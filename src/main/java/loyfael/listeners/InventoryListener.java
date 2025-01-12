package loyfael.listeners;

import loyfael.utils.CookingManager;
import loyfael.utils.LuckPermsUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
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

    // Verify if the inventory is the Cooker's
    if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

    // Management of the confirmation button
    if (event.getSlot() == 26) {
      event.setCancelled(true);
      Inventory inventory = event.getClickedInventory();
      if (inventory != null) {
        boolean itemsCooked = cookingManager.cookItems(player, inventory);
        if (itemsCooked) {
          LuckPermsUtils.setCooldown(player, "itemcooker.use", 15);
        }
      }
      player.closeInventory();
      return;
    }

    // Management of the addition of items
    if (event.getClickedInventory() == event.getView().getTopInventory()) {
      ItemStack cursorItem = event.getCursor(); // Item which is being moved
      if (cursorItem != null) {
        Material type = cursorItem.getType();
        if (!cookingManager.getCookingMap().containsKey(type)) {
          event.setCancelled(true); // block the addition of unauthorized items
          player.sendMessage("§cCet item ne peut pas être ajouté à l'inventaire !");
        }
      }
    }
  }

  @EventHandler
  public void onInventoryDrag(InventoryDragEvent event) {
    if (!(event.getWhoClicked() instanceof Player player)) return;

    // Verify if the inventory is the Cooker's
    if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

    // Block the addition of unauthorized items
    for (int slot : event.getRawSlots()) {
      if (slot < event.getView().getTopInventory().getSize()) { // Si le slot est dans l'inventaire Cooker
        Material type = event.getOldCursor().getType();
        if (!cookingManager.getCookingMap().containsKey(type)) {
          event.setCancelled(true);
          player.sendMessage("§cVous ne pouvez pas déplacer cet item ici !");
          break;
        }
      }
    }
  }
}
