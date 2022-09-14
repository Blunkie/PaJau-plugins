package com.example;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("pruebaConfig")
public interface PruebaConfig extends Config {
	@Alpha
	@ConfigItem(
			name = "Colorcito",
			keyName = "colocito",
			description = "Que color desea el overlay. miau",
			position = 0
	)
	default Color katita(){return new Color(255,255,0,50); }
}
