package loyfael.commands;

import loyfael.utils.CookingManager;
import loyfael.utils.LuckPermsUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FurnaceCommand implements CommandExecutor {
  private final CookingManager cookingManager;

  public FurnaceCommand(CookingManager cookingManager) {
    this.cookingManager = cookingManager;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("Cette commande est réservée aux joueurs.");
      return true;
    }

    if (LuckPermsUtils.isInCooldown(player, "itemcooker.use")) {
      long remainingTime = LuckPermsUtils.getRemainingCooldown(player, "itemcooker.use");
      long minutes = remainingTime / 60;
      long seconds = remainingTime % 60;

      player.sendMessage(String.format("§cVous devez attendre encore %d minutes et %d secondes pour réutiliser cette commande.", minutes, seconds));
      return true;
    }

    cookingManager.openCookerInventory(player);
    player.sendMessage("§aFour virtuel ouvert !");
    return true;
  }
}
