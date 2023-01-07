package net.marscraft.commands;

import net.marscraft.Main;
import net.marscraft.instances.BedWarsInstanceManager;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class CmdJoinLobby extends Command {

    private static final BedWarsInstanceManager instanceManager = Main.bwInstanceManager;

    public CmdJoinLobby() {
        super("join");
        setDefaultExecutor(((sender, context) -> sender.sendMessage("/lobby create. /lobby join <id>")));

        var optionInt = ArgumentType.Integer("join");
        addSyntax(((sender, context) -> {
            final Player player = (Player) sender;
            int id = context.get(optionInt);
            instanceManager.joinLobby(player, id);
            System.out.println("Join geklappt");
        }), optionInt);

    }

}
