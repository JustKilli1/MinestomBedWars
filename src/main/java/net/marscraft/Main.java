package net.marscraft;

import net.marscraft.commands.TestCommand;
import net.marscraft.commands.TestCommandJoin;
import net.marscraft.commands.TestLeave;
import net.marscraft.instances.BedWarsInstanceManager;
import net.marscraft.instances.InstanceImporter;
import net.marscraft.server.Server;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

public class Main {

    public static InstanceContainer mainInstance;
    public static BedWarsInstanceManager bwInstanceManager;

    public static void main(String[] args) {
        System.out.println("Hello world!");

        MinecraftServer server = MinecraftServer.init();
        Server.createFolderStructure();

        bwInstanceManager = new BedWarsInstanceManager();
        InstanceManager instanceManager = new InstanceManager();
        MinecraftServer.getCommandManager().register(new TestCommand());
        MinecraftServer.getCommandManager().register(new TestCommandJoin());
        MinecraftServer.getCommandManager().register(new TestLeave());
        mainInstance = InstanceImporter.importWorld("instances/world");

                /*instanceManager.createInstanceContainer();
        container.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 50, Block.STONE);
        });*/

        MojangAuth.init();
        registerEvents(mainInstance);
        server.start("0.0.0.0", 25565);
    }

    public static void registerEvents(InstanceContainer spawningInstance) {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
           final Player player = event.getPlayer();
           event.setSpawningInstance(spawningInstance);
           player.setRespawnPoint(new Pos(0, 42, 0));
           player.setGameMode(GameMode.CREATIVE);
        });
    }
}