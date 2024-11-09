package clanovi;

import projekat.Zadatak;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public interface GeneratorZadataka {
    default PriorityBlockingQueue<Zadatak> generisiZadatake(int brojSprinta) {
        PriorityBlockingQueue<Zadatak> zadaci = new PriorityBlockingQueue<>(5, (z1, z2) -> z2.prioritet - z1.prioritet);
        Random rand = new Random();
        for(int i = 0; i < 5; i++) {
            String id = brojSprinta + "_" + rand.nextInt(100);    //zadatak 1x...x - 1. sprint
            Zadatak z = new Zadatak(id, rand.nextInt(5) + 1);
            z.brojIteracije = brojSprinta;
            zadaci.add(z);
        }
        return zadaci;
    }
}
