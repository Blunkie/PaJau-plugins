package analitico;

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
		name = "Pajau Analitico"
)
public class AnalPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private AnalPlugin config;

	@Inject
	private EventBus eventBus;

	private boolean knockout = true;
	private long nextKnockOutTick = 0L;


	@Override
	protected void startUp() throws Exception {
		log.info("Tu PaJau plugin a Empezado perrita!");

	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Tu PaJau plugin a Terminado Gatita!");
	}

	@Subscribe
	private void onClientTick(ClientTick event) {
		if (this.client.getGameState() != GameState.LOGGED_IN || this.client
				.getMapRegions() == null)
			return;
		MenuEntry[] entries = this.client.getMenuEntries();
		int putAtTopId = -1;
		for (int i = 0; i < entries.length; i++) {
			MenuEntry entry = entries[i];
			if (entry.getOption().contains("Pet")) {
				//entries[i].setOption("Anal");
				putAtTopId = i;
				break;
			}
		}
		if (putAtTopId != -1) {
			MenuEntry[] temp = new MenuEntry[entries.length +1];
			for (int i = 0; i < entries.length; i++){
				temp[i] = entries [i];
			}
			entries[putAtTopId].setOption("Penetrar");
			entries[putAtTopId].setIdentifier(14);
			temp[temp.length-1]=entries[putAtTopId];
			this.client.setMenuEntries(temp);
		}
	}

	@Subscribe
	private void onGameTick(GameTick event) {
		if (this.client.getTickCount() >= this.nextKnockOutTick)
			this.knockout = true;
	}
}

