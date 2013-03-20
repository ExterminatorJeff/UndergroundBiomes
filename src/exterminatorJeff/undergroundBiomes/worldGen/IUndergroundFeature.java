package exterminatorJeff.undergroundBiomes.worldGen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IUndergroundFeature{
	
	public void generate(World world, Chunk chunk);
	
	

}
