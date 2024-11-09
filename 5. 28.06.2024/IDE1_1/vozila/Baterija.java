package vozila;

import java.util.Random;

public class Baterija {
    public int getNivoBaterije() {
        return new Random().nextInt(51) + 50;
    }
}
