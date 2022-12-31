package io.github.zerthick.enderbank;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationSettings {
    
    public final double fee;
    public final String successMessage;
    public final String failureMessage;
    public final String permissionsMessage;

    public ConfigurationSettings(final FileConfiguration config) {
        this.fee = config.getDouble("fee");
        this.successMessage = config.getString("successMessage");
        this.failureMessage = config.getString("failureMessage");
        this.permissionsMessage = config.getString("permissionsMessage");
    }

}
