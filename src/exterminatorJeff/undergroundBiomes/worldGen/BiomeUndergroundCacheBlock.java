package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.common.UBiomeCache;

public class BiomeUndergroundCacheBlock {
	
    /** The array of biome types stored in this BiomeCacheBlock. */
    public BiomeGenUndergroundBase[] biomes;

    /** The x coordinate of the BiomeCacheBlock. */
    public int xPosition;

    /** The z coordinate of the BiomeCacheBlock. */
    public int zPosition;

    /** The last time this BiomeCacheBlock was accessed, in milliseconds. */
    public long lastAccessTime;

    /** The BiomeCache object that contains this BiomeCacheBlock */
    final UBiomeCache theBiomeCache;

    public BiomeUndergroundCacheBlock(UBiomeCache par1BiomeCache, int par2, int par3)
    {
        this.theBiomeCache = par1BiomeCache;
        this.biomes = new BiomeGenUndergroundBase[256];
        this.xPosition = par2;
        this.zPosition = par3;
        theBiomeCache.worldGen.getUndergroundBiomeGenAt(this.biomes, par2 << 4, par3 << 4, 16, 16, false);
    }

    /**
     * Returns the BiomeGenBase related to the x, z position from the cache block.
     */
    public BiomeGenUndergroundBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomes[par1 & 15 | (par2 & 15) << 4];
    }

}
