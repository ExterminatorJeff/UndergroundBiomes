package exterminatorJeff.undergroundBiomes.worldGen;

public class StrataLayer{
	
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
