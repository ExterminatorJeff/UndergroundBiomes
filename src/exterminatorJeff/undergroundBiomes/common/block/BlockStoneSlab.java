package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStep;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockStoneSlab extends BlockStep
{
    private Icon[] textures;
    protected final boolean isDoubleSlab;
    protected final BlockMetadataBase referenceBlock;
    
    public BlockStoneSlab(int par1, boolean isDouble, Block refBlock)
    {
        super(par1, isDouble);
        this.setCreativeTab(isDouble ? null : UndergroundBiomes.tabModBlocks);
        isDoubleSlab = isDouble;
        referenceBlock = (BlockMetadataBase)refBlock;
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
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int meta)
    {
        return textures[meta & 7];
    }

    public void getSubBlocks(int id, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 8; i++)
        {
            list.add(new ItemStack(id, 1, i));
        } 
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

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[8];
        for(int i = 0; i < 8; i++)
        {
            textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + referenceBlock.getBlockName(i));
        }
    }
    
    public String getFullSlabName(int index)
    {
        return super.getUnlocalizedName() + "." + referenceBlock.getBlockTypeName(index);
    }
}
