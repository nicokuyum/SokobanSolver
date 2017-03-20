package ar.edu.itba.sia.tp1;

public class SolidTerrain implements Terrain{

	@Override
	public boolean isDestination() {
		return false;
	}

	@Override
	public void setHasBox(boolean val) {
	}

	@Override
	public boolean hasBox() {
		return false;
	}

	@Override
	public boolean canMoveThrough() {
		return false;
	}

	@Override
	public void setIsDestination(boolean val) {
	}

}
