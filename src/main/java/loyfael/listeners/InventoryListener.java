package loyfael.listeners;

import loyfael.utils.CookingManager;
import loyfael.utils.LuckPermsUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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

    // Vérifie si l'inventaire est celui du Cooker
    if (!INVENTORY_TITLE.equals(event.getView().getTitle())) return;

    // Vérifie si le joueur clique sur le bouton de confirmation
    if (event.getSlot() == 26) {
      event.setCancelled(true); // Empêche l'interaction par défaut avec le bouton
      Inventory inventory = event.getClickedInventory();
      if (inventory != null) {
        boolean itemsCooked = cookingManager.cookItems(player, inventory);
        if (itemsCooked) {
          LuckPermsUtils.setCooldown(player, "itemcooker.use", 15); // Applique la permission en false
        }
      }
      player.closeInventory();
      return;
    }

    // Permet de déplacer ou retirer des items autorisés
    if (event.getClickedInventory() == event.getView().getTopInventory()) {
      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem != null) {
        Material type = clickedItem.getType();
        if (!cookingManager.getCookingMap().containsKey(type)) {
          event.setCancelled(true); // Bloque uniquement les items non cuisables
          player.sendMessage("§cCet objet ne peut pas être cuit.");
        }
      }
    }
  }
}
