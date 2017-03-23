package game;

public interface Terrain {
	
	public void setIsDestination(boolean val);
	public boolean isDestination();
	public void setHasBox(boolean val);
	public boolean hasBox();
	public boolean canMoveThrough();
}
