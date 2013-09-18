package exterminatorJeff.undergroundBiomes.common.block;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public abstract class BlockMetadataBase extends Block
{
    protected Icon[] textures = {null, null, null, null, null, null, null, null};

    public BlockMetadataBase(int id)
    {
        super(id, Material.rock);
        this.setCreativeTab(UndergroundBiomes.tabModBlocks);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        for(int i = 0; i < 8; i++)
        {
            textures[i] = iconRegister.registerIcon("undergroundbiomes:" + getBlockName(i));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return textures[metadata & 7];
    }
    
    public void getSubBlocks(int id, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 8; i++)
        {
            list.add(new ItemStack(id, 1, i));
        } 
    }

    public int damageDropped(int metadata)
    {
        return metadata & 7;
    }

    public boolean isGenMineableReplaceable(World world, int x, int y, int z)
    {
        return true;
    }

    public boolean isGenMineableReplaceable(World world, int x, int y, int z, int target)
    {
        return true;
    }

    public float getBlockHardness(int meta)
    {
        return UndergroundBiomes.hardnessModifier;
    }

    public float getBlockExplosionResistance(int meta)
    {
        return UndergroundBiomes.resistanceModifier;
    }

    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return getBlockExplosionResistance(getDamageValue(world, x, y, z) & 7);
    }
    
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return getBlockHardness(getDamageValue(world, x, y, z) & 7);
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(this.blockID, 1, metadata & 7);
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y)
    {
        return createStackedBlock(metadata);
    }

    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = 1;
        ItemStack stack = itemDropped(metadata, world.rand, fortune, y);

        if ((fortune != 0) && (UndergroundBiomes.fortuneAffected.contains(stack.itemID)))
        {
            // Fortune III gives up to 4 items
            int j = world.rand.nextInt(fortune + 2);
            count = (j < 1) ? 1 : j;
        }
        for(int i = 0; i < count; i++)
        {
            ret.add(stack);
        }
        return ret;
    }

    public abstract String getBlockTypeName(int index);

    public abstract boolean hasRareDrops();

    public String getBlockName(int index)
    {
        return getBlockTypeName(index);
    }
}
