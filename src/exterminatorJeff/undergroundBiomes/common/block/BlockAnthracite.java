package exterminatorJeff.undergroundBiomes.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockAnthracite extends Block
{
    public BlockAnthracite(int id, int texture)
    {
        super(id, texture, Material.rock);
        this.setHardness(1.5f);
        this.setResistance(30.0f);
        this.setCreativeTab(UndergroundBiomes.tabModBlocks);
    }
    
    public String getTextureFile()
    {
        return UndergroundBiomes.textures;
    }
}
