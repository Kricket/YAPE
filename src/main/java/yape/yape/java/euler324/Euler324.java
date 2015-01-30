package yape.yape.java.euler324;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import yape.math.LongMatrix;

public class Euler324 {
	
	/**
	 * The modulus by which we reduce the answer. The nice thing about this is that it's small enough
	 * that we can use a matrix with plain old Longs, instead of BigIntegers.
	 */
	public static final long Q = 100000007;
	
	/**
	 * The index (in the matrix of transitions) of the base, "empty" tower state.
	 */
	private static int baseECIndex;
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		LongMatrix result = goTo(BigInteger.TEN.pow(10000));
		System.out.println(String.format("Took %.3f seconds", (System.currentTimeMillis() - start) * 0.001));
		System.out.println("Result is " + result.get(baseECIndex, baseECIndex));
		// Answer is: 96972774
	}

	/**
	 * Get a matrix representing the transitions from one equivalence class of TowerStates
	 * to another.
	 */
	public static LongMatrix calcTransitionsMatrix() {
		Map<Integer, Set<TowerState>> equiv_classes = getEquivalenceClasses();
		
		// First, set up a few things: we will need a coherent (but arbitrary) ordering
		// of the equivalence classes.
		int[] state_to_eqindex = new int[TowerState.NUM_STATES];
		int[] EQUIV_CLASSES = new int[equiv_classes.size()];
		int i = 0;
		for(Integer ec : equiv_classes.keySet()) {
			EQUIV_CLASSES[i] = ec;
			for(TowerState ts : equiv_classes.get(ec)) {
				state_to_eqindex[ts.toInt()] = i;
			}
			
			i++;
		}
		
		baseECIndex = state_to_eqindex[0];
		
		// Now, build the matrix.
		LongMatrix m = new LongMatrix(equiv_classes.size(), equiv_classes.size());
		
		for(int ecIndex=0; ecIndex<EQUIV_CLASSES.length; ecIndex++) {
			TowerState eClass = new TowerState(EQUIV_CLASSES[ecIndex]);
			Map<TowerState, Integer> nextStates = eClass.getNextStates();
			for(TowerState next : nextStates.keySet()) {
				int nextECIndex = state_to_eqindex[next.toInt()];
				m.set(ecIndex, nextECIndex, m.get(ecIndex, nextECIndex) + nextStates.get(next));
			}
		}
		
		return m;
	}
	
	/**
	 * Get a mapping of (one member of an equivalence class) => (all members of the equivalence class)
	 * for all TowerStates.
	 * @return
	 */
	private static Map<Integer, Set<TowerState>> getEquivalenceClasses() {
		int[] state_to_class = new int[TowerState.NUM_STATES];
		Map<Integer, Set<TowerState>> equiv_classes = new HashMap<Integer, Set<TowerState>>();
		
		for(int i=0; i<TowerState.NUM_STATES; i++) {
			if(state_to_class[i] < 1) {
				Set<TowerState> equivalents = new TowerState(i).calculateEquivalents();
				
				// Pick a representative for the equivalence class
				int eClass = equivalents.iterator().next().toInt();
				equiv_classes.put(eClass, equivalents);
				
				// Populate all the equivalents
				for(TowerState ts : equivalents) {
					state_to_class[ts.toInt()] = eClass;
				}
			}
		}
		
		return equiv_classes;
	}
	
	/**
	 * Get a matrix representing all the ways to get from an empty tower, to the different states of a
	 * tower of the given height.
	 * @param height The height of the tower.
	 * @return
	 */
	private static LongMatrix goTo(BigInteger height) {
		// The idea here: we calculate matrix, matrix^2, matrix^4, ...
		// matrix^n represents the ways to get to a tower of height n.
		// To get to the given height, we can look at its binary representation. For every 1 in the binary
		// form of height, we need to multiply the result by the current matrix.
		// For example, ten = 1010b. Thus, matrix^10 = matrix^8 * matrix^2.
		LongMatrix matrix = calcTransitionsMatrix();
		System.out.println("Got transitions matrix...");
		LongMatrix result = LongMatrix.makeIdentity(matrix.getRowDimension());
		for(int i=0; i<=height.bitLength(); i++) {
			// prod = m^i, indicating how to get to a tower of height i
			if(height.testBit(i)) {
				result = result.times(matrix);
				reduce(result);
			}
			matrix = matrix.times(matrix);
			reduce(matrix);
		}
		
		return result;
	}
	
	/**
	 * Reduce the entries of the given matrix by the modulus Q.
	 * @param m
	 */
	private static void reduce(LongMatrix m) {
		long[][] matrix = m.getArray();
		for(int i=0; i<matrix.length; i++) { for(int j=0; j<matrix[i].length; j++)
			if(matrix[i][j] > Q)
				matrix[i][j] %= Q;
		}
	}
}
