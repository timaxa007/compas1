package timaxa007.compas.v1;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class Events {

	private static final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void drawText(RenderGameOverlayEvent.Post event) {
		switch(event.type) {
		case ALL:
			GL11.glPushMatrix();

			int min = event.resolution.getScaledWidth() / 3;
			int max = event.resolution.getScaledWidth() - (min * 2);
			GL11.glScissor(
					min * event.resolution.getScaleFactor(),
					(event.resolution.getScaledHeight() - 20) * event.resolution.getScaleFactor(),
					min * event.resolution.getScaleFactor(),
					235 * event.resolution.getScaleFactor());
			GL11.glEnable(GL11.GL_SCISSOR_TEST);

			double deg = mc.thePlayer.rotationYaw + 180D;
			deg %= 360D;
			if (deg < 0) deg = 360D + deg;

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(0.25F, 0.25F, 0.25F, 0.25F);
			mc.ingameGUI.drawTexturedModalRect(min, 0, 0, 0, max, 20);
			//mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 0, event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);

			mc.fontRenderer.drawString("|", (event.resolution.getScaledWidth() / 2), 11, 0xFFFFFF);

			int offset = ((int)deg / 15) * 15;
			for (int i = -12; i < 36; ++i) {
				int g = ((i + 24) % 24) * 15;
				int setX = (int)((event.resolution.getScaledWidth() / 2) + (g == 0 ? 2 : 0) + (i - (deg / 15)) * 15);
				GL11.glPushMatrix();
				//GL11.glTranslated((min + min + (i - (deg / 15)) * 15), 0, 0);
				GL11.glTranslated(setX, 0, 0);

				GL11.glPushMatrix();
				if (!Minecraft.getMinecraft().fontRenderer.getUnicodeFlag())
					GL11.glScalef(0.75F, 0.75F, 0.75F);
				mc.fontRenderer.drawString(
				g == 0 ? StatCollector.translateToLocal("compas1.N.name") :
				g == 90 ? StatCollector.translateToLocal("compas1.E.name") :
				g == 180 ? StatCollector.translateToLocal("compas1.S.name") :
				g == 270 ? StatCollector.translateToLocal("compas1.W.name") :
				(Integer.toString(g)),
				(g == 0 ? (Minecraft.getMinecraft().fontRenderer.getUnicodeFlag() ? -2 : -3) : g == 90 || g == 180 || g == 270 ? 0 : -4), 2, 0xFFFFFF);
				GL11.glPopMatrix();

				mc.fontRenderer.drawString("|", g == 0 ? -1 : 1, 5, 0xDDDDDD);

				GL11.glPopMatrix();
			}

			GL11.glDisable(GL11.GL_SCISSOR_TEST);

			//mc.fontRenderer.drawString(Integer.toString((int)deg), (event.resolution.getScaledWidth() / 2) - (mc.fontRenderer.getStringWidth(Integer.toString((int)deg)) / 2), 20, 0xFFFFFF);

			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glPopMatrix();
			break;
		default:return;
		}
	}

}
