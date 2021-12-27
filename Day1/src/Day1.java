import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public List<Integer>extractData(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Integer> firstDataExtract = new ArrayList<>();

        while (scanner.hasNextLine()) {
            firstDataExtract.add(Integer.parseInt(scanner.nextLine()));
        }

        return firstDataExtract;
    }

    public Integer findIncreasingSonar(List<Integer> data) {
        int count = 0, previous = 0;
        for (int i = 1; i < data.size(); i++) {
            int integer = data.get(i);
            if (integer > previous) {
                count++;
            }

            previous = integer;
        }

        return count;
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        List<Integer> data = day1.extractData(new File("Day1.txt"));
        int numIncreasing = day1.findIncreasingSonar(data);
        System.out.println(numIncreasing);
    }
}


