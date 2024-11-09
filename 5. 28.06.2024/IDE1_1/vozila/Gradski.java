package vozila;

import putnici.PoolPutnikaThread;

import java.util.LinkedList;

public class Gradski extends Autobus {
    public Baterija baterija;
    protected String fajl = "sa_baterijom.txt";
    public Gradski(PoolPutnikaThread pool, int id) {
        super(pool, id);
        baterija = new Baterija();
    }
    @Override
    public String ulazIspis() {
        return String.format("U gradski autobus [%d] je uslo putnika: ", baterija.getNivoBaterije());
    }
    @Override
    public String izlazIspis() {
        return String.format("Iz gradskog autobusa [%d] je izaslo putnika: ", baterija.getNivoBaterije());
    }
    @Override
    protected LinkedList<String> ispis() {
        LinkedList<String> ispis = new LinkedList<>();
        ispis.add(fajl);
        ispis.add(String.format("###Gradski autobus [%d]:", baterija.getNivoBaterije()));
        putnici.forEach(putnik -> ispis.add(putnik.toString()));
        return ispis;
    }
}
