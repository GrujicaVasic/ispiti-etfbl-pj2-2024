import java.util.HashMap;

public class Konverzija {
    private static HashMap<String, String> mapa;
    static {
        mapa = new HashMap<String, String>(10);
        mapa.put("a", "а");
        mapa.put("b", "б");
        mapa.put("v", "в");
        mapa.put("g", "г");
        mapa.put("d", "д");
        mapa.put("đ", "ђ");
        mapa.put("e", "е");
        mapa.put("ž", "ж");
        mapa.put("z", "з");
        mapa.put("i", "и");
        mapa.put("j", "ј");
        mapa.put("k", "к");
        mapa.put("l", "л");
        mapa.put("m", "м");
        mapa.put("n", "н");
        mapa.put("o", "о");
        mapa.put("p", "п");
        mapa.put("r", "р");
        mapa.put("s", "с");
        mapa.put("t", "т");
        mapa.put("ć", "ћ");
        mapa.put("u", "у");
        mapa.put("f", "ф");
        mapa.put("h", "х");
        mapa.put("c", "ц");
        mapa.put("č", "ч");
        mapa.put("š", "ш");
    }

    public static String konvertuj(String lat) {
        return mapa.get(lat);
    }
}