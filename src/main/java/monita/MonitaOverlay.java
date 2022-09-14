package monita;

import jdk.vm.ci.meta.Local;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
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


public class MonitaOverlay extends Overlay {
	@Inject
	private Client client;

	@Inject
	private MonitaPlugin monitaPlugin;

	@Inject
	MonitaOverlay(Client client,MonitaPlugin monitaPlugin){
		this.monitaPlugin=monitaPlugin;
		this.client=client;
		setLayer(OverlayLayer.ABOVE_SCENE);
		setPriority(OverlayPriority.HIGH);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		if(monitaPlugin.getCount()>=0){
			graphics.setFont(new Font("Arial",Font.BOLD,24));
			LocalPoint lp = monitaPlugin.getMona().getLocalLocation();
			Point punto = Perspective.getCanvasTextLocation(client,graphics,lp,String.valueOf(monitaPlugin.getCount()),0);
			OverlayUtil.renderTextLocation(graphics,punto,String.valueOf(monitaPlugin.getCount()), Color.WHITE);
		}

		return null;
	}
}
