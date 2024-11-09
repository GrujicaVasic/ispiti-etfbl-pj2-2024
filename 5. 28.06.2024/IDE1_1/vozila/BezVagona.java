package vozila;

import putnici.PoolPutnikaThread;

import java.util.LinkedList;

public class BezVagona extends Tramvaj {
    protected String fajl = "sa_baterijom.txt";
    Baterija baterija;
    public BezVagona(PoolPutnikaThread pool, int id) {
        super(pool, id);
        baterija = new Baterija();
    }

    @Override
    public String ulazIspis() {
        return String.format("U tramvaj bez vagona [%d] je uslo putnika: ", baterija.getNivoBaterije());
    }
    @Override
    public String izlazIspis() {
        return String.format("Iz tramvaja bez vagona [%d] je izaslo putnika: ", baterija.getNivoBaterije());
    }
    @Override
    protected LinkedList<String> ispis() {
        LinkedList<String> ispis = new LinkedList<>();
        ispis.add(fajl);
        ispis.add(String.format("###Tramvaj bez vagona [%d]:", baterija.getNivoBaterije()));
        putnici.forEach(putnik -> ispis.add(putnik.toString()));
        return ispis;
    }
}
