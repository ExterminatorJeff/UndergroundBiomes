package exterminatorJeff.undergroundBiomes.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;

public class BiomeUndergroundDecorator {
	
    /** The world the BiomeDecorator is currently decorating */
    protected World currentWorld;

    /** The Biome Decorator's random number generator. */
    protected Random randomGenerator;

    /** The X-coordinate of the chunk currently being decorated */
    protected int chunk_X;

    /** The Z-coordinate of the chunk currently being decorated */
    protected int chunk_Z;

    /** The biome generator object. */
    protected BiomeGenUndergroundBase biome;
    
    BiomeGenUndergroundBase[] undergroundBiomesForGeneration;
    public boolean compatibilityMode = false;
    public WorldGenManager worldGen;
    
	int maxHeight = 60;
	int octaves = 2;
	double frequency = 0.005;
	double amplitude = 1.0;
	boolean normalized = true;
	boolean done = false;    
    
    public BiomeUndergroundDecorator(BiomeGenUndergroundBase biome){
    	
    	undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
    	
    	worldGen = UndergroundBiomes.getWorldGenManager();
    	
    	this.biome = biome;
    }
    
    public void decorate(World par1World, Random par2Random, int x, int y)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = par1World;
            this.randomGenerator = par2Random;
            this.chunk_X = x;
            this.chunk_Z = y;
            this.decorate();
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }
    
    public void decorate(){
    	worldGen = UndergroundBiomes.getWorldGenManager();
    	replaceBlocksForUndergroundBiome(chunk_X, chunk_Z);
    	//currentWorld.getChunkProvider().loadChunk(chunk_X, chunk_Z);
    }
    
	public void replaceBlocksForUndergroundBiome(int par1, int par2){
		
		if(undergroundBiomesForGeneration == null){
			undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
		}
		undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par1, par2, 16, 16);
    	for(int x = par1; x < par1 + 16; x++){
    		for(int y = par2; y < par2 + 16; y++){
    			//current underground Biome
    			BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[(x-par1) + (y-par2) * 16];
    			
    			for(int z = 0; z < 128; z++){
    				if(UndergroundBiomes.testMode1){
    					if(currentWorld.getBlockId(x, z, y) != 0){
    						currentWorld.setBlock(x, z, y, currentBiome.fillerBlock, currentBiome.fillerBlockMetadata, 0x2);
    					}
    				}else{
	    				if(currentWorld.getBlockId(x, z, y) == Block.stone.blockID){
	    					if(UndergroundBiomes.testMode2){
	    						currentWorld.setBlock(x, z, y, 0, 0, 0x2);
	    					}else{
		    					if(currentBiome.hasStrata){
		    						int variation = (int) (currentBiome.strataNoise.noise(x/55.533, y/55.533, 3, 1, 0.5) * 10 - 5);
		    						int[] strata = currentBiome.getStrataBlockAtLayer(z + variation);
		    						currentWorld.setBlock(x, z, y, strata[0], strata[1], 0x02);
		    					}else{
		    						currentWorld.setBlock(x, z, y, currentBiome.fillerBlock);
		    					}
	    					}
	    				}
    				}
    			}
    		}
    	}
    }


}
