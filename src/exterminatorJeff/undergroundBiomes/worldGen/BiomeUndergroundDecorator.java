package exterminatorJeff.undergroundBiomes.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import exterminatorJeff.undergroundBiomes.common.BiomeGenRule;
import exterminatorJeff.undergroundBiomes.common.PerlinNoiseGenerator;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;

public class BiomeUndergroundDecorator {
	
	boolean oreVeins = true;
	
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
    
    
    //veins ores
    protected WorldGenUndergroundOreVeins coalGenVeins;
    protected WorldGenUndergroundOreVeins ironGenVeins;
    protected WorldGenUndergroundOreVeins goldGenVeins;   
    
    BiomeGenUndergroundBase[] undergroundBiomesForGeneration;
    public boolean compatibilityMode = false;
    public WorldGenManager worldGen;
    
	PerlinNoiseGenerator cavernNoise;
	int maxHeight = 60;
	int octaves = 2;
	double frequency = 0.005;
	double amplitude = 1.0;
	boolean normalized = true;
	boolean done = false;    
    
    public BiomeUndergroundDecorator(BiomeGenUndergroundBase biome){
    	if(UndergroundBiomes.oreVeins == false){
    		oreVeins = false;
    	}
    	
    	cavernNoise = new PerlinNoiseGenerator(UndergroundBiomes.getWorld().getSeed() * 10);
    	
    	undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
    	
    	worldGen = UndergroundBiomes.getWorldGenManager();
    	
    	this.biome = biome;
    	
        //veins
        this.coalGenVeins = new WorldGenUndergroundOreVeins(Block.oreCoal.blockID, biome.fillerBlock, 0, 90);
        this.ironGenVeins = new WorldGenUndergroundOreVeins(Block.oreIron.blockID, biome.fillerBlock, 0, 60);
        this.goldGenVeins = new WorldGenUndergroundOreVeins(Block.oreGold.blockID, biome.fillerBlock, 0, 30);

    	
    }
    
    public void decorate(World par1World, Random par2Random, int x, int y, boolean[] ores)
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
            this.decorate(ores);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }
    
    public void decorate(boolean[] ores){
    	worldGen = UndergroundBiomes.getWorldGenManager();
    	replaceBlocksForUndergroundBiome(chunk_X, chunk_Z);
    	//currentWorld.getChunkProvider().loadChunk(chunk_X, chunk_Z);
    	//this.generateOres(ores);
    }
    
    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
            int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
        }
    }

    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     */
    protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
            int var7 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
        }
    }

    /**
     * Generates ores in the current chunk
     */
    protected void generateOres(boolean[] ores)
    {
        if(oreVeins){
        long seed = currentWorld.getSeed();
	        if(ores[0]){
	        	ironGenVeins.generate(currentWorld, randomGenerator, chunk_X, chunk_Z, 0.5, 0.5, seed + 1000, 0.125);
	        }
	        if(ores[1]){
	        	coalGenVeins.generate(currentWorld, randomGenerator, chunk_X, chunk_Z, 0.5, 0.6, seed + 1001, 0.125);
	        }
	        if(ores[2]){
	        	goldGenVeins.generate(currentWorld, randomGenerator, chunk_X, chunk_Z, 0.6, 0.75, seed + 1002, 0.06);
	        }
        }else if(!oreVeins){

        }
    }
    
	public void replaceBlocksForUndergroundBiome(int par1, int par2){
		BiomeGenRule[] rule = worldGen.BiomeGenRules;
		
		if(undergroundBiomesForGeneration == null){
			undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
		}
		undergroundBiomesForGeneration = this.worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par1, par2, 16, 16);
    	for(int x = par1; x < par1 + 16; x++){
    		for(int y = par2; y < par2 + 16; y++){
    			//current underground Biome
    			BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[(x-par1) + (y-par2) * 16];
    			//current surface biome
    			BiomeGenBase currentSurfaceBiome = currentWorld.getBiomeGenForCoords(x-par1, y-par2);
    			
    			for(int z = 0; z < 128; z++){
    				if(UndergroundBiomes.testMode1){
    					if(currentWorld.getBlockId(x, z, y) != 0){
    						currentWorld.setBlock(x, z, y, currentBiome.fillerBlock, currentBiome.fillerBlockMetadata, 0x2);
    					}
    				}else if(rule[currentSurfaceBiome.biomeID] != null && currentWorld.getBlockId(x, z, y) == Block.stone.blockID){//has a custom rule defined
    					if(currentWorld.getBlockId(x, z, y) != 0){//block is not air
    						if(currentBiome.hasStrata){//underground biome has strata?
	    						int variation = (int) (currentBiome.strataNoise.noise(x/55.533, y/55.533, 3, 1, 0.5) * 10 - 5);
	    						int[] strata = currentBiome.getStrataBlockAtLayer(z + variation);
	    						currentWorld.setBlock(x, z, y, strata[0], strata[1], 0x2);
    						}
	    					int[] strata = currentBiome.getStrataBlockAtLayerWithRule(z, rule[currentSurfaceBiome.biomeID].ruleLayers);//strata layer for custom rule
	    					if(strata != null){
	    						currentWorld.setBlock(x, z, y, strata[0], strata[1], 0x2);
	    					}
    					}
    				}else{//no custom rule for this surface biome
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
					/*double noise = cavernNoise.noise(x/522.56783, z, y/522.56783, octaves, frequency, amplitude, normalized);
					if(noise > 0.0051 && z < maxHeight){
						boolean success = currentWorld.setBlock(x, z, y, Block.glowStone.blockID);
						
					}*/
    			}
    		}
    	}
    }


}
