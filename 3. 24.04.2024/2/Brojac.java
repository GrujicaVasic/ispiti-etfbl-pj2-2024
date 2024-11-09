import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Brojac {
    public long a = 0, e = 0, i = 0, o = 0, u = 0;
    public Brojac(long a, long e, long i, long o, long u) {
        this.a = a;
        this.e = e;
        this.i = i;
        this.o = o;
        this.u = u;
    }

    public Brojac(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for(String line : lines) {
                a += brojKaraktera(line, "a");
                e += brojKaraktera(line, "e");
                i += brojKaraktera(line, "i");
                o += brojKaraktera(line, "o");
                u += brojKaraktera(line, "u");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public String toString() {
        return String.format("a=%d, e=%d, i=%d, o=%d, u=%d", a, e, i, o, u);
    }
    private long brojKaraktera(String line, String slovo) {
        return Arrays.stream(line.split(""))
                .filter(karakter -> karakter.contentEquals(slovo))
                .count();
    }
}
