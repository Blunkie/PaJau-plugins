package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ExternalPluginsChanged;
import net.runelite.client.externalplugins.ExternalPluginClient;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.externalplugins.ExternalPluginManifest;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
@PluginDescriptor(
        name = "Pajau AnalPlug"
)
public class PajPlug extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private PajPlugConfig config;

    @Inject
    private EventBus eventBus;

    private boolean knockout = true;
    private long nextKnockOutTick = 0L;

    @Provides
    PajPlugConfig getConfig(ConfigManager configManager) {
        return (PajPlugConfig)configManager.getConfig(PajPlugConfig.class);
    }


    @Override
    protected void startUp() throws Exception
    {
        log.info("Tu PaJau plugin a Empezado perrita!");

    }

    @Override
    protected void shutDown() throws Exception
    {
        log.info("Tu PaJau plugin a Terminado Gatita!");
    }

    @Subscribe
    private void onClientTick(ClientTick event) {
        if (this.client.getGameState() != GameState.LOGGED_IN || this.client
                .getMapRegions() == null ||
                !ArrayUtils.contains(this.client.getMapRegions(), 13358))
            return;
        MenuEntry[] entries = this.client.getMenuEntries();
        int putAtTopId = -1;
        for (int i = 0; i < entries.length; i++) {
            MenuEntry entry = entries[i];
            if (entry.getTarget().contains("Bandit") || entry.getTarget().contains("Menaphite Thug"))
                if ((entry.getOption().equals("Knock-Out") && this.knockout) || (entry
                        .getOption().equals("Pickpocket") && !this.knockout)) {
                    putAtTopId = i;
                    break;
                }
        }
        if (putAtTopId != -1) {
            MenuEntry temp = entries[entries.length - 1];
            entries[entries.length - 1] = entries[putAtTopId];
            entries[putAtTopId] = temp;
            this.client.setMenuEntries(entries);
        }
    }
    @Subscribe
    private void onGameTick(GameTick event) {
        if (this.client.getTickCount() >= this.nextKnockOutTick)
            this.knockout = true;
    }

    @Subscribe
    private void onChatMessage(ChatMessage event) {
        String msg = event.getMessage();
        if (event.getType() == ChatMessageType.SPAM && (msg.equals("You smack the bandit over the head and render them unconscious.") || (msg.equals("Your blow only glances off the bandit's head.") && this.config.pickpocketOnAggro()))) {
            this.knockout = false;
            int ticks = this.config.random() ? RandomUtils.nextInt(3, 4) : 4;
            this.nextKnockOutTick = (this.client.getTickCount() + ticks);
        }
    }
}
