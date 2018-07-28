package timaxa007.compas.v1;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = CompasMod.MODID, name = CompasMod.NAME, version = CompasMod.VERSION)
public class CompasMod {

	public static final String
	MODID = "compas1",
	NAME = "Compas Mod",
	VERSION = "1.0";

	@Mod.Instance(MODID)
	public static CompasMod instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new Events());
	}

}
