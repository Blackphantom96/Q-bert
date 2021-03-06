package logicalT;

public class GoodCube extends Land {
	/**
	 * Constructor GoodCube
	 * 
	 * @param color
	 *            un arreglo de colores
	 */
	public GoodCube(String[] color) {
		colors = color;
		actualColor = colors[0];
		visited = false;
	}

	public String toString() {
		return actualColor;
	}

	public void visited(Player a) {
		if (!visited) {
			player=a;
			actualColor = colors[3];
		}
		visited = true;
	}

	public boolean isVisited() {
		return visited;
	}
	public Player getPlayer(){
		return player;
	}
}
