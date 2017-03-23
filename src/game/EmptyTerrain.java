package game;

public class EmptyTerrain implements Terrain{
	
	private boolean hasBox;
	private boolean isDestination;
	private int code;
	
	public EmptyTerrain(boolean startsWithBox, boolean isDestination, final int code){
		hasBox = startsWithBox;
		this.isDestination = isDestination;
		this.code=code;
	}
	
	// Default is no box and no destination
	public EmptyTerrain(int code){
		this(false,false,code);
	}
	
	public void setHasBox(boolean val){
		hasBox = val;
	}

	@Override
	public boolean hasBox() {
		return hasBox;
	}

	@Override
	public boolean canMoveThrough() {
		return true;
	}

	@Override
	public boolean isDestination() {
		return isDestination;
	}

	@Override
	public void setIsDestination(boolean val) {
		this.isDestination = val;
	}
	
	public int hashCode(){
		return code;
	}

	
}
