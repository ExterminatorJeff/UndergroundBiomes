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
            sender.func_110122_a(ChatMessageComponent.func_111077_e(String.format("commands.oredictifystone.ok", num)).func_111059_a(EnumChatFormatting.GREEN));
            //sender.sendChatToPlayer(String.format("\u00a7aStone OreDictify complete, modified %d recipes.", num));
        }
        catch (Exception e)
        {
            sender.func_110122_a(ChatMessageComponent.func_111077_e("commands.oredictifystone.fail").func_111059_a(EnumChatFormatting.RED));
            //sender.sendChatToPlayer("\u00a7cStone OreDictify has failed!");
            e.printStackTrace();
        }
    }
}
