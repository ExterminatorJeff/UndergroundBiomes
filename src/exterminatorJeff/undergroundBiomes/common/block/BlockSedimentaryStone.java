package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockSedimentaryStone extends BlockMetadataBase
{
    private static final float[] hardness = {0.5f, 0.5f, 0.5f, 0.6f, 0.5f, 1.0f, 1.0f, 0.9f};
    private static final String[] blockName = {
        "limestone", "chalk", "shale", "siltstone", "ligniteBlock", "flint", "greywacke", "chert"
    };

    public BlockSedimentaryStone(int id)
    {
        super(id);
    }
    
    public float getBlockHardness(int meta)
    {
        return 1.5f * hardness[meta];
    }

    public float getBlockExplosionResistance(int meta)
    {
        return 10.0f * hardness[meta];
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune)
    {
        if (metadata & 7 == 4) return new ItemStack(UndergroundBiomes.ligniteCoal.itemID, 1, 0);
        return new ItemStack(this.blockID, 1, metadata & 7);
    }

    public String getBlockName(int index)
    {
        return blockName[index & 7];
    }
}
