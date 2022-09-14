package com.example;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@Singleton
public class PruebaOverlay extends Overlay {

	private Client client;

	private PruebaConfig config;

	private PruebaPlug plug;

	@Inject
	private PruebaOverlay(Client client,PruebaPlug plug,PruebaConfig config){
		this.client=client;
		this.config=config;
		this.plug=plug;

		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPriority(OverlayPriority.LOW);
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		if(!plug.enPelea()){
			return null;
		}
		Rectangle rectangulo = new Rectangle(300,200,50,70);
		OverlayUtil.renderPolygon(graphics,rectangulo,config.katita());
		return null;
	}
}
