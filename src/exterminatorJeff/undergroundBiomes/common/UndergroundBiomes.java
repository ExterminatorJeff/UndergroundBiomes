package exterminatorJeff.undergroundBiomes.common;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.*;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.Loader;

import exterminatorJeff.undergroundBiomes.common.block.*;
import exterminatorJeff.undergroundBiomes.common.item.*;
import exterminatorJeff.undergroundBiomes.common.command.*;

import exterminatorJeff.undergroundBiomes.constructs.UndergroundBiomesConstructs;

@Mod(modid = "UndergroundBiomes", name = "Underground Biomes", version = "0.4.2a")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class UndergroundBiomes
{
    public static World world;
    public static boolean addOreDictRecipes = true;
    public static boolean vanillaStoneBiomes = false;
    
    public Configuration config;
    
    public static WorldGenManager worldGen;
    
    public static String textures = "/exterminatorJeff/undergroundBiomes/textures.png";
    
    public static CreativeTabModBlocks tabModBlocks;
    public static CreativeTabModBlocks tabModItems;
    
    public static long worldSeed;
    private boolean gotWorldSeed;
    
    public static Block igneousStone;
    public static Block igneousCobblestone;
    public static Block igneousStoneBrick;
    public static Block metamorphicStone;
    public static Block metamorphicCobblestone;
    public static Block metamorphicStoneBrick;
    public static Block sedimentaryStone;
    public static Item ligniteCoal;
    public static Item fossilPiece;
    public static BlockHalfSlab igneousBrickSlabHalf;
    public static BlockHalfSlab igneousBrickSlabFull;
    public static BlockHalfSlab metamorphicBrickSlabHalf;
    public static BlockHalfSlab metamorphicBrickSlabFull;
    
    public static ArrayList<Integer> fortuneAffected;
    public static ArrayList<ItemStack> nuggets;

    private static String[] nuggetStrings = {
      "nuggetIron", "nuggetCopper", "nuggetTin", "nuggetSilver", "nuggetLead", "nuggetAluminium",
      "nuggetNaturalAluminium", "nuggetNickel", "nuggetPlatinum", "nuggetElectrum", "nuggetZinc",
    };

    private int igneousStoneID;
    private int igneousCobblestoneID;
    private int igneousStoneBrickID;
    private int metamorphicStoneID;
    private int metamorphicCobblestoneID;
    private int metamorphicStoneBrickID;
    private int sedimentaryStoneID;
    private int ligniteCoalID;
    private int fossilPieceID;
    private int igneousBrickSlabHalfId;
    private int igneousBrickSlabFullId;
    private int metamorphicBrickSlabHalfID;
    private int metamorphicBrickSlabFullID;
    private List<Integer> includeDimensionIDs;
    private List<Integer> excludeDimensionIDs;
    private String includeDimensions;
    private String excludeDimensions;

    private int vanillaStoneCrafting;

    private UndergroundBiomesConstructs constructs;
    
    public static int biomeSize = 45;

    @SidedProxy(clientSide = "exterminatorJeff.undergroundBiomes.client.ClientProxy",
                serverSide = "exterminatorJeff.undergroundBiomes.common.CommonProxy")
    public static CommonProxy proxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        igneousStoneID = config.getBlock("Igneous Stone ID:", 2009).getInt();
        metamorphicStoneID = config.getBlock("Metamorphic Stone ID:", 2010).getInt();
        sedimentaryStoneID = config.getBlock("Sedimentary Stone ID:", 2011).getInt();
        
        igneousCobblestoneID = config.getBlock("Igneous Cobblestone ID:", 2000).getInt();
        metamorphicCobblestoneID = config.getBlock("Metamorphic Cobblestone ID:", 2001).getInt();
        
        igneousStoneBrickID = config.getBlock("Igneous Brick ID:", 2002).getInt();
        metamorphicStoneBrickID = config.getBlock("Metamorphic Brick ID:", 2003).getInt();
        
        igneousBrickSlabHalfId = config.getBlock("Igneous Stone Brick Slab ID (half):", 2005).getInt();
        igneousBrickSlabFullId = config.getBlock("Igneous Stone Brick Slab ID (full):", 2006).getInt();
        
        metamorphicBrickSlabHalfID = config.getBlock("Metamorphic Stone Brick Slab ID (half):", 2007).getInt();
        metamorphicBrickSlabFullID = config.getBlock("Metamorphic Stone Brick Slab ID (full):", 2008).getInt();
        
        // Item read from block category to be backwards-compatible
        ligniteCoalID = config.getItem(Configuration.CATEGORY_BLOCK, "Lignite Item ID:", 5500).getInt();
        fossilPieceID = config.getItem("fossilPiece", 5501).getInt();
        
        biomeSize = config.get(Configuration.CATEGORY_GENERAL, "biomeSize", 45, "Warning: exponential").getInt();
        addOreDictRecipes = config.get(Configuration.CATEGORY_GENERAL, "oreDictifyStone", true, "Modify all recipes to include Underground Biomes blocks").getBoolean(true);
        vanillaStoneBiomes = config.get(Configuration.CATEGORY_GENERAL, "vanillaStoneBiomes", false, "Will cause sharp biome transitions if changed while playing the same world").getBoolean(false);
        excludeDimensions = config.get(Configuration.CATEGORY_GENERAL, "excludeDimensionIDs", "-1,1", "Comma-separated list of dimension IDs, used only if include list is *").getString();
        includeDimensions = config.get(Configuration.CATEGORY_GENERAL, "includeDimensionIDs", "*", "Comma-separated list of dimension IDs, put * to use exclude list").getString();
        vanillaStoneCrafting = config.get(Configuration.CATEGORY_GENERAL, "vanillaStoneCrafting", 3, "0 = none; 1 = one rock; 2 = with redstone; 3 = 2x2 stone, lose 3; 4 = 2x2 stone").getInt();

        if (includeDimensions.equals("*"))
        {
            excludeDimensionIDs = new ArrayList<Integer>();
            for (String v : excludeDimensions.split(","))
            {
                excludeDimensionIDs.add(Integer.parseInt(v));
            }
        }
        else
        {
            includeDimensionIDs = new ArrayList<Integer>();
            for (String v : includeDimensions.split(","))
            {
                includeDimensionIDs.add(Integer.parseInt(v));
            }
        }
        
        config.save();

        fortuneAffected = new ArrayList<Integer>();
        fortuneAffected.add(ligniteCoalID);

        nuggets = new ArrayList<ItemStack>();
        nuggets.add(new ItemStack(Item.goldNugget, 1, 0));


        proxy.registerRenderThings();
        
        tabModBlocks = new CreativeTabModBlocks("undergroundBiomesBlocks");
        tabModItems = new CreativeTabModBlocks("undergroundBiomesItems");

        igneousStone = new BlockIgneousStone(igneousStoneID);
        new ItemMetadataBlock(igneousStone);
        
        igneousCobblestone = new BlockIgneousCobblestone(igneousCobblestoneID);
        new ItemMetadataBlock(igneousCobblestone);
        
        igneousStoneBrick = new BlockIgneousStoneBrick(igneousStoneBrickID);
        new ItemMetadataBlock(igneousStoneBrick);
        
        metamorphicStone = new BlockMetamorphicStone(metamorphicStoneID);
        new ItemMetadataBlock(metamorphicStone);
        
        metamorphicCobblestone = new BlockMetamorphicCobblestone(metamorphicCobblestoneID);
        new ItemMetadataBlock(metamorphicCobblestone);
        
        metamorphicStoneBrick = new BlockMetamorphicStoneBrick(metamorphicStoneBrickID);
        new ItemMetadataBlock(metamorphicStoneBrick);
        
        sedimentaryStone = new BlockSedimentaryStone(sedimentaryStoneID);
        new ItemMetadataBlock(sedimentaryStone);
        
        igneousBrickSlabHalf = (BlockHalfSlab)new BlockStoneSlab(igneousBrickSlabHalfId, false, igneousStoneBrick).setUnlocalizedName("igneousBrickSlab").func_111022_d("undergroundbiomes:igneousBrickSlab");
        igneousBrickSlabFull = (BlockHalfSlab)new BlockStoneSlab(igneousBrickSlabFullId, true, igneousStoneBrick).setUnlocalizedName("igneousBrickSlab").func_111022_d("undergroundbiomes:igneousBrickSlab");
        
        new ItemSlab(igneousBrickSlabHalfId - 256, igneousBrickSlabHalf, igneousBrickSlabFull, false);
        new ItemSlab(igneousBrickSlabFullId - 256, igneousBrickSlabHalf, igneousBrickSlabFull, true);
        
        metamorphicBrickSlabHalf = (BlockHalfSlab)new BlockStoneSlab(metamorphicBrickSlabHalfID, false, metamorphicStoneBrick).setUnlocalizedName("metamorphicBrickSlab").func_111022_d("undergroundbiomes:metamorphicBrickSlab");
        metamorphicBrickSlabFull = (BlockHalfSlab)new BlockStoneSlab(metamorphicBrickSlabFullID, true, metamorphicStoneBrick).setUnlocalizedName("metamorphicBrickSlab").func_111022_d("undergroundbiomes:metamorphicBrickSlab");
        
        new ItemSlab(metamorphicBrickSlabHalfID - 256, metamorphicBrickSlabHalf, metamorphicBrickSlabFull, false);
        new ItemSlab(metamorphicBrickSlabFullID - 256, metamorphicBrickSlabHalf, metamorphicBrickSlabFull, true);
        
        //items

        ligniteCoal = new ItemLigniteCoal(ligniteCoalID);
        fossilPiece = new ItemFossilPiece(fossilPieceID);
        
        tabModBlocks.iconID = igneousStoneBrick.blockID;
        tabModItems.iconID = ligniteCoal.itemID;

        proxy.setUpBlockNames();

        constructs = new UndergroundBiomesConstructs();
        constructs.preInit(event);

    }
    
    @Init
    public void load(FMLInitializationEvent event)
    {
        addOreDicts();
        addRecipes();

        constructs.load(event);

        MinecraftForge.EVENT_BUS.register(this);    
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) throws Exception
    {
        if (addOreDictRecipes)
        {
            oreDictifyStone();
        }

        // Pull nuggets from ore dictionary
        ArrayList<ItemStack> stacks;
        for (String s : nuggetStrings)
        {
            stacks = OreDictionary.getOres(s);
            if (stacks.size() > 0)
            {
                nuggets.add(stacks.get(0));
            }
        }

        if (Loader.isModLoaded("Thaumcraft"))
        {
            try
            {
		//ThaumcraftApi.registerObjectTag(id, meta, (new ObjectTags()).add(EnumTag.VALUABLE, 58).add(EnumTag.LIGHT, 15));
            }
            catch (Exception e)
            {
                System.out.println("[UndergroundBiomes] Error while integrating with Thaumcraft");
                e.printStackTrace(System.err);
            }
        }

        constructs.postInit(event);
    }
    
    @ServerStarting
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandOreDictifyStone());
    }
    
    public void addRecipes()
    {
        if (!addOreDictRecipes)
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.furnaceIdle, 1), "XXX", "X X", "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.lever, 1), "I", "X", 'X', "stoneCobble", 'I', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CIC", "CRC", 'W', Block.planks, 'C', "stoneCobble", 'I', Item.ingotIron, 'R', Item.redstone));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stairsCobblestone, 4), "X  ", "XX ", "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestoneWall, 1), "XXX", "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.axeStone, 1), "XX ", "XW ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.pickaxeStone, 1), "XXX", " W ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.hoeStone, 1), "XX ", " W ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.shovelStone, 1), " X ", " W ", " W ", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.swordStone, 1), "X", "X", "W", 'X', "stoneCobble", 'W', Item.stick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.brewingStand, 1), " B ", "XXX", 'X', "stoneCobble", 'B', Item.blazeRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.dispenser, 1), "XXX", "XBX", "XRX", 'X', "stoneCobble", 'B', Item.bow, 'R', Item.redstone));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.pressurePlateStone, 1), "XX", 'X', "stoneSmooth"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), "XXX", 'X', "stoneCobble"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), "XXX", 'X', "stoneSmooth"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.redstoneRepeater, 1), "TRT", "XXX", 'X', "stoneSmooth", 'T', Block.torchRedstoneActive, 'R', Item.redstone));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stairsStoneBrick, 4), "X  ", "XX ", "XXX", 'X', "stoneBricks"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.stoneButton, 1), "stoneSmooth"));
        }
        
        GameRegistry.addRecipe(new ItemStack(Item.coal, 1), "XXX", "XXX", "XXX", 'X', ligniteCoal);
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 15), new ItemStack(fossilPiece, 1, WILDCARD_VALUE));
        
        //vanilla cobblestone
        switch (vanillaStoneCrafting)
        {
            case 1:
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.cobblestone, 1), "stoneCobble"));
                break;
            case 2:
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.cobblestone, 1), Item.redstone, "stoneCobble"));
                break;
            case 3:
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestone, 1), "XX", "XX", 'X', "stoneCobble"));
                break;
            case 4:
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestone, 4), "XX", "XX", 'X', "stoneCobble"));
                break;
            default:
                break;
        }
        
        for (int i = 0; i < 8; i++)
        {
            GameRegistry.addRecipe(new ItemStack(igneousBrickSlabHalf, 6, i), "XXX", 'X', new ItemStack(igneousStoneBrick, 1, i));
            GameRegistry.addRecipe(new ItemStack(metamorphicBrickSlabHalf, 6, i), "XXX", 'X', new ItemStack(metamorphicStoneBrick, 1, i));

            FurnaceRecipes.smelting().addSmelting(metamorphicCobblestone.blockID, i, new ItemStack(metamorphicStone, 1, i), 0.1f);
            FurnaceRecipes.smelting().addSmelting(igneousCobblestone.blockID, i, new ItemStack(igneousStone, 1, i), 0.1f);

            GameRegistry.addRecipe(new ItemStack(metamorphicStoneBrick, 4, i), "xx", "xx", 'x', new ItemStack(metamorphicStone, 1, i));
            GameRegistry.addRecipe(new ItemStack(igneousStoneBrick, 4, i), "xx", "xx", 'x', new ItemStack(igneousStone, 1, i));
        }
        
        GameRegistry.registerFuelHandler(new FuelManager());
    }
    
    public void addOreDicts()
    {
        OreDictionary.registerOre("stoneSmooth", new ItemStack(igneousStone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneSmooth", new ItemStack(metamorphicStone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneCobble", new ItemStack(igneousCobblestone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneCobble", new ItemStack(metamorphicCobblestone, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneBricks", new ItemStack(igneousStoneBrick, 1, WILDCARD_VALUE));
        OreDictionary.registerOre("stoneBricks", new ItemStack(metamorphicStoneBrick, 1, WILDCARD_VALUE));
    }
    
    public static int oreDictifyStone() throws Exception
    {
        int numReplaced = 0;
        Map<ItemStack, String> replacements = new HashMap<ItemStack, String>();
        replacements.put(new ItemStack(Block.stone, 1, WILDCARD_VALUE), "stoneSmooth");
        replacements.put(new ItemStack(Block.cobblestone, 1, WILDCARD_VALUE), "stoneCobble");
        replacements.put(new ItemStack(Block.stoneBrick, 1, WILDCARD_VALUE), "stoneBricks");
        ItemStack[] replaceStacks = replacements.keySet().toArray(new ItemStack[replacements.keySet().size()]);

        // Ignore recipes for the following items
        ItemStack[] exclusions = new ItemStack[]
        {
            new ItemStack(Block.stairsStoneBrick),
            new ItemStack(Block.stoneBrick),
            new ItemStack(Block.stoneSingleSlab, 1, 5),
        };
        List recipes = CraftingManager.getInstance().getRecipeList();
        Constructor shapedConstr = ShapedOreRecipe.class.getDeclaredConstructor(ShapedRecipes.class, Map.class);
        Constructor shapelessConstr = ShapelessOreRecipe.class.getDeclaredConstructor(ShapelessRecipes.class, Map.class);
        shapedConstr.setAccessible(true);
        shapelessConstr.setAccessible(true);

        // Add ore dictionary entries for replaced blocks
        for (ItemStack stack : replacements.keySet())
        {
            OreDictionary.registerOre(replacements.get(stack), stack);
        }

        // Search stone recipes for recipes to replace
        for (int i = 0; i < recipes.size(); i++)
        {
            Object obj = recipes.get(i);
            ItemStack output = ((IRecipe)obj).getRecipeOutput();
            if (output != null && containsMatch(false, exclusions, output))
            {
                continue;
            }
            if (obj instanceof ShapedRecipes)
            {
                ShapedRecipes recipe = (ShapedRecipes)obj;
                if (containsMatch(true, recipe.recipeItems, replaceStacks))
                {
                    recipes.set(i, (ShapedOreRecipe)shapedConstr.newInstance(recipe, replacements));
                    numReplaced++;
                    System.out.println("Changed shaped recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapelessRecipes)
            {
                ShapelessRecipes recipe = (ShapelessRecipes)obj;
                if (containsMatch(true, (ItemStack[])recipe.recipeItems.toArray(new ItemStack[recipe.recipeItems.size()]), replaceStacks))
                {
                    recipes.set(i, (ShapelessOreRecipe)shapelessConstr.newInstance(recipe, replacements));
                    numReplaced++;
                    System.out.println("Changed shapeless recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapedOreRecipe)
            {
                ShapedOreRecipe recipe = (ShapedOreRecipe)obj;
                if (containsMatchReplaceInplace(true, recipe.getInput(), replaceStacks, replacements))
                {
                    numReplaced++;
                    System.out.println("Changed shaped ore recipe for " + output.getItemName());
                }
            }
            else if (obj instanceof ShapelessOreRecipe)
            {
                ShapelessOreRecipe recipe = (ShapelessOreRecipe)obj;
                if (containsMatchReplaceInplace(true, recipe.getInput(), replaceStacks, replacements))
                {
                    numReplaced++;
                    System.out.println("Changed shapeless ore recipe for " + output.getItemName());
                }
            }
        }
        return numReplaced;
    }
    
    private static boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack... targets)
    {
        for (ItemStack input : inputs)
        {
            for (ItemStack target : targets)
            {
                if (OreDictionary.itemMatches(target, input, strict))
                {
                    return true;
                }
            }
        }
        return false;
    }

    // Doing what Forge tells not to do
    private static boolean containsMatchReplaceInplace(boolean strict, Object inputArrayOrList, ItemStack[] targets, Map<ItemStack, String> replacements)
    {
        boolean replaced = false;
        if (inputArrayOrList instanceof ArrayList)
        {
            ArrayList inputList = (ArrayList)inputArrayOrList;
            for (int i = 0; i < inputList.size(); i++)
            {
                Object input = inputList.get(i);
                if (input instanceof ItemStack)
                {
                    for (ItemStack target : targets)
                    {
                        if (OreDictionary.itemMatches(target, (ItemStack)input, strict))
                        {
                            inputList.set(i, OreDictionary.getOres(replacements.get(target)));
                            replaced = true;
                        }
                    }
                }
            }
        } else {
            // Expect array
            Object[] inputArray = (Object[])inputArrayOrList;
            for (int i = 0; i < inputArray.length; i++)
            {
                Object input = inputArray[i];
                if (input instanceof ItemStack)
                {
                    for (ItemStack target : targets)
                    {
                        if (OreDictionary.itemMatches(target, (ItemStack)input, strict))
                        {
                            inputArray[i] = OreDictionary.getOres(replacements.get(target));
                            replaced = true;
                        }
                    }
                }
            }
        }
        return replaced;
    }
    
    public static long getWorldSeed()
    {
        return worldSeed;
    }
    
    public static World getWorld()
    {
        return world;
    }
    
    public static WorldGenManager getWorldGenManager()
    {
        return worldGen;
    }
    
    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event)
    {
        if (gotWorldSeed)
        {
            return;
        }
        world = event.world;
        worldSeed = world.getSeed();
        gotWorldSeed = true;
        worldGen = new WorldGenManager(worldSeed, world.getWorldInfo().getTerrainType(), world);
    }
    
    @ForgeSubscribe
    public void onWorldUnload(WorldEvent.Unload event)
    {
        gotWorldSeed = false;
    }
    
    @ForgeSubscribe
    public void onBiomeDecorate(DecorateBiomeEvent.Post event)
    {
        int id = event.world.provider.dimensionId;
        if (includeDimensions.equals("*"))
        {
            if (excludeDimensionIDs.contains(id)) return;
        } else {
            if (!includeDimensionIDs.contains(id)) return;
        }
        // Sometimes can get called before onWorldLoad, wtf?
        if (worldGen == null)
        {
            System.out.println("UndergroundBiomes warning: onBiomeDecorate before onWorldLoad! Ignoring.");
            return;
        }
        worldGen.onBiomeDecorate(event);
    }

    @ForgeSubscribe
    public void registerOre(OreDictionary.OreRegisterEvent event)
    {
        if (Arrays.asList(nuggets).contains(event.Name))
        {
            nuggets.add(event.Ore);
        }
    }
}
