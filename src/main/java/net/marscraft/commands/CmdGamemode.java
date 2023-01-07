package net.marscraft.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class CmdGamemode extends Command {

    public CmdGamemode() {
        super("gamemode", "gm");
        setDefaultExecutor(((sender, context) -> sender.sendMessage("Use: /gamemode [0, 1]")));

        var optionMode = ArgumentType.Integer("Mode");
        addSyntax(((sender, context) -> {
            int mode = context.get(optionMode);
            final Player player = (Player) sender;
            player.setGameMode(GameMode.fromId(mode));
            player.sendMessage("Gamemode changed to " + player.getGameMode().name());
        }), optionMode);
    }

}
