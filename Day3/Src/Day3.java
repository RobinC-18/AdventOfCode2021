import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public List<String> dataExtract(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> data = new ArrayList<>();
        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            data.add(scanner.nextLine());
        }
        return data;
    }

    public int Part1(List<String> data) {
        String gamma = "";
        String epsilon = "";
        for (int i = 0; i < data.get(0).length(); i++) {
            int zeros = 0;
            int ones = 0;
            for (String line : data) {
                char currChar = line.charAt(i);
                if (currChar == '0')
                    zeros++;
                else
                    ones++;
            }
            if (ones > zeros) {
                gamma = gamma + '1';
                epsilon = epsilon + '0';
            } else {
                gamma = gamma + '0';
                epsilon = epsilon + '1';
            }
        }
        int gammaDecimal = Integer.parseInt(gamma, 2);
        int epsilonDecimal = Integer.parseInt(epsilon, 2);
        return gammaDecimal * epsilonDecimal;
    }

    public int Part2(List<String> oxygen, List<String> c02, int currIndex) {

        if (oxygen.size() == 1 && c02.size() == 1) {
            int oxygenVal = Integer.parseInt(oxygen.get(0), 2);
            int c02Val = Integer.parseInt(c02.get(0), 2);
            System.out.println(oxygen + ": " + oxygenVal + "    " + c02 + ": " + c02Val);
            int answer = oxygenVal * c02Val;
            return answer;
        }

        if (currIndex == oxygen.get(0).length()) {
            System.out.println("currIndex = " + currIndex);
            return 0;
        }

        int ones = 0;
        int zeros = 0;
        char mostCommonOxy, leastCommonC02;
        for (String line : oxygen) {
            char currChar = line.charAt(currIndex);
            if (currChar == '0')
                zeros++;
            else
                ones++;
        }
        if (ones >= zeros) {
            mostCommonOxy = '1';
        } else {
            mostCommonOxy = '0';
        }

        ones = 0;
        zeros = 0;
        for (String line : c02) {
            char currChar = line.charAt(currIndex);
            if (currChar == '0')
                zeros++;
            else
                ones++;
        }
        if (ones < zeros) {
            leastCommonC02 = '1';
        } else {
            leastCommonC02 = '0';
        }

        System.out.println("Most common oxygen: " + mostCommonOxy + " Least common c02: " + leastCommonC02);

        List<String> newOxygen = new ArrayList<>();

        for (String line : oxygen) {
            if (line.charAt(currIndex) == mostCommonOxy)
                newOxygen.add(line);
        }

        if (c02.size() != 1) {
            List<String> newc02 = new ArrayList<>();
            for (String line : c02) {
                if (line.charAt(currIndex) == leastCommonC02)
                    newc02.add(line);
            }
            c02 = newc02;
        }

        System.out.println("Len of oxygen: " + newOxygen.size() + " Len of C02: " + c02.size());

        currIndex++;
        return this.Part2(newOxygen, c02, currIndex);
    }

    public static void main(String[]args){
        File myFile = new File("Day3/src/Day3_data.txt");
        Day3 day3 = new Day3();
        List<String> data = day3.dataExtract(myFile);
//        int answer = day3.Part1(data);
        int answer = day3.Part2(data, data, 0);
        System.out.println("Answer is: " + answer);

    }

}

