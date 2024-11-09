import java.nio.file.*;
import java.util.*;
import java.io.*;


public class Main {
    private static String fajlKaoString;

    public static void main(String[] args) {
        ucitajLinije("recenzije.txt");
        LinkedList<String> zaPrevod = new LinkedList<>();
        for (String s : args) {
            try {
                zaPrevod.add(citanjeRijeci(Integer.valueOf(s)));
                for (String s1 : zaPrevod) {
                    char[] znakovi = s1.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < znakovi.length; i++)
                        sb.append(Konverzija.konvertuj(String.valueOf(znakovi[i])));
                    System.out.println(sb);
                }
            } catch (NepostojecaRijecException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void ucitajLinije(String putanjaFajla) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(putanjaFajla));
        } catch(IOException e) {

        }
        StringBuilder sb = new StringBuilder();
        for(String s : lines)
            sb.append(s + "\\");
        fajlKaoString = sb.toString();
    }

    private static String citanjeRijeci(int pozicija) throws NepostojecaRijecException {
        int pocetakRijeci = pozicija;
        StringBuilder sb = new StringBuilder();

        if(!String.valueOf(fajlKaoString.charAt(pocetakRijeci)).matches("\\w"))
            throw new NepostojecaRijecException(pozicija);
        while(jeKarakter(pocetakRijeci))
            pocetakRijeci--;
        int trentnaPozicija = pocetakRijeci + 1;
        while(jeKarakter(trentnaPozicija))
            sb.append(fajlKaoString.charAt(trentnaPozicija++));
        return sb.toString();
    }
    private static boolean jeKarakter(int pozicija) {
        return String.valueOf(fajlKaoString.charAt(pozicija)).matches("\\w");
    }
}