import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Polica {
    public LinkedList<Knjiga> knjige;
    public int id;
    private final Random rand = new Random();
    public Polica() {
        id = rand.nextInt(501);
        generisiKnjige();
    }

    @Override
    public String toString() {
        return String.format("id=%d");
    }

    private void generisiKnjige() {
        knjige = new LinkedList<Knjiga>();
        for(int i = 0; i < rand.nextInt(51) + 1; i++)
            knjige.add(new Knjiga(id));
    }
}
