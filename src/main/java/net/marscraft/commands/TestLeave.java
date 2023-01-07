package net.marscraft.commands;

import net.marscraft.Main;
import net.marscraft.instances.BedWarsInstanceManager;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class TestLeave extends Command {

    public TestLeave() {
        super("leave");
        setDefaultExecutor(((sender, context) -> sender.sendMessage("/lobby create. /lobby join <id>")));

        addSyntax(((sender, context) -> {
            final Player player = (Player) sender;
            Main.bwInstanceManager.leaveLobby(player);
            System.out.println("Create geklappt");
            System.out.println(BedWarsInstanceManager.getLobbys());
        }));

    }

}
