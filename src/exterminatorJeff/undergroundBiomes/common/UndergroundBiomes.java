package exterminatorJeff.undergroundBiomes.common;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import exterminatorJeff.undergroundBiomes.common.block.BlockAnthracite;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousCobblestone;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousStone;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousStoneBrick;
import exterminatorJeff.undergroundBiomes.common.block.BlockIgneousStoneSlab;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicCobblestone;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStone;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStoneBrick;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetamorphicStoneSlab;
import exterminatorJeff.undergroundBiomes.common.block.BlockSedimentaryStone;
import exterminatorJeff.undergroundBiomes.common.item.ItemIgneousCobblestoneBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemIgneousStoneBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemIgneousStoneBrickBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemIgneousStoneSlab;
import exterminatorJeff.undergroundBiomes.common.item.ItemLigniteCoal;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetamorphicCobblestoneBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetamorphicStoneBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetamorphicStoneBrickBlock;
import exterminatorJeff.undergroundBiomes.common.item.ItemMetamorphicStoneSlab;
import exterminatorJeff.undergroundBiomes.common.item.ItemSedimentaryStoneBlock;


@Mod(modid = "UndergroundBiomes", name = "Underground Biomes", version = "0.3.5")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)


public class UndergroundBiomes{
	
	public static World world;
	public static boolean compatibilityMode = false;
	public static boolean addOreDictRecipes = true;
	public static boolean vanillaStoneBiomes = false;
	
	public Configuration config;
	public static boolean oreVeins = true;
	
	public static WorldGenManager worldGen;
	public static ArrayList<BiomeGenRule> biomeRules;
	
	public static String blockTextures = "/exterminatorJeff/undergroundBiomes/textures/BlockTextures.png";
	public static String itemTextures = "/exterminatorJeff/undergroundBiomes/textures/Items.png";
	public static String texturePath = "UndergroundBiomes:";
	
	public static CreativeTabs tabModBlocks;
	public static CreativeTabs tabModItems;
	
	public static long worldSeed;
	private boolean gotWorldSeed;
	
	public static Block igneousStone;
	public static int igneousStoneID = 250;
	
	public static Block igneousCobblestone;
	public static int igneousCobblestoneID = 2000;
	
	public static Block igneousStoneBrick;
	public static int igneousStoneBrickID = 2002;
	
	public static Block metamorphicStone;
	public static int metamorphicStoneID = 251;
	
	public static Block metamorphicCobblestone;
	public static int metamorphicCobblestoneID = 2001;
	
	public static Block metamorphicStoneBrick;
	public static int metamorphicStoneBrickID = 2003;
	
	public static Block sedimentaryStone;
	public static int sedimentaryStoneID = 252;
	
	public static Item ligniteCoal;
	public static int ligniteCoalID = 550;
	
	public static Block anthraciteCoal;
	public static int anthraciteCoalID = 2004;
	
	public static Block igneousBrickSlabHalf;
	public static int igneousBrickSlabHalfId = 2005;
	
	public static Block igneousBrickSlabFull;
	public static int igneousBrickSlabFullId = 2006;
	
	public static Block metamorphicBrickSlabHalf;
	public static int metamorphicBrickSlabHalfID = 2007;
	
	public static Block metamorphicBrickSlabFull;
	public static int metamorphicBrickSlabFullID = 2008;
	
	public static float hardnessFactor = 0.25f;
	public static float resistanceFactor = 1.5f;
	public static int biomeSize = 45;
	
	//no grass
	public static boolean testMode1 = false;
	//no stone
	public static boolean testMode2 = false;
	

	@SidedProxy(clientSide = "exterminatorJeff.undergroundBiomes.client.ClientProxy", serverSide = "exterminatorJeff.undergroundBiomes.common.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
    public void preInit(FMLPreInitializationEvent event) {
            config = new Configuration(event.getSuggestedConfigurationFile());
            config.load();
            
            igneousStoneID = config.get(Configuration.CATEGORY_BLOCK, "Igneous Stone ID:", 250).getInt();
            metamorphicStoneID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Stone ID:", 251).getInt();
            sedimentaryStoneID = config.get(Configuration.CATEGORY_BLOCK, "Sedimentary Stone ID:", 252).getInt();
            
            igneousCobblestoneID = config.get(Configuration.CATEGORY_BLOCK, "Igneous Cobblestone ID:", 2000).getInt();
            metamorphicCobblestoneID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Cobblestone ID:", 2001).getInt();
            
            igneousStoneBrickID = config.get(Configuration.CATEGORY_BLOCK, "Igneous Brick ID:", 2002).getInt();
            metamorphicStoneBrickID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Brick ID:", 2003).getInt();
            
            anthraciteCoalID = config.get(Configuration.CATEGORY_BLOCK, "Anthracite Block ID:", 2004).getInt();
            
            igneousBrickSlabHalfId = config.get(Configuration.CATEGORY_BLOCK, "Igneous Stone Brick Slab ID (half):", 2005).getInt();
            igneousBrickSlabFullId = config.get(Configuration.CATEGORY_BLOCK, "Igneous Stone Brick Slab ID (full):", 2006).getInt();
            
            metamorphicBrickSlabHalfID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Stone Brick Slab ID (half):", 2007).getInt();
            metamorphicBrickSlabFullID = config.get(Configuration.CATEGORY_BLOCK, "Metamorphic Stone Brick Slab ID (full):", 2008).getInt();
            
            ligniteCoalID = config.get(Configuration.CATEGORY_BLOCK, "Lignite Item ID:", 550).getInt();
            
            biomeSize = config.get(Configuration.CATEGORY_GENERAL, "Biome size (warning: exponential): ", 45).getInt();
            addOreDictRecipes = config.get(Configuration.CATEGORY_GENERAL, "Modify all recipes to include Underground Biomes blocks (could be buggy)", true).getBoolean(true);
            vanillaStoneBiomes = config.get(Configuration.CATEGORY_GENERAL, "Generate Vanilla Stone Biomes (Could cause sharp biome transitions is changed while playing the same world", false).getBoolean(false);
            
            config.save();
            
            biomeRules = new ArrayList<BiomeGenRule>();
            
    }
	
	@Init
	public void load(FMLInitializationEvent event){

		
		proxy.registerRenderThings();
		
		tabModBlocks = new CreativeTabModBlocks(CreativeTabs.creativeTabArray.length, "Underground Biomes Blocks");
		tabModItems = new CreativeTabModBlocks(CreativeTabs.creativeTabArray.length, "Underground Biomes Items");
		
		//rocks
		
		igneousStone = new BlockIgneousStone(igneousStoneID, 0).setUnlocalizedName("igneousStone");
		Item.itemsList[igneousStoneID] = new ItemIgneousStoneBlock(igneousStoneID - 256, igneousStone).setUnlocalizedName("igneousStone");
		
		igneousCobblestone = new BlockIgneousCobblestone(igneousCobblestoneID, 8).setUnlocalizedName("igneousCobblestone");
		Item.itemsList[igneousCobblestoneID] = new ItemIgneousCobblestoneBlock(igneousCobblestoneID - 256, igneousCobblestone).setUnlocalizedName("igneousCobblestone");
		
		igneousStoneBrick = new BlockIgneousStoneBrick(igneousStoneBrickID, 16).setUnlocalizedName("igneousStoneBrick");
		Item.itemsList[igneousStoneBrickID] = new ItemIgneousStoneBrickBlock(igneousStoneBrickID - 256, igneousStoneBrick).setUnlocalizedName("igneousStoneBrick");
		
		metamorphicStone = new BlockMetamorphicStone(metamorphicStoneID).setUnlocalizedName("metamorphicStone");
		Item.itemsList[metamorphicStoneID] = new ItemMetamorphicStoneBlock(metamorphicStoneID - 256, metamorphicStone).setUnlocalizedName("metamorphicStone");
		
		metamorphicCobblestone = new BlockMetamorphicCobblestone(metamorphicCobblestoneID).setUnlocalizedName("metamorphicCobblestone");
		Item.itemsList[metamorphicCobblestoneID] = new ItemMetamorphicCobblestoneBlock(metamorphicCobblestoneID - 256, metamorphicCobblestone).setUnlocalizedName("metamorphicCobblestone");
		
		metamorphicStoneBrick = new BlockMetamorphicStoneBrick(metamorphicStoneBrickID).setUnlocalizedName("metamorphicStoneBrick");
		Item.itemsList[metamorphicStoneBrickID] = new ItemMetamorphicStoneBrickBlock(metamorphicStoneBrickID - 256, metamorphicStoneBrick).setUnlocalizedName("metamorphicStoneBrick");
		
		sedimentaryStone = new BlockSedimentaryStone(sedimentaryStoneID).setUnlocalizedName("sedimentaryStone");
		Item.itemsList[sedimentaryStoneID] = new ItemSedimentaryStoneBlock(sedimentaryStoneID - 256, sedimentaryStone).setUnlocalizedName("sedimentaryStone");
		
		anthraciteCoal = new BlockAnthracite(anthraciteCoalID).setUnlocalizedName("anthraciteCoal");
		GameRegistry.registerBlock(anthraciteCoal, "undergroundBiomes_anthraciteBlock");
		
		igneousBrickSlabHalf = new BlockIgneousStoneSlab(igneousBrickSlabHalfId, false).setUnlocalizedName("igneousBrickSlab");
		Item.itemsList[igneousBrickSlabHalfId] = new ItemIgneousStoneSlab(igneousBrickSlabHalfId - 256, igneousBrickSlabHalf).setUnlocalizedName("igneousBrickSlab");
		
		igneousBrickSlabFull = new BlockIgneousStoneSlab(igneousBrickSlabFullId, true).setUnlocalizedName("igneousBrickSlabFull");
		Item.itemsList[igneousBrickSlabFullId] = new ItemIgneousStoneSlab(igneousBrickSlabFullId - 256, igneousBrickSlabFull).setUnlocalizedName("igneousBrickSlabFull");
		
		metamorphicBrickSlabHalf = new BlockMetamorphicStoneSlab(metamorphicBrickSlabHalfID, false).setUnlocalizedName("metamorphicBrickSlab");
		Item.itemsList[metamorphicBrickSlabHalfID] = new ItemMetamorphicStoneSlab(metamorphicBrickSlabHalfID - 256, metamorphicBrickSlabHalf).setUnlocalizedName("metamorphicBrickSlab");
		
		metamorphicBrickSlabFull = new BlockMetamorphicStoneSlab(metamorphicBrickSlabFullID, true).setUnlocalizedName("metamorphicBrickSlabFull");
		Item.itemsList[metamorphicBrickSlabFullID] = new ItemMetamorphicStoneSlab(metamorphicBrickSlabFullID - 256, metamorphicBrickSlabFull).setUnlocalizedName("metamorphicBrickSlabFull");
		
		//items
		
		
		ligniteCoal = new ItemLigniteCoal(ligniteCoalID).setUnlocalizedName("ligniteCoal");
		
		setUpBlockNames();
		addOreDicts();
		addRecipes();
		
		MinecraftForge.EVENT_BUS.register(this);	
		
		((CreativeTabModBlocks) tabModBlocks).setIcon(igneousStoneBrick.blockID);
		((CreativeTabModBlocks) tabModItems).setIcon(ligniteCoal.itemID);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){
		//CustomOreCreator creator = new CustomOreCreator();
		if(addOreDictRecipes){
			oreDictifyRecipes(Block.stone, "stoneSmooth");
			oreDictifyRecipes(Block.cobblestone, "stoneCobble");
		}
	}
	
	public void setUpBlockNames(){
		//igneous stone
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.redGranite.name", "Red Granite");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.blackGranite.name", "Black Granite");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.rhyolite.name", "Rhyolite");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.andesite.name", "Andesite");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.gabbro.name", "Gabbro");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.basalt.name", "Basalt");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.komatiite.name", "Komatiite");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStone.epidote.name", "Epidote");
		
		//igneous cobble
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.redGraniteCobble.name", "Red Granite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.blackGraniteCobble.name", "Black Granite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.rhyoliteCobble.name", "Rhyolite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.andesiteCobble.name", "Andesite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.gabbroCobble.name", "Gabbro Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.basaltCobble.name", "Basalt Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.komatiiteCobble.name", "Komatiite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.igneousCobblestone.epidoteCobble.name", "Epidote Cobblestone");
		
		//igneous stonebrick
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.redGraniteBrick.name", "Red Granite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.blackGraniteBrick.name", "Black Granite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.rhyoliteBrick.name", "Rhyolite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.andesiteBrick.name", "Andesite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.gabbroBrick.name", "Gabbro Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.basaltBrick.name", "Basalt Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.komatiiteBrick.name", "Komatiite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.igneousStoneBrick.epidoteBrick.name", "Epidote Bricks");
		
		//metamorphic stone
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.gneiss.name", "Gneiss");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.eclogite.name", "Eclogite");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.marble.name", "Marble");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.quartzite.name", "Quartzite");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.blueschist.name", "Blue Schist");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.greenschist.name", "Green Schist");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.soapstone.name", "Soapstone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStone.migmatite.name", "Migmatite");
		
		//metamorphic cobble
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.gneissCobble.name", "Gneiss Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.eclogiteCobble.name", "Eclogite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.marbleCobble.name", "Marble Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.quartziteCobble.name", "Quartzite Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.blueschistCobble.name", "Blue Schist Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.greenschistCobble.name", "Green Schist Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.soapstoneCobble.name", "Soapstone Cobblestone");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicCobblestone.migmatiteCobble.name", "Migmatite Cobblestone");
		
		//metamorphic stonebrick
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.gneissBrick.name", "Gneiss Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.eclogiteBrick.name", "Eclogite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.marbleBrick.name", "Marble Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.quartziteBrick.name", "Quartzite Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.blueschistBrick.name", "Blue Schist Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.greenschistBrick.name", "Green Schist Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.soapstoneBrick.name", "Soapstone Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicStoneBrick.migmatiteBrick.name", "Migmatite Bricks");
		
		//sedimentary stone
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.limestone.name", "Limestone");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.chalk.name", "Chalk");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.shale.name", "Shale");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.siltstone.name", "Siltstone");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.ligniteBlock.name", "Lignite Block");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.flint.name", "Flint");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.greywacke.name", "Greywacke");
		LanguageRegistry.instance().addStringLocalization("tile.sedimentaryStone.chert.name", "Chert");
		
		//igneous brick slab
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.redGraniteBrickSlab.name", "Red Granite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.blackGraniteBrickSlab.name", "Black Granite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.rhyoliteBrickSlab.name", "Rhyolite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.andesiteBrickSlab.name", "Andesite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.gabbroBrickSlab.name", "Gabbro Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.basaltBrickSlab.name", "Basalt Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.komatiiteBrickSlab.name", "Komatiite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.igneousBrickSlab.epidoteBrickSlab.name", "Epidote Brick Slab");
		
		//metamorphic brick slab
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.gneissBrickSlab.name", "Gneiss Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.eclogiteBrickSlab.name", "Eclogite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.marbleBrickSlab.name", "Marble Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.quartziteBrickSlab.name", "Quartzite Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.blueschistBrickSlab.name", "Blue Schist Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.greenschistBrickSlab.name", "Green Schist Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.soapstoneBrickSlab.name", "Soapstone Brick Slab");
		LanguageRegistry.instance().addStringLocalization("tile.metamorphicBrickSlab.migmatiteBrickSlab.name", "Migmatite Brick Slab");
		
		//other blocks
		LanguageRegistry.addName(anthraciteCoal, "Anthracite Coal Block");
		
		//items
		LanguageRegistry.addName(ligniteCoal, "Lignite");
		
	}
	
	public void addRecipes(){
		
		if(!addOreDictRecipes){
			//furnace
			GameRegistry.addRecipe(new ItemStack(Block.furnaceIdle, 1), new Object[]{"XXX", "X X", "XXX", 'X', igneousCobblestone});
			GameRegistry.addRecipe(new ItemStack(Block.furnaceIdle, 1), new Object[]{"XXX", "X X", "XXX", 'X', metamorphicCobblestone});
			
			//lever
			GameRegistry.addRecipe(new ItemStack(Block.lever, 1), new Object[]{"I", "X", 'X', igneousCobblestone, 'I', Item.stick});
			GameRegistry.addRecipe(new ItemStack(Block.lever, 1), new Object[]{"I", "X", 'X', metamorphicCobblestone, 'I', Item.stick});
			
			//piston
			GameRegistry.addRecipe(new ItemStack(Block.pistonBase, 1), new Object[]{"WWW", "CIC", "CRC", 'W', Block.planks, 'C', igneousCobblestone, 'I', Item.ingotIron, 'R', Item.redstone});
			GameRegistry.addRecipe(new ItemStack(Block.pistonBase, 1), new Object[]{"WWW", "CIC", "CRC", 'W', Block.planks, 'C', metamorphicCobblestone, 'I', Item.ingotIron, 'R', Item.redstone});
			
			//cobble slab
			GameRegistry.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), new Object[]{"XXX", 'X', igneousCobblestone});
			GameRegistry.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), new Object[]{"XXX", 'X', metamorphicCobblestone});
			
			//stair
			GameRegistry.addRecipe(new ItemStack(Block.stairsCobblestone, 4), new Object[]{"X  ", "XX ", "XXX", 'X', igneousCobblestone});
			GameRegistry.addRecipe(new ItemStack(Block.stairsCobblestone, 4), new Object[]{"X  ", "XX ", "XXX", 'X', metamorphicCobblestone});
			
			//wall
			GameRegistry.addRecipe(new ItemStack(Block.cobblestoneWall, 1), new Object[]{"XXX", "XXX", 'X', igneousCobblestone});
			GameRegistry.addRecipe(new ItemStack(Block.cobblestoneWall, 1), new Object[]{"XXX", "XXX", 'X', metamorphicCobblestone});
			
			//axe
			GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[]{"XX ", "XW ", " W ", 'X', igneousCobblestone, 'W', Item.stick});
			GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[]{"XX ", "XW ", " W ", 'X', metamorphicCobblestone, 'W', Item.stick});
			
			//pickaxe
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[]{"XXX", " W ", " W ", 'X', igneousCobblestone, 'W', Item.stick});
			GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[]{"XXX", " W ", " W ", 'X', metamorphicCobblestone, 'W', Item.stick});
			
			//hoe
			GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[]{"XX ", " W ", " W ", 'X', igneousCobblestone, 'W', Item.stick});
			GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[]{"XX ", " W ", " W ", 'X', metamorphicCobblestone, 'W', Item.stick});
			
			//shovel
			GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[]{" X ", " W ", " W ", 'X', igneousCobblestone, 'W', Item.stick});
			GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[]{" X ", " W ", " W ", 'X', metamorphicCobblestone, 'W', Item.stick});
			
			//sword
			GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[]{"X", "X", "W", 'X', igneousCobblestone, 'W', Item.stick});
			GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[]{"X", "X", "W", 'X', metamorphicCobblestone, 'W', Item.stick});
			
			//brewing stand
			GameRegistry.addRecipe(new ItemStack(Item.brewingStand, 1), new Object[]{" B ", "XXX", 'X', igneousCobblestone, 'B', Item.blazeRod});
			GameRegistry.addRecipe(new ItemStack(Item.brewingStand, 1), new Object[]{" B ", "XXX", 'X', metamorphicCobblestone, 'B', Item.blazeRod});
			
			//dispenser
			GameRegistry.addRecipe(new ItemStack(Block.dispenser, 1), new Object[]{"XXX", "XBX", "XRX", 'X', igneousCobblestone, 'B', Item.bow, 'R', Item.redstone});
			GameRegistry.addRecipe(new ItemStack(Block.dispenser, 1), new Object[]{"XXX", "XBX", "XRX", 'X', metamorphicCobblestone, 'B', Item.bow, 'R', Item.redstone});
			
			//stone button
			GameRegistry.addShapelessRecipe(new ItemStack(Block.stoneButton, 1), new ItemStack(igneousStone, 1));
			GameRegistry.addShapelessRecipe(new ItemStack(Block.stoneButton, 1), new ItemStack(metamorphicStone, 1));
			
			//pressure plate
			GameRegistry.addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[]{"XX", 'X', igneousStone});
			GameRegistry.addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[]{"XX", 'X', metamorphicStone});
			
			//half slab
			GameRegistry.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[]{"XXX", 'X', igneousStone});
			GameRegistry.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[]{"XXX", 'X', metamorphicStone});
			
			//repeater
			GameRegistry.addRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[]{"TRT", "XXX", 'X', igneousStone, 'T', Block.torchRedstoneActive, 'R', Item.redstone});
			GameRegistry.addRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[]{"TRT", "XXX", 'X', metamorphicStone, 'T', Block.torchRedstoneActive, 'R', Item.redstone});
			
			//brick slab
			GameRegistry.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5), new Object[]{"XXX", 'X', igneousStoneBrick});
			GameRegistry.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5), new Object[]{"XXX", 'X', metamorphicStoneBrick});
		}
			
		//brick stair
		GameRegistry.addRecipe(new ItemStack(Block.stairsStoneBrick, 4), new Object[]{"X  ", "XX ", "XXX", 'X', igneousStoneBrick});
		GameRegistry.addRecipe(new ItemStack(Block.stairsStoneBrick, 4), new Object[]{"X  ", "XX ", "XXX", 'X', metamorphicStoneBrick});
		
		for(int i = 0; i < 8; i++){
			GameRegistry.addRecipe(new ItemStack(igneousBrickSlabHalf, 6, i), new Object[]{"XXX", 'X', new ItemStack(igneousStoneBrick, 1, i)});
			GameRegistry.addRecipe(new ItemStack(metamorphicBrickSlabHalf, 6, i), new Object[]{"XXX", 'X', new ItemStack(metamorphicStoneBrick, 1, i)});
		}
		
		//anthracite
		GameRegistry.addRecipe(new ItemStack(anthraciteCoal, 1), new Object[]{"XX", "XX", 'X', Item.coal});
		GameRegistry.addRecipe(new ItemStack(Item.coal, 1), "XXX", "XXX", "XXX", 'X', (Item)ligniteCoal);
		
		//temp
		//vanilla cobblestone
		GameRegistry.addRecipe(new ItemStack(Block.cobblestone, 4), new Object[]{"XX", "XX", 'X', igneousCobblestone});
		GameRegistry.addRecipe(new ItemStack(Block.cobblestone, 4), new Object[]{"XX", "XX", 'X', metamorphicCobblestone});
		
		for(int i = 0; i < 8; i++){
			FurnaceRecipes.smelting().addSmelting(metamorphicCobblestone.blockID, i, new ItemStack(metamorphicStone, 1, i), 0.1f);
			GameRegistry.addRecipe(new ItemStack(metamorphicStoneBrick, 4, i), new Object[]{"xx", "xx", 'x', new ItemStack(metamorphicStone, 1, i)});
		}
		for(int i = 0; i < 8; i++){
			FurnaceRecipes.smelting().addSmelting(igneousCobblestone.blockID, i, new ItemStack(igneousStone, 1, i), 0.1f);
			GameRegistry.addRecipe(new ItemStack(igneousStoneBrick, 4, i), new Object[]{"xx", "xx", 'x', new ItemStack(igneousStone, 1, i)});
		}
		
		//fuels
		GameRegistry.registerFuelHandler(new FuelManager());
		
	}
	
	public void addOreDicts(){
		
		for(int i = 0; i < 8; i++){
			//Register each stone with its proper name and its base name
			ItemStack igStone = new ItemStack(igneousStone, 1, i);
			ItemStack metStone = new ItemStack(metamorphicStone, 1, i);
			ItemStack sedStone = new ItemStack(sedimentaryStone, 1, i);
			
			ItemStack igStoneCobble = new ItemStack(igneousCobblestone, 1, i);
			ItemStack metStoneCobble = new ItemStack(metamorphicCobblestone, 1, i);
			
			ItemStack igStoneBrick = new ItemStack(igneousStoneBrick, 1, i);
			ItemStack metStoneBrick = new ItemStack(metamorphicStoneBrick, 1, i);
			
			//proper names
			
			//stones
			OreDictionary.registerOre("stone" + ((BlockIgneousStone)igneousStone).getBlockName(i, true),  igStone);
			OreDictionary.registerOre("stone" + ((BlockMetamorphicStone)metamorphicStone).getBlockName(i, true),  metStone);
			OreDictionary.registerOre("stone" + ((BlockSedimentaryStone)sedimentaryStone).getBlockName(i, true),  sedStone);
			//cobble
			OreDictionary.registerOre("stone" + ((BlockIgneousCobblestone)igneousCobblestone).getBlockName(i, true),  igStoneCobble);
			OreDictionary.registerOre("stone" + ((BlockMetamorphicCobblestone)metamorphicCobblestone).getBlockName(i, true),  metStoneCobble);
			//bricks
			OreDictionary.registerOre("stone" + ((BlockIgneousStoneBrick)igneousStoneBrick).getBlockName(i, true),  igStoneBrick);
			OreDictionary.registerOre("stone" + ((BlockMetamorphicStoneBrick)metamorphicStoneBrick).getBlockName(i, true),  metStoneBrick);
			
			//base name
			OreDictionary.registerOre("stoneSmooth", new ItemStack(igneousStone, 1, i));
			OreDictionary.registerOre("stoneSmooth", new ItemStack(metamorphicStone, 1, i));
			OreDictionary.registerOre("stoneCobble", new ItemStack(igneousCobblestone, 1, i));
			OreDictionary.registerOre("blockCobble", new ItemStack(metamorphicCobblestone, 1, i));
			OreDictionary.registerOre("stoneBricks", new ItemStack(igneousStoneBrick, 1, i));
			OreDictionary.registerOre("stoneBricks", new ItemStack(metamorphicStoneBrick, 1, i));
		}
	}
	
	public void oreDictifyRecipes(Block block, String oreDictName){
		List recipes = CraftingManager.getInstance().getRecipeList();
		
		ArrayList<Object> recipesToAdd = new ArrayList();
		ArrayList<Object> recipesToRemove = new ArrayList();
		
		for(Object r: recipes){
			if(r instanceof ShapedRecipes){
				ShapedRecipes recipe = (ShapedRecipes) r;
				boolean hasStone = false;
				for(ItemStack item: recipe.recipeItems){
					try{
						if(item.itemID == block.blockID){
							hasStone = true;
						}
					}catch(Exception e){}
				}
				if(hasStone){
					ArrayList<ItemStack> ingredients = new ArrayList();
					ArrayList<Object> recipeObject = new ArrayList();
					int charCount = 65;
					HashMap<Item, Character> distinctItems = new HashMap();
					
					for(ItemStack item: recipe.recipeItems){
						ingredients.add(item);
						if(item != null){
							if(!distinctItems.containsKey(item)){
								try{
									distinctItems.put(item.getItem(), new Character((char)charCount));
									charCount++;
								}catch(Exception e){
									//System.out.println("Error changing recipe " + recipe.getRecipeOutput().getItemName() + " for item " + item.getItemName());
									//System.out.println(e.getStackTrace());
								}
							}
						}
					}
					String[] rows = new String[3];
					//loop through three rows of crafting area
					int length = ingredients.size();
					boolean notEmpty = false;
					for(int i = 0; i < length / 3; i++){
						String current = "";
						for(int j = 0; j < 3; j++){
							if(ingredients.get((i * 3 + j)) != null){
								current = current + distinctItems.get(ingredients.get((i * 3 + j)).getItem());
								notEmpty = true;
							}else{
								current = current + " ";
							}
						}
						rows[i] = current;
						recipeObject.add(rows[i]);
					}
					if(!notEmpty){
						break;
					}
					
					for(Character c: distinctItems.values()){
						
					}
					for(Item it: distinctItems.keySet()){
						ItemStack is = new ItemStack(it);
						recipeObject.add(Character.valueOf(distinctItems.get(it)));
						if(is.itemID == block.blockID){
							recipeObject.add(oreDictName);
						}else{
							recipeObject.add(is);
						}
					}
					Object[] result = recipeObject.toArray();
					recipesToAdd.add(new ShapedOreRecipe(recipe.getRecipeOutput(), result));
				}
			}else if(r instanceof ShapedOreRecipe){
				try{
					ShapedOreRecipe recipe = (ShapedOreRecipe) r;
					Field recipeInput = recipe.getClass().getDeclaredField("input");
					recipeInput.setAccessible(true);
					
					
					
					boolean hasStone = false;
					
					Object[] inputs = (Object[]) recipeInput.get(r);
					
					for(Object ob: inputs){
						try{
							ItemStack item = null;
							if(ob instanceof ArrayList){
								ArrayList ar = (ArrayList)ob;
								item = (ItemStack) ar.get(0);
							}else{
								item = (ItemStack) ob;
							}
							if(item != null){
								if(item.itemID == block.blockID){
									hasStone = true;
								}
							}
						}catch(Exception e){}
					}
					if(hasStone){
						ArrayList<ItemStack> ingredients = new ArrayList();
						ArrayList<Object> recipeObject = new ArrayList();
						int charCount = 65;
						HashMap<Item, Character> distinctItems = new HashMap();
						
						for(Object ob: inputs){
							ItemStack item = null;
							if(ob instanceof ArrayList){
								ArrayList ar = (ArrayList)ob;
								item = (ItemStack) ar.get(0);
							}else{
								item = (ItemStack) ob;
							}
							ingredients.add(item);
							if(item != null){
								if(!distinctItems.containsKey(item)){
									
									try{
										distinctItems.put(item.getItem(), new Character((char)charCount));
										charCount++;
									}catch(Exception e){
										System.out.println("Error changing recipe " + recipe.getRecipeOutput().getItemName());
										//System.out.println(e.getStackTrace());
									}
								}
							}
						}
						
						Field recipeHeight = recipe.getClass().getDeclaredField("height");
						recipeHeight.setAccessible(true);
						
						Field recipeWidth = recipe.getClass().getDeclaredField("width");
						recipeWidth.setAccessible(true);
						
						String[] rows = new String[3];
						//loop through three rows of crafting area
						int length = ingredients.size();
						boolean notEmpty = false;
						for(int i = 0; i < recipeHeight.getInt(r); i++){
							String current = "";
							for(int j = 0; j < recipeWidth.getInt(r); j++){
								if(ingredients.get((i * recipeWidth.getInt(r) + j)) != null){
									current = current + distinctItems.get(ingredients.get((i * recipeWidth.getInt(r) + j)).getItem());
									notEmpty = true;
								}else{
									current = current + " ";
								}
							}
							rows[i] = current;
							recipeObject.add(rows[i]);
						}
						if(!notEmpty){
							break;
						}
						for(Character c: distinctItems.values()){
							
						}
						for(Item it: distinctItems.keySet()){
							ItemStack is = new ItemStack(it);
							recipeObject.add(Character.valueOf(distinctItems.get(it)));
							if(is.itemID == block.blockID){
								recipeObject.add(oreDictName);
							}else{
								recipeObject.add(is);
							}
						}
						Object[] result = recipeObject.toArray();
						recipesToAdd.add(new ShapedOreRecipe(recipe.getRecipeOutput(), result));
					}
				}catch(Exception e){
					System.out.println(e + " " + r.toString());
				};
			
			}
			
		}
		for(Object o: recipesToAdd){
			CraftingManager.getInstance().getRecipeList().add(o);
		}
		for(Object o: recipesToRemove){
			recipes.remove(o);
		}
	}
	
	
	
	public static long getWorldSeed(){
		return worldSeed;
	}
	
	public static World getWorld(){
		return world;
	}
	
	public static WorldGenManager getWorldGenManager(){
		return worldGen;
	}
	
	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event){
		if(gotWorldSeed){
			return;
		}
		world = event.world;
		worldSeed = world.getSeed();
		gotWorldSeed = true;
		worldGen = new WorldGenManager(worldSeed, world.getWorldInfo().getTerrainType(), world);
	}
	
	@ForgeSubscribe
	public void onWorldUnload(WorldEvent.Unload event){
		gotWorldSeed = false;
		
	}
	
	@ForgeSubscribe
	public void onBiomeDecorate(DecorateBiomeEvent event){
		try{
			if(worldGen != null){
				worldGen.onBiomeDecorate(event);
			}else{
				worldGen = new WorldGenManager(worldSeed, world.getWorldInfo().getTerrainType(), world);
				worldGen.onBiomeDecorate(event);
			}
		}catch(Exception e){
			
		}
	}
	
	/*@ForgeSubscribe
	public void onOreGen(OreGenEvent.GenerateMinable event){
		/*if(oreVeins){
			if(event.type == EventType.COAL || event.type == EventType.GOLD || event.type == EventType.IRON){
				event.setResult(Result.DENY);
			}
		}
	}*/
	
	public static void addBiomeRule(BiomeGenRule rule){
		biomeRules.add(rule);
	}
	
}


