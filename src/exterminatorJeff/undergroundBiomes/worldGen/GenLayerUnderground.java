package exterminatorJeff.undergroundBiomes.worldGen;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public abstract class GenLayerUnderground extends GenLayer
{
    private static int biomeSize = 3;

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType, int size)
    {
        
        GenLayerIsland var3 = new GenLayerIsland(1L);   
        GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
        GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
        
        GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
        var10 = new GenLayerAddIsland(2L, var11);
        
        var11 = new GenLayerZoom(2002L, var10);
        var10 = new GenLayerAddIsland(3L, var11);
        var11 = new GenLayerZoom(2003L, var10);
        var10 = new GenLayerAddIsland(4L, var11);
        int var4 = size;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            var4 = 64;
        }

        GenLayer var5 = GenLayerZoom.func_75915_a(1000L, var11, 0);
        var5 = GenLayerZoom.func_75915_a(1000L, var5, var4 + 2);
        
        GenLayerUndergroundBiomes var17 = new GenLayerUndergroundBiomes(200L, var5, par2WorldType);
        
        
        GenLayerSmooth var15 = new GenLayerSmooth(1000L, var17);
        GenLayer var6 = GenLayerZoom.func_75915_a(1000L, var15, biomeSize);
        var6 = GenLayerZoom.func_75915_a(1000L, var6, 2);
        
        

        GenLayerSmooth var19 = new GenLayerSmooth(1000L, var6);
        
        GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var19);
        var8.initWorldGenSeed(par0);
        return new GenLayer[] {var19, var8, var19};
    }

    public GenLayerUnderground(long par1)
    {
        super(par1);
    }
}
