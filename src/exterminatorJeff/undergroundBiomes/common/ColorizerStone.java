package exterminatorJeff.undergroundBiomes.common;

public class ColorizerStone {
	
	public static int getIgneousStoneColour(int metadataID){
		int colour = 0;
		switch(metadataID){
			case(0): colour = (int)Integer.parseInt("cba186", 16);//red granite
				break;
			case(1): colour = (int)Integer.parseInt("36362f", 16);//black granite
				break;
			case(2): colour = (int)Integer.parseInt("909485", 16);//rhyolite
				break;
			case(3): colour = (int)Integer.parseInt("696f6f", 16);//andesite
				break;
			case(4): colour = (int)Integer.parseInt("7e7e7e", 16);//gabbro
				break;
			case(5): colour = (int)Integer.parseInt("8d8d8c", 16);//basalt
				break;
			case(6): colour = (int)Integer.parseInt("929292", 16);//komatiite
				break;
			case(7): colour = (int)Integer.parseInt("909090", 16);//epidote
				break;
			default: colour = (int)Integer.parseInt("ffffff", 16);//error
		}
		return colour;
	}
	
	public static int getMetamorphicStoneColour(int metadataID){
		int colour = 0;
		switch(metadataID){
			case(0): colour = (int)Integer.parseInt("8d8c8c", 16);//gneiss
				break;
			case(1): colour = (int)Integer.parseInt("91847c", 16);//eclogite
				break;
			case(2): colour = (int)Integer.parseInt("ccd4d7", 16);//marble
				break;
			case(3): colour = (int)Integer.parseInt("b0927c", 16);//quartzite
				break;
			case(4): colour = (int)Integer.parseInt("7a9eb7", 16);//blueschist
				break;
			case(5): colour = (int)Integer.parseInt("839179", 16);//greenschist
				break;
			case(6): colour = (int)Integer.parseInt("d2d4d1", 16);//soapstone
				break;
			case(7): colour = (int)Integer.parseInt("78797a", 16);//migmatite
				break;
			default: colour = (int)Integer.parseInt("ffffff", 16);//error
		}
		return colour;
	}
	
	public static int getSedimentaryStoneColour(int metadataID){
		int colour = 0;
		switch(metadataID){
			case(0): colour = (int)Integer.parseInt("ebebe6", 16);//Limestone
				break;
			case(1): colour = (int)Integer.parseInt("f7f2dd", 16);//chalk
				break;
			case(2): colour = (int)Integer.parseInt("484d52", 16);//shale
				break;
			case(3): colour = (int)Integer.parseInt("a57b69", 16);//siltstone
				break;
			case(4): colour = (int)Integer.parseInt("312b28", 16);//lignite
				break;
			case(5): colour = (int)Integer.parseInt("77766c", 16);//flint
				break;
			case(6): colour = (int)Integer.parseInt("6d6c60", 16);//greywacke
				break;
			case(7): colour = (int)Integer.parseInt("7f6f5b", 16);//chert
				break;
			default: colour = (int)Integer.parseInt("ffffff", 16);//error
		}
		return colour;
	}

}
