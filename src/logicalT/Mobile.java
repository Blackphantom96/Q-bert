package logicalT;

public abstract class Mobile {
	protected int cx,cy;
	public abstract String toString();
	
	public int[] Premove(String a, String b) {
		int cxn,cyn;
		if (cy % 2 != 0)
			cxn = (a.equals("L")) ? cx : cx + 1;
		else
			cxn = (a.equals("R")) ? cx : cx - 1;
		cyn = (b.equals("U")) ? cy - 1 : cy + 1;
	return new int[] {cxn,cyn};
	}
	public void move(String a, String b) {
		if (cy % 2 != 0)
			cx = (a.equals("L")) ? cx : cx + 1;
		else
			cx = (a.equals("R")) ? cx : cx - 1;
		cy = (b.equals("U")) ? cy - 1 : cy + 1;
	}
	public int getCx(){
		return cx;
	}
	public int getCy(){
		return cy;
	}
}
