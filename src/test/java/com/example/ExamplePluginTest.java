package com.example;

import Contador.ContadorTickPlugin;
import Pantheon.PantheonPlugin;
import analitico.AnalPlugin;
import faldita.FaldaPlugin;
import flecha.FlechaPlugin;
import monita.MonitaPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import obelisko.ObeliskoPlugin;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		//ExternalPluginManager.loadBuiltin(ExamplePlugin.class);
		//ExternalPluginManager.loadBuiltin(PajPlug.class);
		//ExternalPluginManager.loadBuiltin(NightmarePlugin.class);
		//ExternalPluginManager.loadBuiltin(PruebaPlug.class);
		ExternalPluginManager.loadBuiltin(PajPlug.class, FaldaPlugin.class, ContadorTickPlugin.class, PantheonPlugin.class, ObeliskoPlugin.class, MonitaPlugin.class);
		RuneLite.main(args);
	}
}