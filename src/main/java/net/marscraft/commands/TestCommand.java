package net.marscraft.commands;

import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.entity.fakeplayer.FakePlayerOption;

import java.util.UUID;

public class TestCommand extends Command {

    public TestCommand() {
        super("test");
        setDefaultExecutor(((sender, context) -> sender.sendMessage("Use: /test")));

        addSyntax((sender, context) -> {
            final Player player = (Player) sender;
            FakePlayer.initPlayer(UUID.randomUUID(), "Test NPC", new FakePlayerOption().setInTabList(false),fakePlayer -> {
                fakePlayer.setSkin(PlayerSkin.fromUsername("JustKilli"));
                fakePlayer.setCustomName(Component.text("§c" + player.getName()));
                fakePlayer.setCustomNameVisible(true);
                fakePlayer.setInstance(player.getInstance(), player.getPosition());
            });
        });

    }

}
