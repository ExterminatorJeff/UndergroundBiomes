package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockMetamorphicStoneSlab extends BlockIgneousStoneSlab{

    public BlockMetamorphicStoneSlab(int par1, boolean par2, int texture)
    {
        super(par1, par2, texture);
    }
    
    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4){
        return UndergroundBiomes.metamorphicStoneBrick.getBlockHardness(par1World, par2, par3, par4)+0.25f;
    }
    
    @Override
    protected ItemStack createStackedBlock(int metadata) {
        return new ItemStack(UndergroundBiomes.metamorphicBrickSlabHalf.blockID, 2, metadata & 7);
    }

    @Override
    public int idPicked(World par1World, int par2, int par3, int par4){
        return UndergroundBiomes.metamorphicBrickSlabHalf.blockID;
    }
    
    @Override
    public int idDropped(int metadata, Random random, int par3)
    {
        return UndergroundBiomes.metamorphicBrickSlabHalf.blockID;
    }

    public String getBlockName(int index)
    {
        return ((BlockMetadataBase)UndergroundBiomes.metamorphicStoneBrick).getBlockName(index);
    }

}
