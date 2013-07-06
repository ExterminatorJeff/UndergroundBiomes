package exterminatorJeff.undergroundBiomes.client;

import java.net.URL;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;

import exterminatorJeff.undergroundBiomes.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
    public void registerRenderThings()
    {
        //MinecraftForgeClient.preloadTexture("/exterminatorJeff/undergroundBiomes/textures/BlockTextures.png");
        //MinecraftForgeClient.preloadTexture("/exterminatorJeff/undergroundBiomes/textures/Items.png");
    }

    public void setUpBlockNames()
    {
        for (Object obj : Minecraft.getMinecraft().func_135016_M().func_135040_d())
        {
            String lang = ((Language)obj).func_135034_a();
            URL urlResource = this.getClass().getResource("/assets/undergroundbiomes/lang/"+lang+".lang");
            if (urlResource != null)
            {
                LanguageRegistry.instance().loadLocalization(urlResource, lang, false);
            }
        }
    }
}
