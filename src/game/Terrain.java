package ar.edu.itba.sia.tp1;

public interface Terrain {
	
	public void setIsDestination(boolean val);
	public boolean isDestination();
	public void setHasBox(boolean val);
	public boolean hasBox();
	public boolean canMoveThrough();
}
