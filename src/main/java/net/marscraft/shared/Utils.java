package net.marscraft.shared;

import net.marscraft.minigame.Member;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    public static List<Member> getPlayerWithUuid(List<Member> teamMember, Predicate<Member> condition) {
        return teamMember.stream().filter(condition).collect(Collectors.toList());
    }

}
