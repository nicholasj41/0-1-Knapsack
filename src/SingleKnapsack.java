import java.util.ArrayList;
import java.util.Scanner;

public class SingleKnapsack {

	private int[] values = {2, 3, 1, 4, 5};
	private int[] weights = {1, 2, 3, 3, 5};
	private int size = values.length;
	private int maxWeight = 7;
	private int table[][];
	
	private SingleKnapsack() {
		initialize();
		fillTable();
		outputOptimal();
	}
	
	private void initialize() {
		table = new int[size+1][maxWeight+1];
		
		for(int i = 0; i <= maxWeight; i++){
			table[0][i] = 0;
		}

		System.out.print("Values: \t");
		for(int x = 0; x < values.length; x++) {
			System.out.print(values[x] + " ");
		}
		System.out.println();
		System.out.print("Weights: \t");
		for(int x = 0; x < weights.length; x++) {
			System.out.print(weights[x] + " ");
		}
		System.out.println();
		System.out.println("Max Weight: \t" + maxWeight);
	}
	
	private void fillTable() {
		for(int i = 1; i <= size; i++) {
			
			int value = values[i-1];
			int weight = weights[i-1];
			
			for(int j = 1; j <= maxWeight; j++) {

				if((weight <= j) && (values[i-1] + table[i-1][j-weight] > table[i-1][j])) {
					table[i][j] = table[i-1][j-weight] + value;
				} else {
					table[i][j] = table[i-1][j];
				}
				
			}
		}

		for(int i = 0; i < size+1; i++) {
			for(int j = 0; j < maxWeight+1; j++) {
				System.out.print(table[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private void outputOptimal() {
		int i = size;
		int j = maxWeight;
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		while(i > 0 && j > 0) {
			if(table[i-1][j] < table[i][j]){
				result.add(values[i-1]);
				j -= weights[i-1];
			}
			i--;
		}

		System.out.println("List of values that make up result: " + result);
		System.out.println("Complexity: O(size*maxWeight)");
	}
	
	public static void main(String[] arguments) {
		new SingleKnapsack();
	}
}
