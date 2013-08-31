/**
 *
 * @author Zeno410
 */

package exterminatorJeff.undergroundBiomes.constructs.block;
import net.minecraft.block.Block;

public class BlockList {
    public static int blockCount = 4096;

    public static class Search {

        public static Block name(String name) {
            // returns the block with that name or null if not found;
            for (int i = 0;  i <blockCount; i++) {
                Block tested = Block.blocksList[i];
                // skip if no block
                if (tested == null) continue;
                if (tested.getUnlocalizedName().equals(name)) return tested;
            }
            return null;
        }

        public static Block mustFindName(String name) {
            Block result = name(name);
            if (result == null) throw new RuntimeException("no block named "+name);
            return result;
        }
    }

}
