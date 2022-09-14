package com.example;

import com.google.inject.Provides;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.AnimationChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Objects;

@PluginDescriptor(name = "PaJau Overlay",description = "Probando como funka los overlay",enabledByDefault = false,tags = {"pajau","overlay"})
@Singleton
public class PruebaPlug extends Plugin {
	//private static final Logger log = LoggerFactory.getLogger(PruebaPlug.class);
	private static final Logger log = LoggerFactory.getLogger(PruebaPlug.class);

	@Inject
	private Client client;

	@Inject
	private PruebaConfig configuracion;

	@Inject
	private PruebaOverlay pOver;

	@Inject
	private OverlayManager meow;


	@Provides
	PruebaConfig provideConfig(ConfigManager configManager) {
		return (PruebaConfig) configManager.getConfig(PruebaConfig.class);
	}

	protected void startUp(){
		this.meow.add(pOver);
	}
	protected void shutDown(){
		this.Peleando = false;
	}

	private boolean Peleando = false;

	public boolean enPelea(){
		return this.Peleando;
	}
/*
	@SuppressWarnings("all")
	@Subscribe
	void onAnimationChanged(@Nullable AnimationChanged event) throws NullPointerException{
		try {
			assert event != null;
			if (event.getActor().getInteracting().getName() != null) {
				if (event.getActor().getInteracting().getName().contains("xJinjer")) {
					this.Peleando = true;
				}
			}
		}catch (NullPointerException q){
			return;
		}
	}

 */

	@Subscribe
	private void onAnimationChanged(AnimationChanged event){
		Actor foo = event.getActor();
		if(!(foo instanceof NPC))
			return;
		if (foo.getInteracting().getName() != null) {
			String nombre = foo.getInteracting().getName() ;
			if (nombre.contains("xJinjer")) {
				this.Peleando = true;
			}
		}
	}

}

	/*
	Overlay guau = new Overlay() {
		@Override
		public Dimension render(Graphics2D graphics) {
			Color col = Color.ORANGE;
			Rectangle rectangulo = new Rectangle(20,20);
			OverlayUtil.renderPolygon(graphics,rectangulo,col);
			return null;
		}
	};*/
/*
	@Inject
	private Overlay guau;
	public Dimension render(Graphics2D graphics){
		Color col = Color.ORANGE;
		Rectangle rectangulo = new Rectangle(20,20);
		OverlayUtil.renderPolygon(graphics,rectangulo,col);
		return null;
	}
	*/
