package exterminatorJeff.undergroundBiomes.worldGen;

import java.util.Random;

import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.SimplexNoiseGenerator;
public class WorldGenUndergroundOreVeins{
	
	Random randomGenerator;
	int blockID;
	int replacementBlockID;
	long seed;
	exterminatorJeff.undergroundBiomes.common.SimplexNoiseGenerator noise1;
	exterminatorJeff.undergroundBiomes.common.SimplexNoiseGenerator noise2;
	int minHeight;
	int maxHeight;
	int midHeight;
	boolean initialSetup = false;
	
	
	public WorldGenUndergroundOreVeins(int par1, int par2, int minHeight, int maxHeight){
		this.blockID = par1;
		this.replacementBlockID = par2;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		midHeight = maxHeight - ((maxHeight - minHeight)/2);
	}

	
	public boolean generate(World world, Random random, int chunkX, int chunkZ, double amount1, double amount2, long oreSeed, double chance){
		if(initialSetup == false){
			this.seed = world.getSeed();
			this.randomGenerator = new Random(seed);
			noise1 = new SimplexNoiseGenerator(seed * oreSeed);
			noise2 = new SimplexNoiseGenerator(seed * oreSeed);
			initialSetup = true;
		}
		chance = 1 - chance; 
		int blockX = chunkX;
		int blockZ = chunkZ;
		double max = 0;
		for(int x = blockX; x < (blockX + 16); x++){
			for(int z = blockZ; z < (blockZ + 16); z++){
				double noise2d = noise2.noise(x/100.35, z/100.35, 3, 1, 0.5);
				if(noise2d > amount1){
				
					for(int y = minHeight; y < maxHeight; y++){
						
						double noise3d = noise1.noise(x/35.256, y/20.256, z/35.256, 3, 1, 0.5);
						noise3d = noise3d / 1.8;
						if(noise3d > max){
							max = noise3d;
						}

						if(y < midHeight){//below
							double percentage = ((double)(y-minHeight) / ((double)midHeight-minHeight));
							noise3d = (noise3d * (percentage)) + 0.15;
						}else{//above
							double percentage = (((double)y-midHeight) / ((double)maxHeight-midHeight));
							percentage = 1 - percentage;
							noise3d = (noise3d * (percentage)) + 0.15;
						}
						if(noise3d > amount2){
							if(world.getBlockId(x, y, z) == replacementBlockID){
								if(random.nextDouble() > chance){
									world.setBlock(x, y, z, this.blockID);
								}
							}
						}
					}
				}
	
			}
		}
		//System.out.println("Max " + max);
		
		
		return false;
	}

}
