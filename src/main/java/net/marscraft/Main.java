package net.marscraft;

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
    public static void main(String[] args) {
        System.out.println("Hello world!");

        MinecraftServer server = MinecraftServer.init();
        Server.createFolderStructure();

        InstanceManager instanceManager = new InstanceManager();
        InstanceContainer container = instanceManager.createInstanceContainer();
        container.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 50, Block.STONE);
        });
        
        MojangAuth.init();
        registerEvents(container);
        server.start("0.0.0.0", 25565);
    }

    private static void registerEvents(InstanceContainer spawningInstance) {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
           final Player player = event.getPlayer();
           event.setSpawningInstance(spawningInstance);
           player.setRespawnPoint(new Pos(0, 42, 0));
           player.setGameMode(GameMode.CREATIVE);
        });
    }


}