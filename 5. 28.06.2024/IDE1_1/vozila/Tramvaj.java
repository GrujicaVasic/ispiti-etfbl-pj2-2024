package vozila;

import putnici.PoolPutnikaThread;

public abstract class Tramvaj extends Vozilo {
    public Tramvaj(PoolPutnikaThread pool, int id) {
        super(pool, id);
    }
    public abstract String ulazIspis();
    public abstract String izlazIspis();
}
