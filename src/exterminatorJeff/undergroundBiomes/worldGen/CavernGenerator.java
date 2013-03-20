package exterminatorJeff.undergroundBiomes.worldGen;

import exterminatorJeff.undergroundBiomes.common.PerlinNoiseGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class CavernGenerator {
	
	World world;
	PerlinNoiseGenerator cavernNoise;
	int maxHeight = 100;
	int octaves = 2;
	double frequency = 0.005;
	double amplitude = 1.0;
	boolean normalized = true;
	boolean done = false;
	
	public CavernGenerator(World world){
		this.world = world;
		System.out.println(world.getWorldInfo().getWorldName());
		cavernNoise = new PerlinNoiseGenerator(world.getSeed() * 10);
	}
	
	public void generate(int chunkX, int chunkZ){
		System.out.println(chunkX + " " + chunkZ);
		for(double x = chunkX; x < chunkX + 16; x++){
			for(double z = chunkZ; z < chunkZ + 16; z++){
				for(double y = 80; y < maxHeight; y++){
					double noise = cavernNoise.noise(x, y, z, octaves, frequency, amplitude, normalized);
					if(noise > -0.9){
						boolean success = world.setBlock((int)x, (int)y, (int)z, Block.blockDiamond.blockID);
						if(!success)
						//System.out.println("placed at" + x + " " + y + " " + z); 
						done = true;
					}
				}
			}
		}
	}

}
