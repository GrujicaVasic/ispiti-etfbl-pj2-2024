package vozila;

import putnici.PoolPutnikaThread;

public abstract class Autobus extends Vozilo {
    public Autobus(PoolPutnikaThread pool, int id) {
        super(pool, id);
    }

    public abstract String ulazIspis();
    public abstract String izlazIspis();
}
