/**
 * This interface indicates the block provides a workaround for rendering calls
 * that don't permit getting information from tile entities. The set call can store
 * an icon the block then provides from getIcon(side) and getIcon(side,metadata) calls
 * @author Zeno410
 */

package exterminatorJeff.undergroundBiomes.constructs.block;

import net.minecraft.util.Icon;

public interface IconKludgeable {
    public abstract void setIconKludge(Icon toUse);
}
