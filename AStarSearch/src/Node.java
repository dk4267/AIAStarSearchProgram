import java.util.ArrayList;

public class Node {

	private ArrayList<Integer> value = new ArrayList<Integer>();
	private int distFromRoot;
	private int distFromGoal;
	private ArrayList<Integer> parentValue = new ArrayList<Integer>();
	
	public Node(ArrayList<Integer> value) {
		this.value = value;
	}
		
	public void setRootDistance(int distance) {
		this.distFromRoot = distance;
	}
	
	public int getRootDistance() {
		return this.distFromRoot;
	}
	
	public void setGoalDistance(int distance) {
		this.distFromGoal = distance;
	}
	
	public int getGoalDistance() {
		return this.distFromGoal;
	}
	
	public int getTotalDistance() {
		return(this.distFromGoal + this.distFromRoot);
	}
	
	
	public ArrayList<Integer> getVal() {
		return this.value;
	}
	
	public void setParentArray(ArrayList<Integer> parentArr) {
		this.parentValue = parentArr;
	}
	
	public ArrayList<Integer> getParentArray() {
		return this.parentValue;
	}
	
}
	

