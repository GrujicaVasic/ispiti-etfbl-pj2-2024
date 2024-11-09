import putnici.PoolPutnikaThread;
import vozila.*;

public class Main {
    public static void main(String[] args) {
        PoolPutnikaThread pool = new PoolPutnikaThread();
        Vozilo[] vozila = new Vozilo[20];
        for(int i = 0; i < 20; i++) {
            if(i % 4 == 0)
                vozila[i] = new Gradski(pool, i);
            else if(i % 4 == 1)
                vozila[i] = new Prigradski(pool, i);
            else if(i % 4 == 2)
                vozila[i] = new SaVagonima(pool, i);
            else
                vozila[i] = new BezVagona(pool, i);
        }
        pool.vozila = vozila;

        pool.start();
        for(Vozilo v : vozila)
            v.start();
    }
}
