package io.github.zerthick.enderbank;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationSettings {

    public final double fee;
    public final String successMessage;
    public final String failureMessage;
    public final String permissionsMessage;

    public ConfigurationSettings(final FileConfiguration config) {
        fee = config.getDouble("fee");
        successMessage = config.getString("successMessage");
        failureMessage = config.getString("failureMessage");
        permissionsMessage = config.getString("permissionsMessage");
    }

}
