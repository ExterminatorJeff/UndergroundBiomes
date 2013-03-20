package exterminatorJeff.undergroundBiomes.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exterminatorJeff.undergroundBiomes.worldGen.BiomeGenUndergroundBase;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundCacheBlock;
import exterminatorJeff.undergroundBiomes.worldGen.BiomeUndergroundDecorator;
import exterminatorJeff.undergroundBiomes.worldGen.CavernGenerator;
import exterminatorJeff.undergroundBiomes.worldGen.GenLayerUnderground;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class WorldGenManager {
	
	private GenLayerUnderground genUndergroundBiomes;
    
    private GenLayerUnderground undergroundBiomeIndexLayer;
    
    private UBiomeCache biomeCache;
    
    private byte[] blockUndergroundBiomeArray;
    
    private int biomeSize;
    
    public BiomeGenRule[] BiomeGenRules;
    
    CavernGenerator cavernGenerator;
    
    public World world;
	
	public WorldGenManager(long par1, WorldType par3WorldType, World world){

		this.biomeCache = new UBiomeCache(this);
		
		GenLayerUnderground[] gen = GenLayerUnderground.initializeAllBiomeGenerators(par1, par3WorldType, UndergroundBiomes.biomeSize);
        this.genUndergroundBiomes = gen[0];
        this.undergroundBiomeIndexLayer = gen[1];
        
        //add custom rules for above ground biomes
        BiomeGenRules = new BiomeGenRule[256];
        for(BiomeGenRule rule: UndergroundBiomes.biomeRules){
        	BiomeGenRules[rule.biomeID] = rule;
        }
        
        cavernGenerator = new CavernGenerator(world);
	}
	
	
	public void onBiomeDecorate(DecorateBiomeEvent event){
		this.blockUndergroundBiomeArray = new byte[256];
		getUndergroundBiomeGenAt(event.chunkX, event.chunkZ).decorate(event.world, event.rand, event.chunkX, event.chunkZ);
		//cavernGenerator.generate(event.chunkX, event.chunkZ);
	}
	
	public BiomeGenUndergroundBase getUndergroundBiomeGenAt(int par1, int par2)
    {
        return this.biomeCache.getUndergroundBiomeGetAt(par1, par2);
    }
	
    public BiomeGenUndergroundBase[] loadUndergroundBlockGeneratorData(BiomeGenUndergroundBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getUndergroundBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }
    
    public BiomeGenUndergroundBase[] getUndergroundBiomeGenAt(BiomeGenUndergroundBase[] biomesArrayPar, int par2, int par3, int par4, int par5, boolean par6)
    {
	        IntCache.resetIntCache();
	        
	        BiomeGenUndergroundBase[] biomesArray = biomesArrayPar;
	
	        if (biomesArray == null || biomesArray.length < par4 * par5)
	        {
	            biomesArray = new BiomeGenUndergroundBase[par4 * par5];
	        }
	
	        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0){
	        	
	        	BiomeGenUndergroundBase[] var9 = this.biomeCache.getCachedUndergroundBiomes(par2, par3);
	            System.arraycopy(var9, 0, biomesArray, 0, par4 * par5);
	            
	            return biomesArray;
	            
	        }else{
	        	
	            int[] var7 = this.undergroundBiomeIndexLayer.getInts(par2, par3, par4, par5);
	
	            for (int var8 = 0; var8 < par4 * par5; ++var8){
	                biomesArray[var8] = BiomeGenUndergroundBase.biomeList[var7[var8]];
	            }
	
	       return biomesArray;
        }
   
    }
    
    public ChunkPosition findUndergroundBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
    {
        IntCache.resetIntCache();
        int var6 = par1 - par3 >> 2;
        int var7 = par2 - par3 >> 2;
        int var8 = par1 + par3 >> 2;
        int var9 = par2 + par3 >> 2;
        int var10 = var8 - var6 + 1;
        int var11 = var9 - var7 + 1;
        int[] var12 = this.genUndergroundBiomes.getInts(var6, var7, var10, var11);
        ChunkPosition var13 = null;
        int var14 = 0;

        for (int var15 = 0; var15 < var12.length; ++var15)
        {
            int var16 = var6 + var15 % var10 << 2;
            int var17 = var7 + var15 / var10 << 2;
            BiomeGenUndergroundBase var18 = BiomeGenUndergroundBase.biomeList[var12[var15]];

            if (par4List.contains(var18) && (var13 == null || par5Random.nextInt(var14 + 1) == 0))
            {
                var13 = new ChunkPosition(var16, 0, var17);
                ++var14;
            }
        }

        return var13;
    }
    
    public BiomeGenUndergroundBase getUndergroundBiomeGenForWorldCoords(int par1, int par2, int chunkX, int chunkY)
    {
    	int xPosition = chunkX + par1;
    	int zPosition = chunkY + par2;
    	
        int var4 = this.blockUndergroundBiomeArray[par2 << 4 | par1] & 255;

        if (var4 == 255)
        {
        	BiomeGenUndergroundBase var5 = getUndergroundBiomeGenAt((xPosition << 4) + par1, (zPosition << 4) + par2);
            var4 = var5.biomeID;
            this.blockUndergroundBiomeArray[par2 << 4 | par1] = (byte)(var4 & 255);
        }

        return BiomeGenUndergroundBase.biomeList[var4];
    }
    
    

}


