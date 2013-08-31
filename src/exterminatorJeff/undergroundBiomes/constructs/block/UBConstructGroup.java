/*
 * author Zeno410
 * This is an abstract class for defining a group of similar objects based on the
 * Underground Biomes block classes
 * Each object is created by a combination of a block and a tileEntity which has
 * an integer index into a master list of UB blocks
 */

package exterminatorJeff.undergroundBiomes.constructs.block;

import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;

import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlock;
import exterminatorJeff.undergroundBiomes.constructs.util.UndergroundBiomesBlockList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class UBConstructGroup {
        public Integer constructID;
        public int baseID;
        public BlockMetadataBase baseBlock;
        public final String name;
        public Block construct; // the block representing the group of constructs

        public UBConstructGroup(String _name) {
            name  = _name;
        }

        public void define(int _constructID) {
            constructID = _constructID;
            construct = definedBlock();
            GameRegistry.registerBlock( construct, itemClass(),name);
            addRecipes();

        }

        public void addRecipes() {
            for (int ubIndex = 0; ubIndex <UndergroundBiomesBlockList.detailedBlockCount;ubIndex++) {
                addRecipe(new ProductItemDefiner(ubIndex),new StoneItemDefiner(ubIndex));
            }
        }

        abstract Block definedBlock(); // this should return the base block for the group

        abstract Class<? extends ItemBlock> itemClass(); // the class to handle items

        abstract void addRecipe(ProductItemDefiner product, StoneItemDefiner stone); //

        public BlockMetadataBase baseBlock() {
            return baseBlock;
        }

        /* the next two inner classes wrap references out of the package so descendent
        *  classes have minimal imports and couplings */
        
        class StoneItemDefiner {
            // this inner class hides the references to UndergroundBiomesBlockList
            final int stoneIndex;

            StoneItemDefiner(int _stoneIndex) {stoneIndex = _stoneIndex;}

            public final ItemStack stackOf(int items) {
                UndergroundBiomesBlock stone = UndergroundBiomesBlockList.indexed(stoneIndex);
                return new ItemStack(stone.ubBlock,items,stone.metadata);
            }

            public final ItemStack one() {return stackOf(1);}
        }

        class ProductItemDefiner {
            // this inner class wraps the stoneIndex for "standard" UBConstruct item group
            // the assumption is that a damage index of x indicates that stone
            final int stoneIndex;

            ProductItemDefiner(int _stoneIndex) {stoneIndex = _stoneIndex;}

            public final ItemStack stackOf(int items) {
                return new ItemStack(construct,items,stoneIndex);
            }

            public final ItemStack one() {return stackOf(1);}
        }

}
