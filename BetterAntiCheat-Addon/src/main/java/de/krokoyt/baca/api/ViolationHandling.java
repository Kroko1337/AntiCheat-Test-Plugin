package de.krokoyt.baca.api;

import de.liquiddev.betteranticheat.main.BACAPI;
import de.liquiddev.betteranticheat.main.Flag;
import de.liquiddev.betteranticheat.main.Violation;

public class ViolationHandling implements BACAPI {
    @Override
    public void onFlag(Flag flag) {
        System.out.println("§c" + flag.getPlayer().getName() + " flagged for " + flag.getCheck().getName() + "(" + flag.getReason() + ")");
    }

    @Override
    public void onViolation(Violation violation) {
        System.out.println("§c" + violation.getPlayer().getName() + " was detected for " + violation.getCheck().getName() + "(" + violation.getReason() + ")");
    }
}
