package exterminatorJeff.undergroundBiomes.constructs.item;

import exterminatorJeff.undergroundBiomes.constructs.block.IconKludgeable;

import exterminatorJeff.undergroundBiomes.constructs.entity.UndergroundBiomesTileEntity;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author Zeno410
 */
public abstract class ItemUndergroundBiomesConstruct extends ItemBlock {

    public static int NO_PRIOR_METADATA = 0;

    private IconKludgeable theBlock;
    final int blockID;

    public ItemUndergroundBiomesConstruct(Block block){
        super(block.blockID - 256);
        this.theBlock = (IconKludgeable)block;
        blockID = block.blockID;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("wall");
    }

    public ItemUndergroundBiomesConstruct(int ID, Block block){
        this(block);
        if (block.blockID != ID + 256) {
            throw new RuntimeException("Mismatch between item ID " + ID +
                    " and block " + block.getUnlocalizedName() + " block # " + block.blockID);
        }
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        int i1 = world.getBlockId(x, y, z);

        if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
                && (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(world, x, y, z))) {
            if (side == 0) {--y;}

            if (side == 1) { ++y;}

            if (side == 2) {--z;}

            if (side == 3) {++z;}

            if (side == 4) {--x;}

            if (side == 5) {++x;}

        }

        if (par1ItemStack.stackSize == 0) { return false; }

        if (y == 255 && Block.blocksList[this.blockID].blockMaterial.isSolid())
        {return false;}

        if (true) //(World.canPlaceEntityOnSide(this.blockID, x, y, z, false, side, par2EntityPlayer, par1ItemStack))
        {
            Block block = Block.blocksList[this.blockID];
            int j1 = this.getMetadata(par1ItemStack.getItemDamage());
            int k1 = Block.blocksList[this.blockID].onBlockPlaced(world, x, y, z, side, par8, par9, par10, NO_PRIOR_METADATA);

            if (placeBlockAt(par1ItemStack, par2EntityPlayer, world, x, y, z, side, par8, par9, par10, k1))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                --par1ItemStack.stackSize;
            }

            // set the TileEntity data
            UndergroundBiomesTileEntity target = (UndergroundBiomesTileEntity)(world.getBlockTileEntity(x, y, z));
            target.setMasterIndex(par1ItemStack.getItemDamage());
            return true;
        }
        else
        {
            return false;
        }
    }
   
    public final UndergroundBiomesBlock ubBlock(int reference) {
        return UndergroundBiomesBlockList.indexed(reference);
    }
    public int getMetadata(int damage){ return damage;}

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int damage){
        UndergroundBiomesBlock source = ubBlock(damage);
        Icon found = source.icon();
        //throw new RuntimeException();
        return source.icon();
        //return theBlock.getIcon(2, getMetadata(damage));
    }

    private void setIconKludge(Icon kludged) {
        theBlock.setIconKludge(kludged);
    }

    @Override
    public int getDamage(ItemStack stack) {
        int result = super.getDamage(stack);
        setIconKludge(getIconFromDamage(result));
        return result;
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {
        int result = super.getDisplayDamage(stack);
        setIconKludge(getIconFromDamage(result));
        return result;
    }

    private Icon iconKludgeFromHell;
    /* this is a really scary kludge. Item rendering code for some items gets the block
     * without carrying through the damage information. To get around this I intercept the
     * closest previous call which does carry the needing info and set a variable in the block class
    */
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int side){
        int damage = itemStack.getItemDamage();
        iconKludgeFromHell = ubBlock(damage).icon();
        setIconKludge(iconKludgeFromHell);
        return super.getColorFromItemStack( itemStack,  side);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister){}

    public abstract String groupName();

    @Override
    public String getUnlocalizedName(ItemStack stack){
        //int meta = getMetadata(stack.getItemDamage());
        String name = ubBlock(stack.getItemDamage()).getUnlocalizedName();

        //String name = theBlock.getBlockName(meta);
        return name + "." + groupName();
    }

}
