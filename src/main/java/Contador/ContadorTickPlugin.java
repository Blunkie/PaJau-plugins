package Contador;

import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Objects;

@PluginDescriptor(name = "PaJau ContadorTick",description = "Cuenta los tick de atake de un NPC(guardia de varrock)")
public class ContadorTickPlugin extends Plugin {
	private static final Logger log = LoggerFactory.getLogger(ContadorTickPlugin.class);

	@Inject
	private Client client;

	@Inject
	private ContadorTickOverlay contadorTickOverlay;

	@Inject
	private OverlayManager overlayManager;

	private static final int guardAtkSpeed = 4;
	public boolean enPelea = false;
	private static final int aniATK_ID = 6489;
	private static final int deathAni_ID = 6490; //colocar
	public int tickUntilNextAtck = -1;

	@Nullable
	public NPC mob = null;

	protected void startUp(){
		this.overlayManager.add(this.contadorTickOverlay);
	}

	@Subscribe
	private void onAnimationChanged(AnimationChanged event) throws NullPointerException{
		Actor actor = event.getActor();
		if(!(actor instanceof NPC) || actor.getName()==null ){
			return;
		}
		log.info("Katarina Chupalo");
		if(actor.getInteracting()==null){
			return;
		}
		if(actor.isDead() || actor.getAnimation()==deathAni_ID){
			if(actor.equals(mob)){
				mob=null;
				enPelea=false;
				tickUntilNextAtck=-1;
			}
		}

		if(event.getActor().getInteracting().equals(client.getLocalPlayer()) ){
			log.debug("Quieres anal KATA? ");
			if(mob==null){
				mob = (NPC) event.getActor();
				enPelea = true;
				tickUntilNextAtck=-1;
			}
			if (mob.getAnimation() == aniATK_ID){
				tickUntilNextAtck =guardAtkSpeed;
			} else if (mob.getAnimation() == deathAni_ID) {
				mob = null;
				enPelea = false;
				log.info("Se murio el qliao!!!!");
			}
		}
	}

	@Subscribe
	private void onGameTick(GameTick event){

		if(enPelea && mob != null && tickUntilNextAtck >=  0){
			log.debug("En pelea con tickUntilAtk={}",tickUntilNextAtck);
			log.debug("Lenia={}",mob.getName());
			tickUntilNextAtck--;
		}
	}
}
