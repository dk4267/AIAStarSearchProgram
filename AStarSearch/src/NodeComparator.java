import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node arg0, Node arg1) {
		if (arg0.getTotalDistance() < arg1.getTotalDistance()) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
