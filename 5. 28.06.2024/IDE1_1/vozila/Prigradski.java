package vozila;

import putnici.PoolPutnikaThread;

import java.util.LinkedList;

public class Prigradski extends Autobus {
    protected String fajl = "bez_baterije.txt";
    public Prigradski(PoolPutnikaThread pool, int id) {
        super(pool, id);
    }
    @Override
    public String ulazIspis() {
        return "U prigradski autobus je uslo putnika: ";
    }
    @Override
    public String izlazIspis() {
        return "Iz prigradskog autobusa je izaslo putnika: ";
    }
    @Override
    protected LinkedList<String> ispis() {
        LinkedList<String> ispis = new LinkedList<>();
        ispis.add(fajl);
        ispis.add("###Prigradski autobus:");
        putnici.forEach(putnik -> ispis.add(putnik.toString()));
        return ispis;
    }
}
