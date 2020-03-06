import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solve {

	public static void main(String[] args) {
		
		//read in file, output results of search
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get("program_1_data.txt"));
			FileWriter out = new FileWriter("outfile.txt");
			int lineCount = 1;
			ArrayList<Integer> currentArray = new ArrayList<>();
			for (String line : lines) {
				if (lineCount != 4) {
					String lineNoWhite = line.replaceAll("\\s+", "");
					for (char c : lineNoWhite.toCharArray()) {
						currentArray.add(Character.getNumericValue(c));
					}	
					lineCount++;
				}
				else if (lineCount == 4) {
					String result = Search.aStarSearch(currentArray);
					out.write(result);
					currentArray.clear();
					lineCount = 1;
					
				}
			}
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}


