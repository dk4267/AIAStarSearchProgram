import java.util.ArrayList;
import java.util.Collections;

public class Move {
	

	public static ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<ArrayList<Integer>>();
	
	
	//checks if node is solvable by parity rules
	public static boolean isSolvable(Node currNode) { 
		ArrayList<Integer> currBoard = currNode.getVal();
		int inversions = 0;
		
		for (int i = 0; i < currBoard.size(); i++) {
			for (int j = i + 1; j < currBoard.size(); j++) {
				if ((currBoard.get(i) != 0) && (currBoard.get(j) != 0)) {
					if (currBoard.get(i) > currBoard.get(j)) {
						inversions++;
					}
				}
			}
		}
		if (inversions % 2 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//calculates Manhattan block distance between each position in 2 nodes
	public static int calcDistance(Node node1, Node node2) { 
		ArrayList<Integer> arr1 = node1.getVal();
		ArrayList<Integer> arr2 = node2.getVal();
		int distance = 0;
		
		for (int i = 0; i < arr1.size(); i++) {
			int j = arr2.indexOf(arr1.get(i));
			int row1 = (i/3) + 1;
			int row2 = (j/3) + 1;
			int col1 = findColumn(i);
			int col2 = findColumn(j);
			distance += (Math.abs(row1 - row2) + Math.abs(col1 - col2));
		}
		return distance;
	}
	
	//returns what column an index is in. Prevents me from having to bother with 2D arrays
	private static int findColumn(int index) { 
		if (((index + 1) % 3) == 0) {
			return 3;
		}
		else if (((index + 1) % 3) == 1) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	//finds possible next moves and adds them to priority queue
	public static void move(Node currNode) {
		int i = currNode.getVal().indexOf(0);
		int col = findColumn(i);
		int row = (i / 3) + 1;
		possibleMoves(row, col, currNode.getVal());
		
		for (int j = 0; j < possibleMoves.size(); j++) {
			Node tempNode = new Node(possibleMoves.get(j));
			if(isSolvable(tempNode) && (!Search.wasVisited(tempNode))) {
				int rootDistance = calcDistance(tempNode, Search.rootNode);
				int goalDistance = calcDistance(tempNode, Search.goalNode);


				tempNode.setRootDistance(rootDistance);
				tempNode.setGoalDistance(goalDistance);
				tempNode.setParentArray(currNode.getVal());
				Search.addToQueue(tempNode);
				
			}
		}
		possibleMoves.clear();
	}
	
	public static ArrayList<Integer> deepCopy(ArrayList<Integer> origArray) {
		ArrayList<Integer> copy = new ArrayList<Integer>(origArray.size());
		for (Integer i : origArray) {
			copy.add(new Integer(i));
		}
		return copy;
	}
	
	//returns all possible moves from one node
	private static void possibleMoves(int row, int col, ArrayList<Integer> origArray) {
		ArrayList<Integer> arr1 = deepCopy(origArray);
		if (row == 1) {
			Collections.swap(arr1, col - row, col - row + 3);
		}
		else if (row == 3) {
			Collections.swap(arr1,  col + row + 2, col + row - 1);
		}
		else {
			Collections.swap(arr1,  col + row,  col + row - 3);
			
			ArrayList<Integer> arr2 = deepCopy(origArray);
			Collections.swap(arr2,  col + row,  col + row + 3);
			possibleMoves.add(arr2);
		}
		possibleMoves.add(arr1);
		
		ArrayList<Integer> arr3 = deepCopy(origArray);
		if (col == 1) {
			Collections.swap(arr3,  3 * (row - 1), 3 * (row - 1) + 1);
		}
		else if (col == 3) {
			Collections.swap(arr3,  3 * (row - 1) + 2,  3 * (row - 1) + 1);
		}
		else {
			Collections.swap(arr3,  3 * (row - 1) + 1,  3 * (row - 1) + 2);
			
			ArrayList<Integer> arr4 = deepCopy(origArray);
			Collections.swap(arr4,  3 * (row - 1) + 1,  3 * (row - 1));
			possibleMoves.add(arr4);
		}
		possibleMoves.add(arr3);
	}
	
}
