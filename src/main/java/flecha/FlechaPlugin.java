package flecha;

import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(name = "PaJau Projectiles",description = "Marka los projectiles")
public class FlechaPlugin extends Plugin {

	@Inject
	private FlechaOverlay flechaOverlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private Client client;

	public Projectile p;

	protected void startUp(){
		this.overlayManager.add(this.flechaOverlay);
	}

	@Subscribe
	private void onProjectileMoved(ProjectileMoved event){
		if(event.getProjectile().getId() == 9){
			p= event.getProjectile();

		}
	}
}
