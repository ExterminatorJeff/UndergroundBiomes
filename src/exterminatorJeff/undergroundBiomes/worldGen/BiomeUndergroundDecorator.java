package exterminatorJeff.undergroundBiomes.worldGen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.WorldGenManager;

public class BiomeUndergroundDecorator
{
    /** The world the BiomeDecorator is currently decorating */
    protected World currentWorld;
    
    BiomeGenUndergroundBase[] undergroundBiomesForGeneration;
    
    public BiomeUndergroundDecorator()
    {
        undergroundBiomesForGeneration = new BiomeGenUndergroundBase[256];
    }
    
    public void decorate(World par1World, Random par2Random, int x, int z)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = par1World;
            replaceBlocksForUndergroundBiome(x, z);
            this.currentWorld = null;
        }
    }
    
    public void replaceBlocksForUndergroundBiome(int par_x, int par_z)
    {
        WorldGenManager worldGen = UndergroundBiomes.getWorldGenManager();
        undergroundBiomesForGeneration = worldGen.loadUndergroundBlockGeneratorData(undergroundBiomesForGeneration, par_x, par_z, 16, 16);
        for(int x = par_x; x < par_x + 16; x++)
        {
            for(int z = par_z; z < par_z + 16; z++)
            {
                BiomeGenUndergroundBase currentBiome = undergroundBiomesForGeneration[(x-par_x) + (z-par_z) * 16];
                for(int y = 1; y <= UndergroundBiomes.generateHeight; y++)
                {
                    if(currentWorld.getBlockId(x, y, z) == Block.stone.blockID)
                    {
                        int variation = (int) (currentBiome.strataNoise.noise(x/55.533, z/55.533, 3, 1, 0.5) * 10 - 5);
                        int[] strata = currentBiome.getStrataBlockAtLayer(y + variation);
                        currentWorld.setBlock(x, y, z, strata[0], strata[1], 0x02);
                    }
                }
            }
        }
    }


}
