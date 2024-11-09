import java.util.Random;

public class Main {
	public static void main(String[] args) {
		int unos = Integer.valueOf(args[0]);
		Letjelica[][] mapa = new Letjelica[3][unos];
		
		/*for(int i = 0; i < 3; i++) {
			for(int j = 0; j < unos; j++) {
				mapa[i][j] = new Letjelica();
			}
		}*/
		
		HashSet redovi = new HashSet(2);
		while(redovi.size() != 2) {
			int red = new Random().nextInt(3);
			if(redovi.add(red)) {
				Brod b = new Brod(i);
				Stanice s = new Stanice(i);
				Sonda so = new Sonda(i);
				mapa[red][0] = b;
				mapa[red][0] = s;
				mapa[red][unos] = so; 
			}
		}
	}
}