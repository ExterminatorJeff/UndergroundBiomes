/**
 *
 * @author Zeno410
 *
 * Constructor for the button group
 */
package exterminatorJeff.undergroundBiomes.constructs.block;
import exterminatorJeff.undergroundBiomes.constructs.item.ItemUBButton;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.oredict.ShapedOreRecipe;

public class UBButtonGroup extends UBConstructGroup {

    public UBButtonGroup() {super("button");}

    void addRecipe(ProductItemDefiner product,StoneItemDefiner stone) {

            GameRegistry.addRecipe(new ShapedOreRecipe(
                    product.stackOf(2), "   ", " X ", " X ", 'X', stone.one()));
    }

    Class<? extends ItemBlock> itemClass() {
        return ItemUBButton.class;
    }

    Block definedBlock() {
        return new UndergroundBiomesButton(constructID);
    }

}

