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

    public int idDropped(int metadata, Random random, int par3)
    {
        return metadata == 4 ? UndergroundBiomes.ligniteCoal.itemID : this.blockID;
    }

    public int damageDropped(int metadata)
    {
        return metadata == 4 ? 0 : metadata;
    }
    
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (fortune == 0 || meta != 4) return 1;
        // Lignite is affected by fortune: Fortune III gives up to 4 items
        int j = random.nextInt(fortune + 2);
        return (j < 1) ? 1 : j;
    }

    public String getBlockName(int index)
    {
        return blockName[index & 7];
    }
}
