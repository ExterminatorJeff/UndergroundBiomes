package exterminatorJeff.undergroundBiomes.common.command;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

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

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "/" + this.getCommandName();
    }

    public void processCommand(ICommandSender sender, String as[])
    {
        try
        {
            int num = UndergroundBiomes.oreDictifyStone();
            sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(String.format("commands.oredictifystone.ok", num)).setColor(EnumChatFormatting.GREEN));
            //sender.sendChatToPlayer(String.format("\u00a7aStone OreDictify complete, modified %d recipes.", num));
        }
        catch (Exception e)
        {
            sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.oredictifystone.fail").setColor(EnumChatFormatting.RED));
            //sender.sendChatToPlayer("\u00a7cStone OreDictify has failed!");
            e.printStackTrace();
        }
    }
}
