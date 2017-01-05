/**
 *
 * @author Zeno410
 *
 * Constructor for the Stairs group
 */

package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBStairs;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.oredict.ShapedOreRecipe;


public class UBStairsGroup extends UBConstructGroup {
    
    public UBStairsGroup() {super("stairs");}

    void addRecipe(ProductItemDefiner product, StoneItemDefiner stone) {
            GameRegistry.addRecipe(new ShapedOreRecipe(
                    product.stackOf(4), "XXX", " XX", "  X", 'X', stone.one()));
    }

    Class<? extends ItemBlock> itemClass() {
        return ItemUBStairs.class;
    }

    Block definedBlock() {
        int metadata = 0;// needed for BlockStairs ancestor
        return new UBStairsBase(constructID,baseBlock(),metadata);
    }

}
