package yape.yape.java.euler324;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The state of the topmost layer of the tower.
 * What really interests us here is which of the 9 blocks is occupied. This can be compactly
 * represented using 9 bits (or just an integer...). We can then figure out all the ways to
 * fill this layer, giving us the possible states for the NEXT layer.
 */
public class TowerState {
	
	public static final int WIDTH = 3, HEIGHT = 3, NUM_STATES = 1 << (WIDTH * HEIGHT);
	
	/**
	 * 0 1 2
	 * 3 4 5
	 * 6 7 8
	 */
	private int squares;
	
	TowerState(int s) {
		squares = s;
	}
	
	TowerState(TowerState copy) {
		squares = copy.squares;
	}
	
	/**
	 * Get an integer that uniquely representats this state.
	 * @return
	 */
	public int toInt() {
		return squares;
	}
	
	/**
	 * @return true if (x,y) is occupied; false otherwise.
	 */
	public boolean getSquare(int x, int y) {
		return (shift(x,y) & squares) > 0;
	}
	
	void setSquare(int x, int y) {
		squares = squares | shift(x,y);
	}
	
	void setSquare(int x, int y, boolean value) {
		if(value)
			setSquare(x,y);
		else if(getSquare(x, y))
			squares = squares ^ shift(x,y);
	}
	
	private int shift(int x, int y) {
		if(x >= WIDTH || y >= HEIGHT)
			throw new UnsupportedOperationException("Tried to access square " + x + ", " + y);
		int shift = y*WIDTH + x;
		return (1 << shift);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TowerState)
			return ((TowerState)o).squares == squares;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return squares;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<HEIGHT; y++) {
			if(y != 0) {
				if(y == HEIGHT/2) {
					sb.append(" ");
					sb.append(Integer.toHexString(squares));
					sb.append(" ");
					sb.append(squares);
				}
				sb.append("\n");
			}
			
			for(int x=0; x<WIDTH; x++) {
				sb.append(getSquare(x, y) ? "X" : "O");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Try all possible ways to fill up this level. Return what the next level will look like.
	 */
	public Map<TowerState, Integer> getNextStates() {
		return getNextStates(new TowerState(0));
	}
	
	private Map<TowerState, Integer> getNextStates(TowerState next) {
		Map<TowerState, Integer> result = new HashMap<TowerState, Integer>();
		
		for(int x=0; x<WIDTH; x++) {
			for(int y=0; y<HEIGHT; y++) {
				if(getSquare(x, y))
					continue;
				
				TowerState me;
				
				// We found an empty square. There are 3 possibilities:
				
				// ----- put a block here, laying flat horizontally
				if(x + 1 < WIDTH && !getSquare(x+1, y)) {
					me = new TowerState(this);
					me.setSquare(x, y);
					me.setSquare(x+1, y);
					merge(result, me.getNextStates(next));
				}
				
				// ----- put a block here, laying flat vertically
				if(y + 1 < HEIGHT && !getSquare(x, y+1)) {
					me = new TowerState(this);
					me.setSquare(x, y);
					me.setSquare(x, y+1);
					merge(result, me.getNextStates(next));
				}
				
				// ----- put a block here, standing up into the next level
				me = new TowerState(this);
				me.setSquare(x, y);
				TowerState nextCopy = new TowerState(next);
				nextCopy.setSquare(x, y);
				merge(result, me.getNextStates(nextCopy));
				
				// We've now recursively tried everything. Return the result.
				return result;
			}
		}
		
		// Getting here means that all squares were full. We thus just return the next state.
		result.put(next, 1);
		return result;
	}
	
	/**
	 * Merge the second map into the first one.
	 * @param result
	 * @param map
	 */
	private void merge(Map<TowerState, Integer> result, Map<TowerState, Integer> map) {
		for(TowerState ts : map.keySet()) {
			if(result.containsKey(ts)) {
				result.put(ts, result.get(ts) + map.get(ts));
			} else {
				result.put(ts, map.get(ts));
			}
		}
	}

	/**
	 * Find all the equivalent states. Equivalence is considered under rotation and reflection.
	 * @return
	 */
	Set<TowerState> calculateEquivalents() {
		Set<TowerState> result = new HashSet<TowerState>();
		
		TowerState ts = this;
		
		// This, plus 3 rotations.
		result.add(ts);
		ts = ts.rotate(); // 90°
		result.add(ts);
		ts = ts.rotate(); // 180°
		result.add(ts);
		ts = ts.rotate(); // 270°
		result.add(ts);
		
		// Flip, then rotate the flipped version.
		ts = flipVertical();
		result.add(ts);
		ts = ts.rotate(); // 90°
		result.add(ts);
		ts = ts.rotate(); // 180°
		result.add(ts);
		ts = ts.rotate(); // 270°
		result.add(ts);
		
		return result;
	}
	
	/**
	 * Rotate (this) 90° once.
	 * @return
	 */
	TowerState rotate() {
		TowerState result = new TowerState(0);
		
		/*
		 * 6 3 0
		 * 7 4 1
		 * 8 5 2
		 * --> x becomes y; y becomes (width - x)
		 */
		for(int y=0; y<HEIGHT; y++) {
			for(int x=0; x<WIDTH; x++) {
				if(getSquare(x, y))
					result.setSquare(WIDTH - y - 1, x);
			}
		}
		
		return result;
	}
	
	TowerState flipVertical() {
		TowerState result = new TowerState(0);
		
		for(int y=0; y<HEIGHT/2; y++) {
			for(int x=0; x<WIDTH; x++) {
				result.setSquare(x, y, getSquare(x, HEIGHT - y - 1));
				result.setSquare(x, HEIGHT - y - 1, getSquare(x, y));
			}
		}
		
		if((HEIGHT % 2) == 1) {
			for(int x=0; x<WIDTH; x++) {
				result.setSquare(x, HEIGHT/2, getSquare(x, HEIGHT/2));
			}
		}
		
		return result;
	}
}
