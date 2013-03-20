package exterminatorJeff.undergroundBiomes;

import net.minecraft.world.WorldType;

public abstract class GenLayerUnderground
{
    /** seed from World#getWorldSeed that is used in the LCG prng */
    public long worldGenSeed;

    /** parent GenLayer that was provided via the constructor */
    protected GenLayerUnderground parent;

    /**
     * final part of the LCG prng that uses the chunk X, Z coords along with the other two seeds to generate
     * pseudorandom numbers
     */
    private long chunkSeed;

    /** base seed to the LCG prng provided via the constructor */
    private long baseSeed;
    
    private static int biomeSize = 3;

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayerUnderground[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType, int size)
    {
    	
        GenLayerUndergroundIsland var3 = new GenLayerUndergroundIsland(1L);   
        GenLayerUndergroundFuzzyZoom var9 = new GenLayerUndergroundFuzzyZoom(2000L, var3);
        GenLayerUndergroundAddIsland var10 = new GenLayerUndergroundAddIsland(1L, var9);
        
        GenLayerUndergroundZoom var11 = new GenLayerUndergroundZoom(2001L, var10);
        var10 = new GenLayerUndergroundAddIsland(2L, var11);
        
        var11 = new GenLayerUndergroundZoom(2002L, var10);
        var10 = new GenLayerUndergroundAddIsland(3L, var11);
        var11 = new GenLayerUndergroundZoom(2003L, var10);
        var10 = new GenLayerUndergroundAddIsland(4L, var11);
        int var4 = size;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            var4 = 64;
        }

        GenLayerUnderground var5 = GenLayerUndergroundZoom.func_75915_a(1000L, var11, 0);
        var5 = GenLayerUndergroundZoom.func_75915_a(1000L, var5, var4 + 2);
        
        GenLayerUndergroundBiomes var17 = new GenLayerUndergroundBiomes(200L, var5, par2WorldType);
        
        
        GenLayerUndergroundSmooth var15 = new GenLayerUndergroundSmooth(1000L, var17);
        GenLayerUnderground var6 = GenLayerUndergroundZoom.func_75915_a(1000L, var15, biomeSize);
        var6 = GenLayerUndergroundZoom.func_75915_a(1000L, var6, 2);
        /*Object var18 = new GenLayerHills(1000L, var6);

        for (int var7 = 0; var7 < var4; ++var7)
        {
            var18 = new GenLayerZoom((long)(1000 + var7), (GenLayerUnderground)var18);

            if (var7 == 0)
            {
                var18 = new GenLayerAddIsland(3L, (GenLayerUnderground)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerShore(1000L, (GenLayerUnderground)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerSwampRivers(1000L, (GenLayerUnderground)var18);
            }
        }*/
        
        

        GenLayerUndergroundSmooth var19 = new GenLayerUndergroundSmooth(1000L, (GenLayerUnderground)var6);
        
        GenLayerUndergroundVoronoiZoom var8 = new GenLayerUndergroundVoronoiZoom(10L, var19);
        var8.initWorldGenSeed(par0);
        return new GenLayerUnderground[] {var19, var8, var19};
    }

    public GenLayerUnderground(long par1)
    {
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    public void initWorldGenSeed(long par1)
    {
        this.worldGenSeed = par1;

        if (this.parent != null)
        {
            this.parent.initWorldGenSeed(par1);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }

    /**
     * Initialize layer's current chunkSeed based on the local worldGenSeed and the (x,z) chunk coordinates.
     */
    public void initChunkSeed(long par1, long par3)
    {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }

    /**
     * returns a LCG pseudo random number from [0, x). Args: int x
     */
    protected int nextInt(int par1)
    {
        int var2 = (int)((this.chunkSeed >> 24) % (long)par1);

        if (var2 < 0)
        {
            var2 += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
        return var2;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public abstract int[] getInts(int var1, int var2, int var3, int var4);
}
