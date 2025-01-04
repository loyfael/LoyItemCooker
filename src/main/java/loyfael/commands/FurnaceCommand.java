package loyfael.commands;

import loyfael.cooking.CookingManager;
import loyfael.database.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FurnaceCommand implements CommandExecutor {
  private final MySQLManager mysqlManager;

  public FurnaceCommand(MySQLManager mysqlManager) {
    this.mysqlManager = mysqlManager;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("Cette commande est réservée aux joueurs.");
      return true;
    }

    String uuid = player.getUniqueId().toString();
    if (mysqlManager.isOnCooldown(uuid)) {
      long remainingTime = mysqlManager.getRemainingCooldown(uuid);
      long minutes = remainingTime / 60000;
      long seconds = (remainingTime / 1000) % 60;
      player.sendMessage("§cVous devez attendre encore " + minutes + " minutes et " + seconds + " secondes.");
      return true;
    }

    Bukkit.dispatchCommand(player, "furnace");
    mysqlManager.addCooldown(uuid);
    player.sendMessage("§aFour virtuel ouvert ! Cooldown activé pour 45 minutes.");
    return true;
  }
}
