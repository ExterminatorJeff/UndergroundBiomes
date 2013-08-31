package exterminatorJeff.undergroundBiomes.constructs.util;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;


import net.minecraft.util.Icon;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Zeno410
 */

public class UndergroundBiomesBlock {
    // A hook that lets us get to one particular block
    public final BlockMetadataBase ubBlock;
    public final int metadata;
    public UndergroundBiomesBlock(BlockMetadataBase _ubBlock, int _metadata) {
        ubBlock = _ubBlock; metadata = _metadata;
    }

    public final Icon icon() {return ubBlock.getIcon(2, metadata);}

    public final String name() {return ubBlock.getBlockName(metadata);}

    public final float hardness() {return ubBlock.getBlockHardness(metadata);}

    public final float explosionResistance() {return ubBlock.getBlockExplosionResistance(metadata);}

    public final ItemStack stack(int items) {return new ItemStack(ubBlock,items,metadata);}

    public final ItemStack one() {return stack(1);}

    public final String getUnlocalizedName() {
        return ubBlock.getUnlocalizedName()+"."+ubBlock.getBlockTypeName(metadata);
    }

}
