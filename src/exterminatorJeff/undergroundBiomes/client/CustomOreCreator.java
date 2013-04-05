package exterminatorJeff.undergroundBiomes.client;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class CustomOreCreator {/*
	
	Block[] blockList;
	public ArrayList<Block> ores;
	
	public CustomOreCreator(){
		blockList = Block.blocksList;
		GetListOfOres();
		ProcessTextures();
		PrintOresList();
	}
	
	//Get a list of all the blocks with Ore in their name
	public void GetListOfOres(){
		ores = new ArrayList<Block>();
		for(Block b: blockList){
			if(b != null){
				String name = b.getUnlocalizedName();
				if(name != null){
					if(name.contains("ore") || name.contains("Ore")){
						ores.add(b);
					}
				}
			}
		}
	}
	
	public void ProcessTextures(){
		//All ores are assumed to be overlaid over stone
		BufferedImage stoneTexture = GetStone();
		int[][] stonePixels = GetImagePixels(stoneTexture);
		
		for(Block b: ores){
			try {
				//Get the file path of the texture
				String directory = Minecraft.getMinecraftDir().getCanonicalPath();
				String file = directory + b.getTextureFile();
				int index = b.blockIndexInTexture;
				
				//ImageIO.write(stoneTexture, "png", new File(directory + "/stone.png"));
				
				boolean fileExists = new File(file).exists();
				if(fileExists){
					BufferedImage texture = ImageIO.read(new File(file));
					int resolution = texture.getHeight() / 16;
					int imageY = (index / 16) * resolution;
					int imageX = ((index % 16) * resolution);
					//Get the actual block texture from the sheet
					BufferedImage blockTexture = texture.getSubimage(imageX, imageY, resolution, resolution);
					//Get the pixel difference between stone and the ore texture
					BufferedImage diffTexture = GetDiffImage(stonePixels, GetImagePixels(blockTexture));
					
					
					ImageIO.write(diffTexture, "png", new File(directory + "/" + b.getBlockName() + ".png"));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Gets all the pixel values in an image
	public int[][] GetImagePixels(BufferedImage img){
		int[][] pixels = new int[img.getWidth()][img.getHeight()];
		for( int i = 0; i < img.getWidth(); i++){
		    for( int j = 0; j < img.getHeight(); j++){
		        pixels[i][j] = img.getRGB(i, j);
		    }
		}
		return pixels;
	}
	
	//Gets the pixels which are not the same between the two images
	public BufferedImage GetDiffImage(int[][] stone, int [][] ore){
		int[][] imageDiffPixels = new int[stone.length][stone[0].length];
		for(int i = 0; i < stone.length; i++){
			for(int j = 0; j < stone[i].length; j++){
				//if(stone[i][j] != ore[i][j]){
				if(!WithinRange(stone[i][j], ore[i][j], 15)){
					imageDiffPixels[i][j] = ore[i][j];
				}
			}
		}
		BufferedImage diffResult = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < stone.length; i++){
			for(int j = 0; j < stone[i].length; j++){
				diffResult.setRGB(i, j, imageDiffPixels[i][j]);
			}
		}
		return diffResult;
	}
	
	//Stone is a special case
	public BufferedImage GetStone(){
		try{
			String directory = Minecraft.getMinecraftDir().getCanonicalPath();
			String file = directory + Block.stone.getTextureFile();
			BufferedImage texture = ImageIO.read(new File(file));
			int resolution = texture.getHeight() / 16;
			return texture.getSubimage(16, 0, resolution, resolution);
		}catch(Exception e){
			
		}
		return null;
	}
	
	//Test whether a pixel value is within range of another
	public boolean WithinRange(int a, int b, int range){
		int aR = (a)&0xFF;
		int aG = (a>>8)&0xFF;
		int aB = (a>>16)&0xFF;
		//int aA = (a>>24)&0xFF;
		int aAvg = (aR + aG + aB) / 3;
		
		int bR = (b)&0xFF;
		int bG = (b>>8)&0xFF;
		int bB = (b>>16)&0xFF;
		//int bA = (b>>24)&0xFF;
		int bAvg = (bR + bG + bB) / 3;
		
		int rR = aR - bR;
		int rG = aG - bG;
		int rB = aB - bB;
		boolean test1 = (LessOrGreaterThan(rR, range) || LessOrGreaterThan(rG, range) || LessOrGreaterThan(rB, range));
		if(LessOrGreaterThan(aAvg - bAvg, range) || test1){
			return true;
		}else{
			return false;
		}
		
	}
	
	//Test whether a number is within plus or minus a given range
	public boolean LessOrGreaterThan(int a, int amount){
		if(a >= -amount && a <= amount){
			return true;
		}else{
			return false;
		}
	}
	
	public void PrintOresList(){
		for(Block b: ores){
			System.err.println(b.getBlockName());
		}
	}*/
	
}
