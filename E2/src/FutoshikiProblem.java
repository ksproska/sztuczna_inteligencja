import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class FutoshikiProblem extends GridProblem<Object, FutoshikiEnum, Integer> {
    public final int gridX, gridY;
    public static final String neutral = "-", lessThan = "<", moreThan = ">";

    public FutoshikiProblem(FutoshikiEnum chosenProblem) {
        super(chosenProblem,
                FutoshikiConsts.getSize(chosenProblem)[0] * 2 - 1,
                FutoshikiConsts.getSize(chosenProblem)[1] * 2 - 1
        );
        gridX = FutoshikiConsts.getSize(chosenProblem)[0];
        gridY = FutoshikiConsts.getSize(chosenProblem)[1];
        readProblem(ProjectConsts.folderPath);
        setIndexesToFill();
    }

    private void setIndexesToFill() {
        for (int i = 0; i < problem.size(); i++) {
            if(problem.get(i) != null && problem.get(i).equals("x")) {
                indexesToFill.add(i);
            }
        }
    }

    private void readProblem(String folderPath) {
        String filename = String.format("%s\\%s_%sx%s", folderPath, "futoshiki", gridX, gridY);
        try {
            Scanner scanner = new Scanner(new File(filename));
            int rowCounter = 0;
            while (scanner.hasNextLine()) {
                var nextLine = scanner.nextLine().split("");
                for (int i = 0; i < nextLine.length; i++) {
                    var nextChar = nextLine[i];
                    Object nextValue = nextChar;
                    try {
                        nextValue = Integer.valueOf(nextChar);
                    }
                    catch (NumberFormatException ignored) {}
                    if(nextValue == "x") {
                        nextValue = new Integer(null);
                    }
                    problem.add(nextValue);
                    if(rowCounter % 2 == 1 && i < gridX - 1) {
                        problem.add("-");
                    }
                }
                rowCounter += 1;
            }
            scanner.close();
        } catch (IOException e) {
            String errorMessage = String.format("Problem reading file \"%s\"", filename);
            System.out.println(errorMessage);
        }
    }

    public String toDisplay(ArrayList<Object> grid) {
        var allToDisplay = "";
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i) == null
                    || grid.get(i).equals("-")
            ) {
                allToDisplay += " ";
            }
            else {
                allToDisplay += grid.get(i);
            }
            allToDisplay += "   ";
            if((i + 1) % x == 0) {
                allToDisplay += "\n";
            }
        }
        allToDisplay = allToDisplay.substring(0, allToDisplay.length() - 3);
        return allToDisplay;
    }

    @Override
    public FutoshikiPartialSolution getInitialSolution() {
        return new FutoshikiPartialSolution(this);
    }
}

class FutoshikiTests {
    static FutoshikiProblem futoshikiProblem;

    @Test
    void test1() {
        futoshikiProblem = new FutoshikiProblem(FutoshikiEnum.F4x4);
        System.out.println(futoshikiProblem);
        var solution = futoshikiProblem.getInitialSolution();
        solution.setNewValue(4, 4);
        solution.setNewValue(2, 20);
        solution.setNewValue(2, 32);
        solution.setNewValue(1, 46);
        System.out.println(solution);
        System.out.println(solution.partialSolution);
    }
}