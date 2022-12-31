package io.github.zerthick.enderbank;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.milkbowl.vault.economy.Economy;

import java.util.Objects;

public class TextComponents {

    private static Economy econ;
    private final EnderBank instance;
    private final ConfigurationSettings configurationSettings;

    public TextComponents(EnderBank instance) {
        this.instance = instance;
        configurationSettings = instance.getConfigurationSettings();
        econ = EnderBank.getEcon();
    }

    public Component renderSuccessMessageWithoutFee() {
        return Component.text("[" + instance.getDescription().getName() + "] ", NamedTextColor.DARK_PURPLE)
                .append(Component.text(Objects.requireNonNull(configurationSettings.successMessage), NamedTextColor.YELLOW));
    }

    public Component renderSuccessMessageWithFee() {
        return this.renderSuccessMessageWithoutFee().append(Component.text(String.format(" You have been deducted %.2f %s", configurationSettings.fee, econ.currencyNamePlural()), NamedTextColor.YELLOW));
    }

    public Component renderFailureMessage() {
        return Component.text("[" + instance.getDescription().getName() + "] " + configurationSettings.failureMessage, NamedTextColor.RED)
                .append(Component.text(String.format(" Required funds %.2f %s", configurationSettings.fee, econ.currencyNamePlural()), NamedTextColor.RED));
    }

    public Component renderPermissionMessage() {
        return Component.text("[" + instance.getDescription().getName() + "] " + configurationSettings.permissionsMessage, NamedTextColor.RED);
    }
}
