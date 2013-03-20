package exterminatorJeff.undergroundBiomes;

import java.util.Random;

import net.minecraft.world.World;

import exterminatorJeff.undergroundBiomes.common.*;

public abstract class BiomeGenUndergroundBase {
	
	static final BiomeGenStrataLayers strataLayers = new BiomeGenStrataLayers();
	
	public static final BiomeGenUndergroundBase[] biomeList = new BiomeGenUndergroundBase[256];
	
	static int igneousID = UndergroundBiomes.igneousStoneID;
	
	public static final BiomeGenUndergroundBase igneous1 = (new BiomeGenUndergroundIgneous(0, igneousID, 0))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase igneous2 = (new BiomeGenUndergroundIgneous(1, igneousID, 1))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase igneous3 = (new BiomeGenUndergroundIgneous(2, igneousID, 2))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{false, true, false});
	public static final BiomeGenUndergroundBase igneous4 = (new BiomeGenUndergroundIgneous(3, igneousID, 3))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase igneous5 = (new BiomeGenUndergroundIgneous(4, igneousID, 4))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[4]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase igneous6 = (new BiomeGenUndergroundIgneous(5, igneousID, 5))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[5]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase igneous7 = (new BiomeGenUndergroundIgneous(6, igneousID, 6))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[6]).setOres(new boolean[]{false, false, true});
	public static final BiomeGenUndergroundBase igneous8 = (new BiomeGenUndergroundIgneous(7, igneousID, 7))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[7]).setOres(new boolean[]{true, true, false});
	
	public static final BiomeGenUndergroundBase igneous9 = (new BiomeGenUndergroundIgneous(8, igneousID, 0))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[8]).setOres(new boolean[]{false, false, true});
	public static final BiomeGenUndergroundBase igneous10 = (new BiomeGenUndergroundIgneous(9, igneousID, 1))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[9]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous11 = (new BiomeGenUndergroundIgneous(10, igneousID, 2))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase igneous12 = (new BiomeGenUndergroundIgneous(11, igneousID, 3))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous13 = (new BiomeGenUndergroundIgneous(12, igneousID, 4))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{true, false, false});
	public static final BiomeGenUndergroundBase igneous14 = (new BiomeGenUndergroundIgneous(13, igneousID, 5))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous15 = (new BiomeGenUndergroundIgneous(14, igneousID, 6))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[4]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous16 = (new BiomeGenUndergroundIgneous(15, igneousID, 7))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[5]).setOres(new boolean[]{true, false, false});
	
	static int metamorphicID = UndergroundBiomes.metamorphicStoneID;
	
	public static final BiomeGenUndergroundBase metamorphic1 = (new BiomeGenUndergroundMetamorphic(16, metamorphicID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[6]).setOres(new boolean[]{true, false, false});
	public static final BiomeGenUndergroundBase metamorphic2 = (new BiomeGenUndergroundMetamorphic(17, metamorphicID, 1))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[7]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase metamorphic3 = (new BiomeGenUndergroundMetamorphic(18, metamorphicID, 1))//to stop marble from being a base rock
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[8]).setOres(new boolean[]{false, false, true});
	public static final BiomeGenUndergroundBase metamorphic4 = (new BiomeGenUndergroundMetamorphic(19, metamorphicID, 3))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[9]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase metamorphic5 = (new BiomeGenUndergroundMetamorphic(20, metamorphicID, 4))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase metamorphic6 = (new BiomeGenUndergroundMetamorphic(21, metamorphicID, 5))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, false, false});
	public static final BiomeGenUndergroundBase metamorphic7 = (new BiomeGenUndergroundMetamorphic(22, metamorphicID, 6))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic8 = (new BiomeGenUndergroundMetamorphic(23, metamorphicID, 7))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{false, false, false});
	
	public static final BiomeGenUndergroundBase metamorphic9 = (new BiomeGenUndergroundMetamorphic(24, metamorphicID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[4]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic10 = (new BiomeGenUndergroundMetamorphic(25, metamorphicID, 1))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[5]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase metamorphic11 = (new BiomeGenUndergroundMetamorphic(26, metamorphicID, 1))//to stop marble from being a base rock
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[6]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase metamorphic12 = (new BiomeGenUndergroundMetamorphic(27, metamorphicID, 3))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[7]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic13 = (new BiomeGenUndergroundMetamorphic(28, metamorphicID, 4))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[8]).setOres(new boolean[]{false, false, false});
	public static final BiomeGenUndergroundBase metamorphic14 = (new BiomeGenUndergroundMetamorphic(29, metamorphicID, 5))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[9]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase metamorphic15 = (new BiomeGenUndergroundMetamorphic(30, metamorphicID, 6))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic16 = (new BiomeGenUndergroundMetamorphic(31, metamorphicID, 7))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, true, true});
	
	public String biomeName;
	
	public final int biomeID;
	
	public BiomeUndergroundDecorator theBiomeDecorator;
	
	public boolean hasStrata = false;
	
	public StrataLayer[] strata;
	
	public boolean hasOres[];
	
	public exterminatorJeff.undergroundBiomes.common.PerlinNoiseGenerator strataNoise;
	
	public int fillerBlock = 0;
	public byte fillerBlockMetadata;
	
	protected BiomeGenUndergroundBase(int ID, int filler){
		this.biomeID = ID;
		this.fillerBlock = filler;
		this.theBiomeDecorator = this.createBiomeDecorator();
		biomeList[ID] = this;
		strataNoise = new PerlinNoiseGenerator(1);
	}
	
	protected BiomeGenUndergroundBase(int ID, int filler, int metadataValue){
		this.biomeID = ID;
		this.fillerBlock =  filler;
		this.fillerBlockMetadata = (byte) metadataValue;
		this.theBiomeDecorator = this.createBiomeDecorator();
		biomeList[ID] = this;
		strataNoise = new PerlinNoiseGenerator(1);
	}
	
	protected BiomeGenUndergroundBase AddStrataLayers(StrataLayer[] strata){
		hasStrata = true;
		this.strata = strata;	
		return this;
	}
	
	protected BiomeGenUndergroundBase setOres(boolean[] ores){
		this.hasOres = ores;
		return this;
	}
	
	public int[] getStrataBlockAtLayer(int y){
		for(int i = 0; i < strata.length; i++){
			if(strata[i].valueIsInLayer(y) == true){
				return new int[] {strata[i].layerBlockID, strata[i].layerMetadataID};
			}
		}
		return new int[] {fillerBlock, fillerBlockMetadata};
	}
	
	protected BiomeGenUndergroundBase setFillerBlock(byte blockID){
		this.fillerBlock = blockID;
		return this;
	}
	
	protected BiomeGenUndergroundBase setName(String name){
		this.biomeName = name;
		return this;
	}
	
	protected BiomeUndergroundDecorator createBiomeDecorator()
    {
        return new BiomeUndergroundDecorator(this);
    }
	
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        this.theBiomeDecorator.decorate(par1World, par2Random, par3, par4, hasOres);
    }
    
}

class StrataLayer{
	
	public int layerBlockID, layerMetadataID, layerMin, layerMax;
	
	public StrataLayer(int layerBlockID, int layerMetadataID, int layerMin, int layerMax){
		this.layerBlockID = layerBlockID;
		this.layerMetadataID = layerMetadataID;
		this.layerMin = layerMin;
		this.layerMax = layerMax;
	}
	
	public boolean valueIsInLayer(int y){
		if(y >= layerMin && y <= layerMax){
			return true;
		}else{
			return false;
		}
	}
	
}
