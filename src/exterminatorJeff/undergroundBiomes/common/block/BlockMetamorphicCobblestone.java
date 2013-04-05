package exterminatorJeff.undergroundBiomes.common.block;


public class BlockMetamorphicCobblestone extends BlockMetamorphicStone{

	public BlockMetamorphicCobblestone(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public String getBlockName(int index){
		String s = this.getUnlocalizedName();
		return super.getBlockName(index) + "Cobble";
	}
	
	

}
