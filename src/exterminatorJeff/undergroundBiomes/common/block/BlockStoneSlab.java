package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStep;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockStoneSlab extends BlockStep
{
    protected final boolean isDoubleSlab;
    protected final BlockMetadataBase referenceBlock;
    
    public BlockStoneSlab(int par1, boolean isDouble, int texture, Block refBlock)
    {
        super(par1, isDouble);
        this.setCreativeTab(isDouble ? null : UndergroundBiomes.tabModBlocks);
        isDoubleSlab = isDouble;
        referenceBlock = (BlockMetadataBase)refBlock;
        this.blockIndexInTexture = texture;
        this.useNeighborBrightness[par1] = true;
    }

    public float getBlockHardness(World par1World, int x, int y, int z)
    {
        return referenceBlock.getBlockHardness(par1World, x, y, z);
    }
    
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return referenceBlock.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(getHalfSlab(), 2, metadata & 7);
    }
    
    private int getHalfSlab()
    {
        if (!isDoubleSlab) return blockID;
        if (blockID == UndergroundBiomes.igneousBrickSlabFull.blockID) return UndergroundBiomes.igneousBrickSlabHalf.blockID;
        return UndergroundBiomes.metamorphicBrickSlabHalf.blockID;
    }

    public int idPicked(World world, int x, int y, int z)
    {
        return getHalfSlab();
    }

    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & 7;
    }
    
    public int idDropped(int metadata, Random random, int fortune)
    {
        return getHalfSlab();
    }

    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return this.blockIndexInTexture + (meta & 7);
    }
    
    public String getTextureFile()
    {
        return UndergroundBiomes.textures;
    }

    public String getFullSlabName(int index)
    {
        return super.getBlockName() + "." + referenceBlock.getBlockTypeName(index);
    }

}
