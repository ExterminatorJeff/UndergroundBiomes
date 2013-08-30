package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBButton;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;
import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockButton;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.Icon;
import net.minecraft.item.ItemStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

/**
 *
 * @author Zeno410
 */
public class UndergroundBiomesButton extends BlockButton implements ITileEntityProvider, IconKludgeable{

    public UndergroundBiomesButton(int theID) {
        super(theID, true);
        this.setUnlocalizedName("button");
        // make the item here;
        new ItemUBButton(this);
        this.isBlockContainer = true;
    }
    public boolean hasTileEntity(int metadata) {return true;}

    public TileEntity createNewTileEntity(World world) {
        return new UndergroundBiomesTileEntity();

    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){}

    public final UndergroundBiomesTileEntity ubTileEntity(World world, int x, int y, int z) {
        UndergroundBiomesTileEntity result;
        result = (UndergroundBiomesTileEntity)(world.getBlockTileEntity(x, y, z));
        return result;
    }

    public final UndergroundBiomesBlock ubBlock(World world, int x, int y, int z) {
        return UndergroundBiomesBlockList.indexed(ubTileEntity(world,x,y,z).masterIndex());
    }

    public final UndergroundBiomesBlock safeUBBlock(World world, int x, int y, int z) {
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

    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side){
        //logger.logger().log(Level.INFO,"texture " + x + " " + y + " " + z);
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
        //return 6.0f * metadataBase.getBlockExplosionResistance(meta);
        throw new RuntimeException();
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z,
            double explosionX, double explosionY, double explosionZ){
        return 6.0f *ubBlock(world,x,y,z).explosionResistance();
    }

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

    @Override
     public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        for(int i = 0; i < count; i++)
        {
            int id = idDropped(metadata, world.rand, fortune);
            if (id > 0)
                {ret.add(new ItemStack(id, 1, ubTileEntity(world,x,y,z).masterIndex()));}
        }

        world.removeBlockTileEntity(x, y, z);
        return ret;
    }}
