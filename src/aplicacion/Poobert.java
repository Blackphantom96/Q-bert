package aplicacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Poobert {
	private String[] color;
	private int size;
	private Player[] players;
	private int level, yLevel, xLevel;
	private String[] playersNames;
	private char selection;
	private char charLand[][];

	public Poobert(String[] strings, char selection) {
		level = 2;
		players = new Player[2];
		playersNames = strings;
		this.selection = selection;
		try {
			leerNivel(level);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * genera el tablero dada el tamaño en x
	 * 
	 * @param b
	 *            el tamaño en x del tablero
	 */
	private void tablero(int b) {
		land = new ArrayList<>();
		for (int i = 0; i < b; i++) {
			if (i % 2 == 0)
				land.add(new Cube[b]);
			else
				land.add(new Cube[(b)]);
		}
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < land.get(i).length; j++) {
				if (i % 2 == 0) {
					land.get(i)[j] = (new Cube(size, null));
					land.get(i)[j].Premove(j * size * 4, i * size * 3);
				} else {
					land.get(i)[j] = (new Cube(size, null));
					land.get(i)[j].Premove((j * size * 4) + size * 2, i * size * 3);
				}
			}
		}
	}

	/**
	 * al cubo en la posicion x,y lo cambia a habitable
	 * 
	 * @param x
	 *            pos x
	 * @param y
	 *            pos y
	 */
	public void add(int x, int y) {
		land.get(x)[y].setColors(color, false);
	}

	/**
	 * cambia el color en la posicioin que le jugador esta
	 * 
	 * @param play
	 *            el jugador
	 */
	

	/**
	 * setea el jugador dado su posicion inicial, y nombre
	 * 
	 * @param i
	 *            pos en x
	 * @param q
	 *            pos rn y
	 * @param name
	 *            nombre
	 */
	public void setPlayer1(int i, int q, String name) {
		int[] temo = land.get(i)[q].getCords();
		players[0] = new Player(temo[0], temo[1], size, q, i, name, 'n');
	}

	/**
	 * setea el jugador dado su posicion inicial, y nombre
	 * 
	 * @param i
	 *            pos en x
	 * @param q
	 *            pos rn y
	 * @param name
	 *            nombre
	 */
	public void setPlayer2(int i, int q, String name) {
		int[] temo = land.get(i)[q].getCords();
		players[1] = new Player(temo[0], temo[1], size, q, i, name, 'b');
	}

	/**
	 * lee un mundo dado el nivel en numero
	 * 
	 * @param le
	 *            el nivel
	 * @throws IOException
	 */
	private void leerNivel(int le) throws IOException {
		int zoom = 20 * 3;
		size = zoom / 3;
		BufferedReader in;
		in = new BufferedReader(new FileReader("resources/Levels/" + le + ".level"));
		yLevel = Integer.parseInt(in.readLine());
		xLevel = Integer.parseInt(in.readLine());
		charLand = new char[yLevel][xLevel];
		color = new String[] { in.readLine(), "lightGray", "darkGray", in.readLine() };
		tablero(xLevel);
		for (int i = 0; i < yLevel; i++) {
			int q = 0;
			for (char j : in.readLine().trim().toCharArray()) {
				charLand[i][q] = j;
				if (j != 'x')
					add(i, q);
				if (j == 'Q')
					setPlayer1(i, q, playersNames[0]);
				if (j == 'P' && selection != '1')
					setPlayer2(i, q, playersNames[1]);
				if (j != '*')
					q++;
			}
		}
		in.close();
	}

	/**
	 * mueve el jugador 1
	 * 
	 * @param a
	 *            en x
	 * @param b
	 *            en y
	 */
	public void move1(String a, String b) {
		charLand[players[0].cy][players[0].cx]='c';
		if (players[0].Premove(a, b))
			changeColor(players[0]);
		charLand[players[0].cy][players[0].cx]='Q';
	}

	/**
	 * mueve el jugador 2
	 * 
	 * @param a
	 *            en x
	 * @param b
	 *            en y
	 */
	public void move2(String a, String b) {
		charLand[players[1].cy][players[1].cx]='c';
		if (players[1].Premove(a, b))
			changeColor(players[1]);
		charLand[players[1].cy][players[1].cx]='P';
	}

	/**
	 * @return devuelve los jugadores
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * @return devuelve el mapa
	 */
	public ArrayList<Cube[]> getLand() {
		return land;
	}

	/**
	 * @return devuelve el tamaño que ocupa en x
	 */
	public int getSizeX() {
		return size * (yLevel * 2 + 10);
	}

	/**
	 * @return devuelve el tamaño que ocupa en y
	 */
	public int getSizeY() {
		return size * (xLevel * 4 + 2);
	}

	/* debug */
	public void printMat() {
		for (char[] a : charLand) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println('\n');
	}
}
