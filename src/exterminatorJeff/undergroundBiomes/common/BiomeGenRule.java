package exterminatorJeff.undergroundBiomes.common;

import java.util.ArrayList;

import net.minecraft.block.Block;

import exterminatorJeff.undergroundBiomes.worldGen.StrataLayer;

public class BiomeGenRule {
	
	public ArrayList<StrataLayer> ruleLayers;
	int biomeID;

	public BiomeGenRule(ArrayList<StrataLayer> ruleLayers, int biomeID){
		this.ruleLayers = ruleLayers;
		this.biomeID = biomeID;
	}
	
	public StrataLayer getStrataAtLayer(int layer){
		for(StrataLayer s: ruleLayers){
			if(s.valueIsInLayer(layer)){
				return s;
			}
		}
		return null;
	}
}
