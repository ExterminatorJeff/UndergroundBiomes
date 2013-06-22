package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class ItemIgneousStoneSlab extends ItemSlab
{
    private static BlockHalfSlab singleSlab;
    private static BlockHalfSlab doubleSlab;
    
    public static void setSlabs(BlockHalfSlab singleS, BlockHalfSlab doubleS)
    {
        ItemIgneousStoneSlab.singleSlab = singleS;
        ItemIgneousStoneSlab.doubleSlab = doubleS;
    }

    public ItemIgneousStoneSlab(int id, Block block)
    {
        super(id, singleSlab, doubleSlab, (id == doubleSlab.blockID));
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return UndergroundBiomes.igneousBrickSlabHalf.getIcon(0, par1);
    }

    public String getItemName(int index){
        String name = "";
        switch(index){
            case(0): name = "redGraniteBrickSlab";
                break;
            case(1): name = "blackGraniteBrickSlab";
                break;
            case(2): name = "rhyoliteBrickSlab";
                break;
            case(3): name = "andesiteBrickSlab";
                break;
            case(4): name = "gabbroBrickSlab";
                break;
            case(5): name = "basaltBrickSlab";
                break;
            case(6): name = "komatiiteBrickSlab";
                break;
            case(7): name = "daciteBrickSlab";
                break;
            default: name="default";
            
        }
        return getUnlocalizedName() + "." + name;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return getItemName(itemstack.getItemDamage());
    }

}
