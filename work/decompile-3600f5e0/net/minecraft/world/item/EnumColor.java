package net.minecraft.world.item;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.INamable;
import net.minecraft.world.level.material.MaterialMapColor;
import org.jetbrains.annotations.Contract;

public enum EnumColor implements INamable {

    WHITE(0, "white", 16383998, MaterialMapColor.SNOW, 15790320, 16777215), ORANGE(1, "orange", 16351261, MaterialMapColor.COLOR_ORANGE, 15435844, 16738335), MAGENTA(2, "magenta", 13061821, MaterialMapColor.COLOR_MAGENTA, 12801229, 16711935), LIGHT_BLUE(3, "light_blue", 3847130, MaterialMapColor.COLOR_LIGHT_BLUE, 6719955, 10141901), YELLOW(4, "yellow", 16701501, MaterialMapColor.COLOR_YELLOW, 14602026, 16776960), LIME(5, "lime", 8439583, MaterialMapColor.COLOR_LIGHT_GREEN, 4312372, 12582656), PINK(6, "pink", 15961002, MaterialMapColor.COLOR_PINK, 14188952, 16738740), GRAY(7, "gray", 4673362, MaterialMapColor.COLOR_GRAY, 4408131, 8421504), LIGHT_GRAY(8, "light_gray", 10329495, MaterialMapColor.COLOR_LIGHT_GRAY, 11250603, 13882323), CYAN(9, "cyan", 1481884, MaterialMapColor.COLOR_CYAN, 2651799, 65535), PURPLE(10, "purple", 8991416, MaterialMapColor.COLOR_PURPLE, 8073150, 10494192), BLUE(11, "blue", 3949738, MaterialMapColor.COLOR_BLUE, 2437522, 255), BROWN(12, "brown", 8606770, MaterialMapColor.COLOR_BROWN, 5320730, 9127187), GREEN(13, "green", 6192150, MaterialMapColor.COLOR_GREEN, 3887386, 65280), RED(14, "red", 11546150, MaterialMapColor.COLOR_RED, 11743532, 16711680), BLACK(15, "black", 1908001, MaterialMapColor.COLOR_BLACK, 1973019, 0);

    private static final IntFunction<EnumColor> BY_ID = ByIdMap.continuous(EnumColor::getId, values(), ByIdMap.a.ZERO);
    private static final Int2ObjectOpenHashMap<EnumColor> BY_FIREWORK_COLOR = new Int2ObjectOpenHashMap((Map) Arrays.stream(values()).collect(Collectors.toMap((enumcolor) -> {
        return enumcolor.fireworkColor;
    }, (enumcolor) -> {
        return enumcolor;
    })));
    public static final INamable.a<EnumColor> CODEC = INamable.fromEnum(EnumColor::values);
    public static final StreamCodec<ByteBuf, EnumColor> STREAM_CODEC = ByteBufCodecs.idMapper(EnumColor.BY_ID, EnumColor::getId);
    private final int id;
    private final String name;
    private final MaterialMapColor mapColor;
    private final float[] textureDiffuseColors;
    private final int fireworkColor;
    private final int textColor;

    private EnumColor(final int i, final String s, final int j, final MaterialMapColor materialmapcolor, final int k, final int l) {
        this.id = i;
        this.name = s;
        this.mapColor = materialmapcolor;
        this.textColor = l;
        int i1 = (j & 16711680) >> 16;
        int j1 = (j & '\uff00') >> 8;
        int k1 = (j & 255) >> 0;

        this.textureDiffuseColors = new float[]{(float) i1 / 255.0F, (float) j1 / 255.0F, (float) k1 / 255.0F};
        this.fireworkColor = k;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float[] getTextureDiffuseColors() {
        return this.textureDiffuseColors;
    }

    public MaterialMapColor getMapColor() {
        return this.mapColor;
    }

    public int getFireworkColor() {
        return this.fireworkColor;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public static EnumColor byId(int i) {
        return (EnumColor) EnumColor.BY_ID.apply(i);
    }

    @Nullable
    @Contract("_,!null->!null;_,null->_")
    public static EnumColor byName(String s, @Nullable EnumColor enumcolor) {
        EnumColor enumcolor1 = (EnumColor) EnumColor.CODEC.byName(s);

        return enumcolor1 != null ? enumcolor1 : enumcolor;
    }

    @Nullable
    public static EnumColor byFireworkColor(int i) {
        return (EnumColor) EnumColor.BY_FIREWORK_COLOR.get(i);
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
