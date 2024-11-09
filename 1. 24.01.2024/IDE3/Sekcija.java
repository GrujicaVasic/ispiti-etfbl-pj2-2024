import java.util.LinkedList;
import java.util.Random;

public class Sekcija {
    public LinkedList<Polica> police;
    public int id;
    private final Random rand = new Random();
    public Sekcija() {
        id = rand.nextInt(50);
        generisiPolice();
    }
    private void generisiPolice() {
        police = new LinkedList<>();
        for(int i = 0; i < rand.nextInt(10) + 1; i++)
            police.add(new Polica());
    }
}
