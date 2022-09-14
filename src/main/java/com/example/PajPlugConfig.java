package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("Perrita")
public interface PajPlugConfig extends Config{
    @ConfigItem(keyName = "pickpocketOnAggro", name = "Pickpocket al aggro", description = "Deja el \"Pickpocket\" cuando el bandit te agarra el agro. Salva food pero pierde un poco de xp/h.", position = 0)
    default boolean pickpocketOnAggro() {
        return false;
    }

    @ConfigItem(keyName = "random", name = "1 tick adicional", description = "Pierde 1 pickpocket cada cierto tiempo<br>  de forma random", position = 1)
    default boolean random() {
        return false;
    }
}
