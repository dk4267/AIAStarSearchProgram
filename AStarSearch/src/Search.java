import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Search {

	public static Node goalNode;
	public static Node rootNode;
	private static PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>(new NodeComparator());
	private static HashMap<ArrayList<Integer>, ArrayList<Integer>> visited = new HashMap<>();

	//Main A* search function
	public static String aStarSearch(ArrayList<Integer> root) {

		Search.rootNode = new Node(root);
		Search.rootNode.setParentArray(null);
		Search.goalNode = new Node(createGoalArr());
		Search.rootNode.setRootDistance(0);
		Search.rootNode.setGoalDistance(Move.calcDistance(rootNode, goalNode));
		addToQueue(Search.rootNode);
		while(!nodeQueue.isEmpty()) {
			Node currentNode = nodeQueue.poll();
			addToVisited(currentNode.getVal(), currentNode.getParentArray());

			if (Move.calcDistance(currentNode,  goalNode) == 0) {
				String res = printPath(currentNode);
				visited.clear();
				nodeQueue.clear();
				return res;
			}
			else {
				Move.move(currentNode);
			}
		}
		try {
			FileWriter outFile = new FileWriter("outfile.txt");
			outFile.write("No solution found");
			outFile.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		visited.clear();
		nodeQueue.clear();
		return ("No solution found \n");
	}
	
	public static void addToQueue(Node currNode) {
		nodeQueue.add(currNode);
	}
	
	
	public static void addToVisited(ArrayList<Integer> currentNode, ArrayList<Integer> parentNode) {
		visited.put(currentNode, parentNode);
	}
	
	public static boolean wasVisited(Node currNode) {
		return visited.containsKey(currNode.getVal());
	}
	
	public static ArrayList<Integer> createGoalArr() {
		ArrayList<Integer> goalArr = new ArrayList<Integer>(9);
		for (int i = 1; i < 9; i++) {
			goalArr.add(i);
		}
		goalArr.add(0);
		return goalArr;
	}
	

	//returns string with path to goal
	private static String printPath(Node currentNode) {
		ArrayList<Integer> outputList = new ArrayList<Integer>();
		ArrayList<Integer> currArr = currentNode.getVal();
		int val = 0;
		while(visited.get(currArr) != null){
			ArrayList<Integer> parentArr = visited.get(currArr);
			for (int i = 0; i < currArr.size(); i++) {
				if (!currArr.get(i).equals(parentArr.get(i))) {	
					if (currArr.get(i) != 0) {
						val = currArr.get(i);
						outputList.add(val);
						
					}
					else if (parentArr.get(i) != 0) {
						val = parentArr.get(i);
						outputList.add(val);
						
					}
					else {
						System.out.println("Error");
						val = -1;
					}
					break;
					
				}
				
			}
			currArr = parentArr;
		}

		String result = "";
		for (int i = outputList.size() - 1; i >= 0; i--) {
			result += (outputList.get(i) + " --> ");
		}
		result += "Goal \n";
		return result;
	}
	
}
