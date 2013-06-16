package exterminatorJeff.undergroundBiomes.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;

public class ItemMetadataBlock extends ItemBlock
{
    private Block theBlock;

    public ItemMetadataBlock(Block block)
    {
        super(block.blockID - 256);
        this.theBlock = block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    public int getMetadata(int meta)
    {
        return meta | 8;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
        return theBlock.getIcon(2, meta);
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        String name = ((BlockMetadataBase)theBlock).getBlockName(stack.getItemDamage());
        return super.getUnlocalizedName() + "." + name;
    }
}
