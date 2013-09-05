/* Base class for the block that can support stairs of any UB stone
 * Author Zeno410
 */


package exterminatorJeff.undergroundBiomes.constructs.block;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBStairs;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.Icon;
import net.minecraft.item.ItemStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;

public class UBStairsBase extends BlockStairs implements ITileEntityProvider, IconKludgeable {

    public UBStairsBase(
            int theID, BlockMetadataBase _baseBlock, int metadata) {
        super(theID,_baseBlock,metadata);
        this.setUnlocalizedName("stairs");
        this.setLightOpacity(4);
        // make the item here;
        new ItemUBStairs(this);
    }

    public boolean hasTileEntity(int metadata) {return true;}

    public TileEntity createNewTileEntity(World world) {
        return new UndergroundBiomesTileEntity();

    }
    @Override
    public void onBlockAdded(World par1World, int x, int y, int z){
        super.onBlockAdded(par1World, x, y, z);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     *
     * no action; normally removes the TileEntity. However, the tile entity is needed for the item drop
     * procedure. The actual drop is at the end of getBlockDropped. Kludgey.
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){}

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */

    public final UndergroundBiomesTileEntity ubTileEntity(IBlockAccess world, int x, int y, int z) {
        UndergroundBiomesTileEntity result;
        result = (UndergroundBiomesTileEntity)(world.getBlockTileEntity(x, y, z));
        return result;
    }

    public final UndergroundBiomesBlock ubBlock(World world, int x, int y, int z) {
        return UndergroundBiomesBlockList.indexed(ubTileEntity(world,x,y,z).masterIndex());
    }

    public final UndergroundBiomesBlock safeUBBlock(IBlockAccess world, int x, int y, int z) {
        // this is needed for block breaking; there seems to be a call to getHardness after
        // the block is already gone
        UndergroundBiomesTileEntity entity = ubTileEntity(world,x,y,z);
        if (entity == null) return ubBlock(0);
        return UndergroundBiomesBlockList.indexed(ubTileEntity(world,x,y,z).masterIndex());
    }

    public final UndergroundBiomesBlock ubBlock(int reference) {
        return UndergroundBiomesBlockList.indexed(reference);
    }

    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z){
        return safeUBBlock(world,x,y,z).hardness();
    }

    public void setIconKludge(Icon kludged) {
        iconKludgeFromHell = kludged;
        //hitCount = 7;
    }
    private Icon iconKludgeFromHell;
    private int hitCount;
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata){
        //hitCount--;
        //if (hitCount == 0) iconKludgeFromHell= null;
        return iconKludgeFromHell;
        //return ubBlock(metadata).icon();
    }

    @Override
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z) {
        setIconKludge(safeUBBlock(world,x,y,z).icon());
        return super.getMixedBrightnessForBlock(world,x,y,z);
    }
    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side){
        int metadataFromEntity = ((UndergroundBiomesTileEntity)(world.getBlockTileEntity(x, y, z))).masterIndex();
        return ubBlock(metadataFromEntity).icon();
    }

    @Override
    public void getSubBlocks(int id, CreativeTabs tabs, List list){
        //list.add(new ItemStack(id, 1, 0));
    }

    @Override
    public boolean isGenMineableReplaceable(World world, int x, int y, int z, int target){
        return true;
    }

    public float getBlockExplosionResistance(int meta){
        //return block 0 value to avoid a crash
        return 6.0f *ubBlock(0).explosionResistance();
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z,
            double explosionX, double explosionY, double explosionZ){
        return 6.0f *ubBlock(world,x,y,z).explosionResistance();
    }

    public int getRenderType(){return 10;}

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    public ItemStack itemDropped(int metadata, Random random, int fortune, int y){
        return new ItemStack(this.blockID, 1, metadata);
    }

    public String getBlockName(int meta) {
        return ubBlock(meta).name();
    }

     public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        for(int i = 0; i < count; i++)
        {
            int id = idDropped(metadata, world.rand, fortune);
            if (id > 0)
            {
                ret.add(new ItemStack(id, 1, ubTileEntity(world,x,y,z).masterIndex()));
            }
        }

        world.removeBlockTileEntity(x, y, z);
        return ret;
    }

}
