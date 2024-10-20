package net.minecraft.world.item;

import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagsBlock;
import net.minecraft.world.EnumInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.decoration.EntityLeash;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.item.context.ItemActionContext;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AxisAlignedBB;

public class ItemLeash extends Item {

    public ItemLeash(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public EnumInteractionResult useOn(ItemActionContext itemactioncontext) {
        World world = itemactioncontext.getLevel();
        BlockPosition blockposition = itemactioncontext.getClickedPos();
        IBlockData iblockdata = world.getBlockState(blockposition);

        if (iblockdata.is(TagsBlock.FENCES)) {
            EntityHuman entityhuman = itemactioncontext.getPlayer();

            if (!world.isClientSide && entityhuman != null) {
                bindPlayerMobs(entityhuman, world, blockposition);
            }

            return EnumInteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return EnumInteractionResult.PASS;
        }
    }

    public static EnumInteractionResult bindPlayerMobs(EntityHuman entityhuman, World world, BlockPosition blockposition) {
        EntityLeash entityleash = null;
        double d0 = 7.0D;
        int i = blockposition.getX();
        int j = blockposition.getY();
        int k = blockposition.getZ();
        AxisAlignedBB axisalignedbb = new AxisAlignedBB((double) i - 7.0D, (double) j - 7.0D, (double) k - 7.0D, (double) i + 7.0D, (double) j + 7.0D, (double) k + 7.0D);
        List<EntityInsentient> list = world.getEntitiesOfClass(EntityInsentient.class, axisalignedbb, (entityinsentient) -> {
            return entityinsentient.getLeashHolder() == entityhuman;
        });

        EntityInsentient entityinsentient;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); entityinsentient.setLeashedTo(entityleash, true)) {
            entityinsentient = (EntityInsentient) iterator.next();
            if (entityleash == null) {
                entityleash = EntityLeash.getOrCreateKnot(world, blockposition);
                entityleash.playPlacementSound();
            }
        }

        if (!list.isEmpty()) {
            world.gameEvent((Holder) GameEvent.BLOCK_ATTACH, blockposition, GameEvent.a.of((Entity) entityhuman));
            return EnumInteractionResult.SUCCESS;
        } else {
            return EnumInteractionResult.PASS;
        }
    }
}
