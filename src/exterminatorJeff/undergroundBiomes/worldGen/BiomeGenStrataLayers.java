package exterminatorJeff.undergroundBiomes.worldGen;

import net.minecraft.block.Block;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;



public class BiomeGenStrataLayers {
	
	public static StrataLayer[][] layers;
	
	/*Sedimentary rock IDs:
	 * 0 - limestone
	 * 1 - chalk
	 * 2 - shale
	 * 3 - siltstone
	 * 4 - lignite
	 * 5 - flint
	 * 6 - greywacke
	 * 7 - chert
	 */
	
	/*Metamorphic rock IDs:
	 * 2 - marble
	 */
	
	public BiomeGenStrataLayers(){
		layers = new StrataLayer[30][];
		createLayers();
	}
	
	public void createLayers(){
		int sedimentaryBlockID = UndergroundBiomes.sedimentaryStone.blockID;
		int metamorphicBlockID = UndergroundBiomes.metamorphicStone.blockID;
		int igneousBlockID = UndergroundBiomes.igneousStone.blockID;
		layers[0] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 0, 30, 32), new StrataLayer(sedimentaryBlockID, 1, 38, 41), new StrataLayer(sedimentaryBlockID, 6, 65, 70)};
		layers[1] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 33, 36), new StrataLayer(sedimentaryBlockID, 1, 38, 41), new StrataLayer(sedimentaryBlockID, 5, 60, 62), new StrataLayer(sedimentaryBlockID, 7, 75, 80)};
		layers[2] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 3, 30, 35), new StrataLayer(sedimentaryBlockID, 4, 26, 29), new StrataLayer(metamorphicBlockID, 2, 70, 74)};
		layers[3] = new StrataLayer[]{new StrataLayer(Block.sandStone.blockID, 0, 40, 43), new StrataLayer(Block.sand.blockID, 0, 44, 47), new StrataLayer(sedimentaryBlockID, 1, 55, 57)};
		layers[4] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 7, 29, 33), new StrataLayer(sedimentaryBlockID, 5, 42, 44), new StrataLayer(sedimentaryBlockID, 4, 90, 105)};
		layers[5] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 33, 35), new StrataLayer(sedimentaryBlockID, 3, 38, 41), new StrataLayer(sedimentaryBlockID, 6, 72, 79)};
		layers[6] = new StrataLayer[]{new StrataLayer(metamorphicBlockID, 2, 30, 35), new StrataLayer(sedimentaryBlockID, 0, 39, 44), new StrataLayer(Block.sandStone.blockID, 0, 52, 54), new StrataLayer(Block.sand.blockID, 0, 55, 60)};
		layers[7] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 0, 33, 35), new StrataLayer(sedimentaryBlockID, 3, 45, 49), new StrataLayer(sedimentaryBlockID, 6, 80, 85)};
		layers[8] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 1, 30, 32), new StrataLayer(metamorphicBlockID, 2, 70, 74), new StrataLayer(sedimentaryBlockID, 4, 75, 79)};
		layers[9] = new StrataLayer[]{new StrataLayer(sedimentaryBlockID, 2, 31, 35), new StrataLayer(sedimentaryBlockID, 5, 39, 42), new StrataLayer(sedimentaryBlockID, 7, 62, 65)};
		
		//Layers for vanilla stone biomes
		layers[10] = new StrataLayer[]{new StrataLayer(igneousBlockID, 2, 12, 18), new StrataLayer(igneousBlockID, 6, 24, 29), new StrataLayer(metamorphicBlockID, 2, 80, 85)};
		layers[11] = new StrataLayer[]{new StrataLayer(igneousBlockID, 5, 13, 22), new StrataLayer(metamorphicBlockID, 6, 29, 36), new StrataLayer(metamorphicBlockID, 3, 80, 128)};
		
	}

}
