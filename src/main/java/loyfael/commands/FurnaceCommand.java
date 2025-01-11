package loyfael.commands;

import loyfael.utils.CookingManager;
import loyfael.utils.LuckPermsUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FurnaceCommand implements CommandExecutor {
  private static final long COOLDOWN_TIME = 15; // Cooldown en minutes
  private static final String PERMISSION = "itemcooker.use";

  private final CookingManager cookingManager;

  public FurnaceCommand(CookingManager cookingManager) {
    this.cookingManager = cookingManager;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("Cette commande est réservée aux joueurs.");
      return true;
    }

    if (LuckPermsUtils.isInCooldown(player, PERMISSION)) {
      long remainingTime = LuckPermsUtils.getRemainingCooldown(player, PERMISSION);
      long minutes = remainingTime / 60;
      long seconds = remainingTime % 60;

      player.sendMessage(String.format("§cVous devez attendre encore %d minutes et %d secondes pour réutiliser cette commande.", minutes, seconds));
      return true;
    }

    // Ajouter un cooldown
    LuckPermsUtils.setCooldown(player, PERMISSION, COOLDOWN_TIME);

    // Ouvre l'inventaire
    cookingManager.openCookerInventory(player);
    player.sendMessage("§aFour virtuel ouvert !");
    return true;
  }
}
