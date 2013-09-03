/**
 *
 * @author Zeno410
 */

package exterminatorJeff.undergroundBiomes.constructs.block;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBWall;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.oredict.ShapedOreRecipe;

public class UBWallGroup  extends UBConstructGroup {

    public UBWallGroup() {super("wall");}

    void addRecipe(ProductItemDefiner product, StoneItemDefiner stone) {
            GameRegistry.addRecipe(new ShapedOreRecipe(
                    product.stackOf(6), "   ", "XXX", "XXX", 'X', stone.one()));
    }

    Class<? extends ItemBlock> itemClass() {
        return ItemUBWall.class;
    }

    Block definedBlock() {
        return new UBWallBase(constructID,baseBlock());
    }

}
