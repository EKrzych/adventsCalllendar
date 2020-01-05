import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class PuzzelInput {
    static ArrayList<Double> readModules(String filePath){
        ArrayList <Double> modules = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                modules.add(scanner.nextDouble());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return modules;
    }

    static ArrayList<Integer> readvalues(String filePath){
        ArrayList <Integer> modules = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                modules.add(scanner.nextInt());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return modules;
    }

    static LinkedList <LinkedList<String>> read(String filePath) {
        LinkedList <LinkedList<String>> modules = new LinkedList<>();
        String path;
        try(Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter("\\n");
            while (scanner.hasNext()) {
                path = scanner.next();
                modules.add(new LinkedList<>(Arrays.asList(path.split(","))));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return modules;
    }
}
