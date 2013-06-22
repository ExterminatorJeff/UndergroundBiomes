package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStep;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousStoneSlab extends BlockStep{
    
    public BlockIgneousStoneSlab(int par1, boolean par2, int texture){
        super(par1, par2);
        if(!par2){
            this.setCreativeTab(UndergroundBiomes.tabModBlocks);
        }else{
            this.setCreativeTab(null);
        }
        this.blockIndexInTexture = texture;
        this.useNeighborBrightness[par1] = true;
    }


    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4){
        return UndergroundBiomes.igneousStoneBrick.getBlockHardness(par1World, par2, par3, par4)+0.25f;
    }
    
    @Override
    protected ItemStack createStackedBlock(int metadata) {
        return new ItemStack(UndergroundBiomes.igneousBrickSlabHalf.blockID, 2, metadata & 7);
    }
    
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return this.blockIndexInTexture + (meta & 7);
    }
    
    public String getTextureFile()
    {
        return UndergroundBiomes.textures;
    }

    @Override
    public int idPicked(World par1World, int par2, int par3, int par4){
        return UndergroundBiomes.igneousBrickSlabHalf.blockID;
    }
    
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMetadata(par2, par3, par4) & 7;
    }
    
    @Override
    public int idDropped(int metadata, Random random, int par3)
    {
        return UndergroundBiomes.igneousBrickSlabHalf.blockID;
    }

    public String getBlockName(int index)
    {
        return ((BlockMetadataBase)UndergroundBiomes.igneousStoneBrick).getBlockName(index);
    }

}
