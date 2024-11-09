package clanovi;

public class ProductOwner extends Clan implements GeneratorZadataka {
    public String opis;
    public ProductOwner(String ime, String prezime, int godineRada, String opis) {
        super(ime, prezime, godineRada);
        this.opis = opis;
    }

    @Override
    public void run() {
        while (!sprint.jeKraj) {
            try {
                synchronized (this) {
                    wait();
                }
                if(!sprint.jeKraj) {
                    sprint.dodajZadatke(generisiZadatake(sprint.iteracija));
                    System.out.printf("**%s %s generisao zadatke**%n%n", ime, prezime);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
