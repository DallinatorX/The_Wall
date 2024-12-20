package net.minecraft.network.protocol.game;

import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.EntityExperienceOrb;

public class PacketPlayOutSpawnEntityExperienceOrb implements Packet<PacketListenerPlayOut> {

    public static final StreamCodec<PacketDataSerializer, PacketPlayOutSpawnEntityExperienceOrb> STREAM_CODEC = Packet.codec(PacketPlayOutSpawnEntityExperienceOrb::write, PacketPlayOutSpawnEntityExperienceOrb::new);
    private final int id;
    private final double x;
    private final double y;
    private final double z;
    private final int value;

    public PacketPlayOutSpawnEntityExperienceOrb(EntityExperienceOrb entityexperienceorb) {
        this.id = entityexperienceorb.getId();
        this.x = entityexperienceorb.getX();
        this.y = entityexperienceorb.getY();
        this.z = entityexperienceorb.getZ();
        this.value = entityexperienceorb.getValue();
    }

    private PacketPlayOutSpawnEntityExperienceOrb(PacketDataSerializer packetdataserializer) {
        this.id = packetdataserializer.readVarInt();
        this.x = packetdataserializer.readDouble();
        this.y = packetdataserializer.readDouble();
        this.z = packetdataserializer.readDouble();
        this.value = packetdataserializer.readShort();
    }

    private void write(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeVarInt(this.id);
        packetdataserializer.writeDouble(this.x);
        packetdataserializer.writeDouble(this.y);
        packetdataserializer.writeDouble(this.z);
        packetdataserializer.writeShort(this.value);
    }

    @Override
    public PacketType<PacketPlayOutSpawnEntityExperienceOrb> type() {
        return GamePacketTypes.CLIENTBOUND_ADD_EXPERIENCE_ORB;
    }

    public void handle(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.handleAddExperienceOrb(this);
    }

    public int getId() {
        return this.id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public int getValue() {
        return this.value;
    }
}
