package exterminatorJeff.undergroundBiomes.client;

import exterminatorJeff.undergroundBiomes.common.CommonProxy;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void registerRenderThings()
    {
        MinecraftForgeClient.preloadTexture(UndergroundBiomes.textures);
    }
}
