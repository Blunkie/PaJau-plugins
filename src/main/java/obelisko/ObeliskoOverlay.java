package obelisko;

import net.runelite.api.Client;
import net.runelite.api.NpcID;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.events.NpcDespawned;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

public class ObeliskoOverlay extends Overlay {

	@Inject
	private ObeliskoPlugin obeliskoPlugin;

	@Inject
	private Client client;

	@Inject
	ObeliskoOverlay(Client client,ObeliskoPlugin obeliskoPlugin){
		this.client=client;
		this.obeliskoPlugin=obeliskoPlugin;
		setPriority(OverlayPriority.HIGH);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPosition(OverlayPosition.DYNAMIC);
	}


	@Override
	public Dimension render(Graphics2D graphics) {
		if(obeliskoPlugin.getContador()>=0){
			graphics.setFont(new Font("Arial",Font.BOLD,24));
			Point punto = Perspective.getCanvasTextLocation(client,graphics,obeliskoPlugin.getObelisko().getLocalLocation(),String.valueOf(obeliskoPlugin.getContador()),0);
			OverlayUtil.renderTextLocation(graphics,punto, String.valueOf(obeliskoPlugin.getContador()), Color.MAGENTA);
		}

		return null;
	}
}
