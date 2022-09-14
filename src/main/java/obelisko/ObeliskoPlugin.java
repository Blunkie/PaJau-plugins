package obelisko;

import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Getter
@PluginDescriptor(name = "PaJau Obelisko",description = "para minar en 1 vuelta")
public class ObeliskoPlugin extends Plugin {

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ObeliskoOverlay obeliskoOverlay;
	private boolean aparecio;
	private int contador=-1;
	private NPC obelisko;

	protected void startUp(){
		this.overlayManager.add(obeliskoOverlay);
	}

	protected void shutDown(){
		this.overlayManager.remove(obeliskoOverlay);
	}

	@Subscribe
	void onGameTick(GameTick event){
		if(contador>=0) {
			contador--;
		}
	}

	@Subscribe
	void onGraphicsObjectCreated(GraphicsObjectCreated event){
		if (event.getGraphicsObject() == null) {
			return;
		}
		if ((event.getGraphicsObject().getId()==2114 ||
				event.getGraphicsObject().getId()==2119 ||
				event.getGraphicsObject().getId()==2064 ||
				event.getGraphicsObject().getId()==2120) &&
				contador != 9){

			contador=9;
		}
	}
	
	@Subscribe
	void onNpcSpawned(NpcSpawned event){
		if(event.getNpc()==null) return;
		if(event.getNpc().getId()==11706){
			obelisko=event.getNpc();
		}
	}

}
