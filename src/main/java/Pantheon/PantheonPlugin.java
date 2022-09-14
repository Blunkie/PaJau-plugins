package Pantheon;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(name = "Pajau Pantheon",description = "Pal facking prayer del pantheon")
@Getter
@Setter
public class PantheonPlugin extends Plugin {

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private PantheonOverlay pantheonOverlay;

	@Inject
	private PantheonPrayOverlay pantheonPrayOverlay;


	private boolean ranged = true;

	private boolean Overlaycito = false;
	private NPC maricon;

	protected void startUp()
	{
		overlayManager.add(pantheonOverlay);
		overlayManager.add(pantheonPrayOverlay);
	}

	protected void shutDown()
	{
		overlayManager.remove(pantheonOverlay);
		overlayManager.remove(pantheonPrayOverlay);
	}

	@Subscribe
	private void onAnimationChanged(AnimationChanged event)
	{
		if(event == null || !(event.getActor() instanceof NPC))
		{
			return;
		}

		if(((NPC) event.getActor()).getId()==11777){
			if (event.getActor().getAnimation() == 9777)
			{
				ranged = !ranged;
			}
		}
	}

	@Subscribe
	private void onNpcSpawned(NpcSpawned event)
	{
		if(event.getNpc() == null){
			return;
		}
		if(event.getNpc().getId() == 11777){
			maricon=event.getNpc();
			Overlaycito = !Overlaycito;
		}
	}

	@Subscribe
	private void onNpcDespawned(NpcDespawned event)
	{
		if(event.getNpc() == null){
			return;
		}
		if(event.getNpc().getId() == 11777){
			maricon=null;
			Overlaycito = !Overlaycito;
			ranged = true;
		}
	}

}
