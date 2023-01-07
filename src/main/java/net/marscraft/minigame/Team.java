package net.marscraft.minigame;

import net.marscraft.shared.Utils;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Team {

    private int id, colorCode, maxMember;
    private String name;
    private List<Member> teamMember;

    public Team(int id, int colorCode, String name, List<Member> teamMember) {
        this.id = id;
        this.colorCode = colorCode;
        this.name = name;
        this.teamMember = teamMember;
    }

    public Team(int id, int colorCode, String name) {
        this(id, colorCode, name, new ArrayList<>());
    }

    /**
     * Adds a new Member if maxMember > currentMember
     * @param member Member that gets added if maxMember is not reached
     * @return Returns true if Member was successfully added to the Team.<p>
     *         Returns False when the maxMember count is reached
     * */
    public boolean addMember(Member member) {
        if(teamMember.size() == maxMember) return false;
        return teamMember.add(member);
    }

    /**
     * Removes a Member from the Team
     * @param playerUuid The User that gets removed
     * */
    public void removeMember(Member member) {
        teamMember.remove(member);
    }

    public Optional<Member> getMember(UUID playerUuid) {
        List<Member> member = Utils.getPlayerWithUuid(teamMember, mem -> mem.getPlayer().getUuid() != playerUuid);
        if (member == null) return Optional.empty();
        if(member.size() != 1) return Optional.empty();

        return Optional.ofNullable(member.get(0));
    }

    public boolean hasMember(Member member) {
        return getMember(member.getPlayer().getUuid()).isPresent();
    }

    public int getMemberCount() {
        return teamMember.size();
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public int getColorCode() {
        return colorCode;
    }

    public String getName() {
        return name;
    }
}
