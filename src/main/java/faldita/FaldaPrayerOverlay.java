package faldita;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.Prayer;
import net.runelite.api.VarClientInt;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
class FaldaPrayerOverlay extends Overlay {
	private static final Logger log = LoggerFactory.getLogger(FaldaPrayerOverlay.class);

	private final Client client;

	private final FaldaPlugin plugin;

	private final FaldaConfig config;

	@Inject
	private FaldaPrayerOverlay(Client client, FaldaPlugin plugin, FaldaConfig config) {
		this.client = client;
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPriority(OverlayPriority.LOW);
	}

	public Dimension render(Graphics2D graphics) {
		if (!this.plugin.isInFight() || this.plugin.getNm() == null)
			return null;
		FaldaAttack attack = this.plugin.getPendingNightmareAttack();
		if (attack == null)
			return null;
		if (!this.config.prayerHelper().showWidgetHelper())
			return null;
		Color color = this.client.isPrayerActive(attack.getPrayer()) ? Color.GREEN : Color.RED;
		renderPrayerOverlay(graphics, this.client, attack.getPrayer(), color);
		return null;
	}

	public static Rectangle renderPrayerOverlay(Graphics2D graphics, Client client, Prayer prayer, Color color) {
		WidgetInfo wea = null;
		if(Prayer.PROTECT_FROM_MAGIC == prayer){
			wea = WidgetInfo.PRAYER_PROTECT_FROM_MAGIC;
		} else if (Prayer.PROTECT_FROM_MISSILES == prayer) {
			wea = WidgetInfo.PRAYER_PROTECT_FROM_MISSILES;
		} else if (Prayer.PROTECT_FROM_MELEE == prayer) {
			wea = WidgetInfo.PRAYER_PROTECT_FROM_MELEE;
		}else {
			wea = null;
		}
		//Widget widget = client.getWidget(prayer.getWidgetInfo());
		Widget widget = client.getWidget(wea);
		//if (widget == null || client.getVar(171) != InterfaceTab.PRAYER.getId())
		if (widget == null || client.getVarcIntValue(VarClientInt.INVENTORY_TAB) != 5) //5 es la PRAYER_TAB
			return null;
		Rectangle bounds = widget.getBounds();
		OverlayUtil.renderPolygon(graphics, rectangleToPolygon(bounds), color);
		return bounds;
	}

	private static Polygon rectangleToPolygon(Rectangle rect) {
		int[] xpoints = { rect.x, rect.x + rect.width, rect.x + rect.width, rect.x };
		int[] ypoints = { rect.y, rect.y, rect.y + rect.height, rect.y + rect.height };
		return new Polygon(xpoints, ypoints, 4);
	}
}
