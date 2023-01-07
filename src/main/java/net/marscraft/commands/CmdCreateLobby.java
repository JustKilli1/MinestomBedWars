package net.marscraft.commands;

import net.marscraft.Main;
import net.marscraft.instances.BedWarsInstanceManager;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;


public class CmdCreateLobby extends Command {

    private static final BedWarsInstanceManager instanceManager = Main.bwInstanceManager;

    public CmdCreateLobby() {
        super("create");
        setDefaultExecutor(((sender, context) -> sender.sendMessage("/lobby create. /lobby join <id>")));

        var optionMaxPlayer = ArgumentType.Integer("Max Player");
        addSyntax(((sender, context) -> {
            int maxPlayer = context.get(optionMaxPlayer);
            instanceManager.addNewLobby(maxPlayer);
            System.out.println("Create geklappt");
            System.out.println(BedWarsInstanceManager.getLobbys());
        }), optionMaxPlayer);

    }


}
