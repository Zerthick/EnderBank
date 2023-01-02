package io.github.zerthick.enderbank;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class EnderBank extends JavaPlugin {

    private static Economy econ;
    private Logger logger;
    private ConfigurationSettings configurationSettings;
    private TextComponents textComponents;

    public static Economy getEcon() {
        return econ;
    }

    public TextComponents getTextComponents() {
        return textComponents;
    }

    public ConfigurationSettings getConfigurationSettings() {
        return configurationSettings;
    }

    @Override
    public void onEnable() {
        logger = getLogger();

        // Plugin startup logic
        if (!setupEconomy()) {
            logger.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.saveDefaultConfig();
        this.configurationSettings = new ConfigurationSettings(this.getConfig());

        this.textComponents = new TextComponents(this);

        this.getServer().getPluginManager().registerEvents(new EnderBankListener(this), this);

        logger.info(String.format("%s version %s by %s enabled!", getDescription().getName(), getDescription().getVersion(), getDescription().getAuthors()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
