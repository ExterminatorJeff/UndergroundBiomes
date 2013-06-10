package exterminatorJeff.undergroundBiomes.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStep;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BlockIgneousStoneSlab extends BlockStep{
    
    protected Icon[] textures = {null, null, null, null, null, null, null, null};
    
    public BlockIgneousStoneSlab(int par1, boolean par2){
        super(par1, par2);
        if(!par2){
            this.setCreativeTab(UndergroundBiomes.tabModBlocks);
        }else{
            this.setCreativeTab(null);
        }
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
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return textures[par2 & 7];
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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister){
        for(int i = 0; i < 8; i++){
            textures[i] = iconRegister.registerIcon(UndergroundBiomes.texturePath + getBlockName(i));
        }
    }
    
    public String getBlockName(int index)
    {
        return ((BlockMetadataBase)UndergroundBiomes.igneousStoneBrick).getBlockName(index);
    }

}
