package net.marscraft.minigame;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.marscraft.Main;
import net.marscraft.instances.MinigameInstance;
import net.marscraft.instances.MinigameInstanceType;
import net.marscraft.instances.MinigameState;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Lobby extends MinigameInstance {

    private List<Team> teams;
    private MinigameState state;


    public Lobby(int id, InstanceContainer container, List<Team> teams, int maxPlayer) {
        super(id, container);
        this.teams = teams;
        setTeamSizes(maxPlayer);
    }

    /**
     * Sets the Team size for the different Teams automatically
     * @param maxPlayer How many Player are allowed in the Lobby
     * */
    private void setTeamSizes(int maxPlayer) {
        int remainder = maxPlayer % teams.size();
        int teamSize = (maxPlayer - remainder) / teams.size();
        for(int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            team.setMaxMember(remainder > 0 ? teamSize + 1 : teamSize);
            remainder--;
        }
    }

    /**
     * Try to connect a Player to a Lobby
     * */
    public boolean join(Player player) {
        System.out.println("Gejoint");
        Member member = new Member(player);
        Optional<Team> team = teams.stream().min(Comparator.comparingInt(Team::getMemberCount));
        if(!team.isPresent()) return false;
        if(!team.get().addMember(member)) return false;
        player.setInstance(getContainer(), new Pos(0, 100, 0));
        player.setCustomName(Component.text(team.get().getColorCode()+ team.get().getName() + " " + player.getName()));
        player.setCustomNameVisible(true);
        player.sendMessage("Team " + team.get().getName() + " beigetreten");
        return true;
    }

    public void leave(Member member) {
        System.out.println("Verlassen");
        for (Team team : teams) {
            if(team.hasMember(member)) {
                team.removeMember(member);
                member.getPlayer().setInstance(Main.mainInstance);
                break;
            }
        }
    }
}
