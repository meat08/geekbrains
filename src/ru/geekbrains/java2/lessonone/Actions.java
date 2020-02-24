package ru.geekbrains.java2.lessonone;


import java.util.*;

public class Actions {

    public static void main(String[] args) {
        List<Barriers> barriers = new ArrayList<>();
        List<Members> members = new ArrayList<>();

        barriers.add(new Lane(450));
        barriers.add(new Lane(120));
        barriers.add(new Wall(3));
        members.add(new Cat("Карл"));
        members.add(new Human("Маркс", 56));
        members.add(new Human("Энгельс", 12));
        members.add(new Robot("R2D2"));

        actions(barriers, members);

    }

    private static void actions (List<Barriers> barriers, List<Members> members) {
        for(Barriers barrier : barriers) {
            for(Members member : members) {
                if(member.getSuccessAction()) {
                    if(barrier.doAction(member)) {
                        System.out.print(" и справился." + System.lineSeparator());
                    } else {
                        System.out.print(" и не справился. Снят с участия." + System.lineSeparator());
                        member.setSuccessAction(false);
                    }
                }
            }
        }
    }

}
