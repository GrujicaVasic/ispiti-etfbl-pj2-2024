import java.io.File;
import java.util.HashMap;

public class Watcher extends Thread {
    public boolean jeKraj;
    File file;  //direktorijum koji nadgleda
    HashMap<File, Brojac> txtFajlovii = new HashMap<>();
    int brojFajlova = 0;
    public Watcher(File file) {
        this.file = file;
        jeKraj = false;
    }
    @Override
    public void run() {
        while(!jeKraj) {
            File[] fajlovi = file.listFiles();
            if(fajlovi.length != brojFajlova) {
                brojFajlova = fajlovi.length;
                for(File f : fajlovi) {
                    if(jeTxtFajl(f) && !txtFajlovii.containsKey(f)){
                        Brojac b = new Brojac(f);
                        txtFajlovii.put(f, b);
                        System.out.println("DODAN " + f.getAbsolutePath());
                        System.out.println("Novi fajl: " + b);
                        System.out.println("Ukupno u direktorijumu: " + ukupnoSamoglasnikaUFajlu() + "\n");
                    }
                }
            }
            try {
                sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean jeTxtFajl(File file) {
        return file.getName().endsWith(".txt");
    }
    private Brojac ukupnoSamoglasnikaUFajlu() {
        long a = 0, e = 0, i = 0, o = 0, u = 0;
        for(Brojac b : txtFajlovii.values()){
            a += b.a;
            e += b.e;
            i += b.i;
            o += b.o;
            u += b.u;
        }
        return new Brojac(a, e, i, o, u);
    }
}
