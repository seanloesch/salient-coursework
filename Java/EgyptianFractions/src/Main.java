package CS145;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        File f1 = new File("fractions.txt");
        File f2 = new File("egyptianfractions.txt");
        try {
            processFractions(f1);
            processEgyptianFractions(f2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void processFractions(File f) throws FileNotFoundException {
        Scanner fileReader = new Scanner(f);
        int numOfFracs = 0;
        String num = null;
        String den = null;
        try {
            numOfFracs = Integer.parseInt(fileReader.next());
        } catch (NumberFormatException e) {
            System.out.println("This file contains bad data.");
        }

        BigFraction[] fracArray = new BigFraction[numOfFracs + 4];
        for (int i = 0; i < numOfFracs; ++i) {
            try {
                num = fileReader.next();
                den = fileReader.next();
            } catch (NumberFormatException e) {
                System.out.println("This file contains bad data.");
            }
            fracArray[i] = (new BigFraction(new BigInteger(num), new BigInteger(den)));
        }
        fracArray[fracArray.length - 4] = fracArray[0].add(fracArray[1]);
        fracArray[fracArray.length - 3] = fracArray[2].subtract(fracArray[0]);
        fracArray[fracArray.length - 2] = fracArray[0].multiply(fracArray[3]);
        fracArray[fracArray.length - 1] = fracArray[0].divide(fracArray[4]);
        for (int i = 1; i < fracArray.length - 3; ++i) {
            System.out.println(fracArray[i - 1]);
        }

        System.out.println();

        for (int i = 5; i < fracArray.length + 1; ++i) {
            System.out.println(fracArray[i - 1]);
        }

        System.out.println();
    }

    public static void processEgyptianFractions(File f) throws FileNotFoundException {
        Scanner fileReader = new Scanner(f);
        int numOfFracs = 0;
        String num = null;
        String den = null;
        try {
            numOfFracs = Integer.parseInt(fileReader.next());
        } catch (NumberFormatException e) {
            System.out.println("This file contains bad data.");
        }

        EgyptianFraction[] egFracArray = new EgyptianFraction[numOfFracs + 2];
        for (int i = 0; i < numOfFracs; ++i) {
            try {
                num = fileReader.next();
                den = fileReader.next();
            } catch (NumberFormatException e) {
                System.out.println("This file contains bad data.");
            }
            egFracArray[i] = new EgyptianFraction(new BigFraction(new BigInteger(num), new BigInteger(den)));
        }

        egFracArray[egFracArray.length - 2] = egFracArray[0].add(egFracArray[1]);
        egFracArray[egFracArray.length - 1] = egFracArray[2].add(egFracArray[3]);

        fileReader = new Scanner(f);
        num = null;
        den = null;

        numOfFracs = Integer.parseInt(fileReader.next());

        for (int i = 0; i < numOfFracs; ++i) {
            String line = "";
            try {
                num = fileReader.next();
                den = fileReader.next();
            } catch (NumberFormatException e) {
                System.out.println("This file contains bad data.");
            }
            line += num + "/" + den + " = " + egFracArray[i];
            System.out.println(line);
        }
        System.out.println();

        System.out.print("(" + egFracArray[0] + ") + (" + egFracArray[1] + ") = ");
        System.out.println(egFracArray[0].add(egFracArray[1]));

        System.out.print("(" + egFracArray[2] + ") + (" + egFracArray[3] + ") = ");
        System.out.println(egFracArray[2].add(egFracArray[3]));
    }
}
