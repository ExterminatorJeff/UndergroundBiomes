/**
 *
 * @author Zeno410
 * from tutorial by WiduX
 */
package exterminatorJeff.undergroundBiomes.constructs.entity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;


import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class UndergroundBiomesTileEntity extends TileEntity {
    public static String IndexName = "index";
    private int masterIndex;

    public UndergroundBiomesTileEntity() {}
        // must be no parameters for Forge
   
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        masterIndex = nbt.getInteger(IndexName);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger(IndexName, masterIndex);
    }

    @Override
    public Packet getDescriptionPacket() {
       NBTTagCompound tileTag = new NBTTagCompound();
       this.writeToNBT(tileTag);
       return new Packet132TileEntityData(this.xCoord,this.yCoord, this.zCoord,0,tileTag);
    }

    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        this.readFromNBT(packet.customParam1);
    }

    public final int masterIndex() {return masterIndex;}

    public final void setMasterIndex(int index) {masterIndex = index;}
}
