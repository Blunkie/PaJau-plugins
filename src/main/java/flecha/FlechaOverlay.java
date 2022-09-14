package flecha;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

public class FlechaOverlay extends Overlay {

	@Inject
	private FlechaPlugin flechaPlugin;

	@Inject
	private Client client;
	int tickRem=-1;

	@Inject
	FlechaOverlay(Client client,FlechaPlugin flechaPlugin){
		this.client =client;
		this.flechaPlugin = flechaPlugin;
		setLayer(OverlayLayer.ABOVE_SCENE);
		setPriority(OverlayPriority.HIGH);
		setPosition(OverlayPosition.DYNAMIC);
	}


	@Override
	public Dimension render(Graphics2D graphics) {

		if(flechaPlugin.p!=null){
			tickRem = flechaPlugin.p.getRemainingCycles()/30;
			if(tickRem<0){
				flechaPlugin.p=null;
				return null;
			}

			LocalPoint lp = new LocalPoint((int) flechaPlugin.p.getX(), (int) flechaPlugin.p.getY());

			Point punto = Perspective.getCanvasTextLocation(this.client,graphics,lp,Integer.toString(tickRem),0);
			graphics.setFont(new Font("Arial",1,24));
			OverlayUtil.renderTextLocation(graphics,punto,Integer.toString(tickRem), Color.RED);

		}
		return null;
	}
}
