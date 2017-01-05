package exterminatorJeff.undergroundBiomes.constructs.util;

import exterminatorJeff.undergroundBiomes.constructs.block.BlockList;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;


/**
 *
 * @author Zeno410
 */
public class UndergroundBiomesBlockList {
    // namespace for references into the underground biome block system

    public static final int detailedBlockCount = 7*8; // number of different type including the subcategories

    private static UndergroundBiomesBlock [] stored; // a singleton instance
    public final BlockMetadataBase igneousStone = blockMetadataBaseNamed("tile.igneousStone");
    public final BlockMetadataBase igneousCobblestone = blockMetadataBaseNamed("tile.igneousCobblestone");
    public final BlockMetadataBase igneousStoneBrick = blockMetadataBaseNamed("tile.igneousStoneBrick");
    public final BlockMetadataBase metamorphicStone = blockMetadataBaseNamed("tile.metamorphicStone");
    public final BlockMetadataBase metamorphicCobblestone = blockMetadataBaseNamed("tile.metamorphicCobblestone");
    public final BlockMetadataBase metamorphicStoneBrick = blockMetadataBaseNamed("tile.metamorphicStoneBrick");
    public final BlockMetadataBase sedimentaryStone = blockMetadataBaseNamed("tile.sedimentaryStone");

    public UndergroundBiomesBlock[] definitionsIndex;

    private UndergroundBiomesBlock [] definitions() {
        // gets a list of definitions and saves in a particular order
        UndergroundBiomesBlock result [] = new UndergroundBiomesBlock[detailedBlockCount];
        int i = 0;
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(metamorphicStone,j);
        }
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(metamorphicCobblestone,j);
        }
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(metamorphicStoneBrick,j);
        }
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(igneousStone,j);
        }
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(igneousCobblestone,j);
        }
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(igneousStoneBrick,j);
        }
        for (int j = 0; j < 8; j++) {
            result[i++] = new UndergroundBiomesBlock(sedimentaryStone,j);
        }
        return result;
    }

    public static BlockMetadataBase blockMetadataBaseNamed(String name) {
        return (BlockMetadataBase)(BlockList.Search.mustFindName(name));
    }

    public static UndergroundBiomesBlock indexed(int index) {
        return stored[index];
    }
    // not using the singleton pattern because the definitions can't be gotten too early
    public UndergroundBiomesBlockList() {
        definitionsIndex = definitions();
        if (stored != null) throw new RuntimeException();
        stored = this.definitionsIndex;
    }

}
