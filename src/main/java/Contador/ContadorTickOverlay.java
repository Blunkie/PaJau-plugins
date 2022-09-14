package Contador;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

@Singleton
class ContadorTickOverlay extends Overlay {

	private final Client client;
	private final ContadorTickPlugin plug;
	private final ModelOutlineRenderer outliner;


	@Inject
	ContadorTickOverlay(Client client,ContadorTickPlugin plug,ModelOutlineRenderer outliner){
		this.client=client;
		this.plug=plug;
		this.outliner=outliner;
		setLayer(OverlayLayer.ABOVE_SCENE);
		setPriority(OverlayPriority.LOW);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		if(plug.enPelea && plug.mob != null && plug.tickUntilNextAtck>=0){
			LocalPoint monoLP = plug.mob.getLocalLocation();
			String cuenta = Integer.toString(plug.tickUntilNextAtck);
			Point lugarEscritura = Perspective.getCanvasTextLocation(this.client,graphics,monoLP,cuenta,0);
			if (lugarEscritura!= null) {
				graphics.setFont(new Font("Arial", 1, 36));
				OverlayUtil.renderTextLocation(graphics, lugarEscritura, cuenta, Color.MAGENTA);
			}
		}
		return null;
	}
}
