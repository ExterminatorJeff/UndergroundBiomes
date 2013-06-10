package exterminatorJeff.undergroundBiomes.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.LongHashMap;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundCacheBlock;

public class UBiomeCache{

    private List cacheUnderground = new ArrayList();
    private LongHashMap undergroundCacheMap = new LongHashMap();
    public WorldGenManager worldGen;
    
    public UBiomeCache(WorldGenManager gen){
        this.worldGen = gen;
    }
    
    public BiomeUndergroundCacheBlock getUndergroundBiomeCacheBlock(int par1, int par2)
    {
        par1 >>= 4;
        par2 >>= 4;
        long var3 = (long)par1 & 4294967295L | ((long)par2 & 4294967295L) << 32;
        BiomeUndergroundCacheBlock var5 = (BiomeUndergroundCacheBlock)this.undergroundCacheMap.getValueByKey(var3);

        if (var5 == null)
        {
            var5 = new BiomeUndergroundCacheBlock(this, par1, par2);
            this.undergroundCacheMap.add(var3, var5);
            this.cacheUnderground.add(var5);
        }

        var5.lastAccessTime = System.currentTimeMillis();
        return var5;
    }
    
    public BiomeGenUndergroundBase getUndergroundBiomeGetAt(int xPos, int yPos){
        return this.getUndergroundBiomeCacheBlock(xPos, yPos).getBiomeGenAt(xPos, yPos);
    }
    
    public BiomeGenUndergroundBase[] getCachedUndergroundBiomes(int xPos, int yPos){
        return this.getUndergroundBiomeCacheBlock(xPos, yPos).biomes;
    }
    
}
