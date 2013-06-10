package exterminatorJeff.undergroundBiomes.worldGen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import exterminatorJeff.undergroundBiomes.common.PerlinNoiseGenerator;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class BiomeGenUndergroundBase {
	
	static final BiomeGenStrataLayers strataLayers = new BiomeGenStrataLayers();
	
	public static final BiomeGenUndergroundBase[] biomeList = new BiomeGenUndergroundBase[256];
	
	static int igneousID = UndergroundBiomes.igneousStoneID;
	
	public static final BiomeGenUndergroundBase igneous1 = (new BiomeGenUndergroundBase(0, igneousID, 0))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase igneous2 = (new BiomeGenUndergroundBase(1, igneousID, 1))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase igneous3 = (new BiomeGenUndergroundBase(2, igneousID, 2))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{false, true, false});
	public static final BiomeGenUndergroundBase igneous4 = (new BiomeGenUndergroundBase(3, igneousID, 3))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase igneous5 = (new BiomeGenUndergroundBase(4, igneousID, 4))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[4]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase igneous6 = (new BiomeGenUndergroundBase(5, igneousID, 5))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[5]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase igneous7 = (new BiomeGenUndergroundBase(6, igneousID, 6))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[6]).setOres(new boolean[]{false, false, true});
	public static final BiomeGenUndergroundBase igneous8 = (new BiomeGenUndergroundBase(7, igneousID, 7))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[7]).setOres(new boolean[]{true, true, false});
	
	public static final BiomeGenUndergroundBase igneous9 = (new BiomeGenUndergroundBase(8, igneousID, 0))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[8]).setOres(new boolean[]{false, false, true});
	public static final BiomeGenUndergroundBase igneous10 = (new BiomeGenUndergroundBase(9, igneousID, 1))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[9]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous11 = (new BiomeGenUndergroundBase(10, igneousID, 2))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase igneous12 = (new BiomeGenUndergroundBase(11, igneousID, 3))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous13 = (new BiomeGenUndergroundBase(12, igneousID, 4))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{true, false, false});
	public static final BiomeGenUndergroundBase igneous14 = (new BiomeGenUndergroundBase(13, igneousID, 5))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous15 = (new BiomeGenUndergroundBase(14, igneousID, 6))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[4]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase igneous16 = (new BiomeGenUndergroundBase(15, igneousID, 7))
			.setName("Igneous").AddStrataLayers(strataLayers.layers[5]).setOres(new boolean[]{true, false, false});
	
	static int metamorphicID = UndergroundBiomes.metamorphicStoneID;
	
	public static final BiomeGenUndergroundBase metamorphic1 = (new BiomeGenUndergroundBase(16, metamorphicID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[6]).setOres(new boolean[]{true, false, false});
	public static final BiomeGenUndergroundBase metamorphic2 = (new BiomeGenUndergroundBase(17, metamorphicID, 1))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[7]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase metamorphic3 = (new BiomeGenUndergroundBase(18, metamorphicID, 1))//to stop marble from being a base rock
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[8]).setOres(new boolean[]{false, false, true});
	public static final BiomeGenUndergroundBase metamorphic4 = (new BiomeGenUndergroundBase(19, metamorphicID, 3))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[9]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase metamorphic5 = (new BiomeGenUndergroundBase(20, metamorphicID, 4))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase metamorphic6 = (new BiomeGenUndergroundBase(21, metamorphicID, 5))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, false, false});
	public static final BiomeGenUndergroundBase metamorphic7 = (new BiomeGenUndergroundBase(22, metamorphicID, 6))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic8 = (new BiomeGenUndergroundBase(23, metamorphicID, 7))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{false, false, false});
	
	public static final BiomeGenUndergroundBase metamorphic9 = (new BiomeGenUndergroundBase(24, metamorphicID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[4]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic10 = (new BiomeGenUndergroundBase(25, metamorphicID, 1))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[5]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase metamorphic11 = (new BiomeGenUndergroundBase(26, metamorphicID, 1))//to stop marble from being a base rock
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[6]).setOres(new boolean[]{false, true, true});
	public static final BiomeGenUndergroundBase metamorphic12 = (new BiomeGenUndergroundBase(27, metamorphicID, 3))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[7]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic13 = (new BiomeGenUndergroundBase(28, metamorphicID, 4))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[8]).setOres(new boolean[]{false, false, false});
	public static final BiomeGenUndergroundBase metamorphic14 = (new BiomeGenUndergroundBase(29, metamorphicID, 5))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[9]).setOres(new boolean[]{true, true, false});
	public static final BiomeGenUndergroundBase metamorphic15 = (new BiomeGenUndergroundBase(30, metamorphicID, 6))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, false, true});
	public static final BiomeGenUndergroundBase metamorphic16 = (new BiomeGenUndergroundBase(31, metamorphicID, 7))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, true, true});
	
	public static final BiomeGenUndergroundBase vanillaStone1 = (new BiomeGenUndergroundBase(32, Block.stone.blockID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[0]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase vanillaStone2 = (new BiomeGenUndergroundBase(33, Block.stone.blockID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[1]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase vanillaStone3 = (new BiomeGenUndergroundBase(34, Block.stone.blockID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[2]).setOres(new boolean[]{true, true, true});
	public static final BiomeGenUndergroundBase vanillaStone4 = (new BiomeGenUndergroundBase(35, Block.stone.blockID, 0))
			.setName("Metamorphic").AddStrataLayers(strataLayers.layers[3]).setOres(new boolean[]{true, true, true});
	
	public String biomeName;
	
	public final int biomeID;
	
	public BiomeUndergroundDecorator theBiomeDecorator;
	
	public boolean hasStrata = false;
	
	public StrataLayer[] strata;
	
	public boolean hasOres[];
	
	public PerlinNoiseGenerator strataNoise;
	
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


