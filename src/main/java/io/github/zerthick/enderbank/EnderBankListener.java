package io.github.zerthick.enderbank;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderBankListener implements Listener {

    private static Economy econ;
    private final EnderBank instance;
    private final TextComponents textComponents;

    public EnderBankListener(EnderBank instance) {
        this.instance = instance;
        textComponents = instance.getTextComponents();
        econ = EnderBank.getEcon();
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.ENDER_CHEST && event.getAction().isRightClick()) {
            final Player player = event.getPlayer();
            if (player.hasPermission("enderbank.use")) {
                if (player.hasPermission("enderbank.free")) {
                    player.sendMessage(textComponents.renderSuccessMessageWithoutFee());
                } else {
                    double balance = econ.getBalance(player);
                    double fee = instance.getConfigurationSettings().fee;
                    if (balance > fee) {
                        EconomyResponse economyResponse = econ.withdrawPlayer(player, fee);

                        if (economyResponse.transactionSuccess()) {
                            player.sendMessage(textComponents.renderSuccessMessageWithFee());
                        } else {
                            event.setCancelled(true);
                            player.sendMessage(textComponents.renderFailureMessage());
                        }
                    } else {
                        event.setCancelled(true);
                        player.sendMessage(textComponents.renderFailureMessage());
                    }
                }
            } else {
                event.setCancelled(true);
                player.sendMessage(textComponents.renderPermissionMessage());
            }
        }
    }
}
