package exterminatorJeff.undergroundBiomes.common.command;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;

public class CommandOreDictifyStone extends CommandBase
{

    public CommandOreDictifyStone()
    {
    }

    public String getCommandName()
    {
        return "oredictifystone";
    }

    public void processCommand(ICommandSender sender, String as[])
    {
        try
        {
            int num = UndergroundBiomes.oreDictifyStone();
            sender.sendChatToPlayer(String.format("\u00a7aStone OreDictify complete, modified %d recipes.", num));
        }
        catch (Exception e)
        {
            sender.sendChatToPlayer("\u00a7cStone OreDictify has failed!");
            e.printStackTrace();
        }
    }
}
