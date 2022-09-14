package monita;

import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;

@Getter
@PluginDescriptor(name = "PaJau Monita",description = "para minar en 1 vuelta")
public class MonitaPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private  MonitaOverlay monitaOverlay;

	@Inject
	private OverlayManager overlayManager;
	private int count;
	private NPC mona;

	protected void startUp(){
		this.overlayManager.add(monitaOverlay);
	}

	protected void shutDown(){
		this.overlayManager.remove(monitaOverlay);
	}

	//9743
	@Subscribe
	void onGameTick(GameTick event){
		if (count>=0){
			count--;
		}
	}

	@Subscribe
	void onAnimationChanged(AnimationChanged event){
		if(event.getActor()!=null){
			if(event.getActor().getAnimation()==9743){
				count=6;
			}
		}
	}

	@Subscribe
	void onNpcSpawned(NpcSpawned event){
		if(event.getNpc()!=null){
			if(event.getNpc().getId()==11778){
				mona=event.getNpc();
			}
		}
	}

}
