import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StickyNotePassword {
    static int begin = 134792;
    static int end = 675810;

    static ArrayList<Integer> passwords = new ArrayList<>();
    static ArrayList<Integer> passwordsWithMoreRules = new ArrayList<>();

    private static boolean checkIfContainsPairs(Integer p) {
        String toCheck = String.valueOf(p);
        return Arrays.asList("00","11","22","33","44","55","66","77","88","99").stream().anyMatch(n -> toCheck.contains(n));
    }

    private static boolean checkIfdigitIncrease(Integer p) {
        String [] numbers = String.valueOf(p).split("");
        Arrays.sort(numbers);
        return Integer.valueOf(Arrays.stream(numbers).collect(Collectors.joining())).equals(p);
    }

    private static void simplerRules() {
        for (int i = begin; i <= end; i++) {
            if(checkIfContainsPairs(i) && checkIfdigitIncrease(i)) {
                passwords.add(i);
            }
        }
        System.out.println("Passwords count: " + passwords.size());
    }



    public static void main(String[] args) {
       simplerRules();
       for (int number : passwords) {
            if(checkIfValid(number)){
                passwordsWithMoreRules.add(number);
            }
       }
        System.out.println("Hard Passwords count: " + passwordsWithMoreRules.size());
    }

    private static boolean checkIfValid(int number) {
        String toCheck = String.valueOf(number);
        ArrayList<String> values = new ArrayList<>(Arrays.asList("11","22","33","44","55","66","77","88","99"));
        for (String v : values) {
            Pattern pattern = Pattern.compile(v);
            Matcher matcher = pattern.matcher(toCheck);

            int count = 0;
            int pos = 0;
            while (matcher.find(pos)) {
                count++;
                pos = matcher.start() + 1;
            }

            if (count==1) {
                return  true;
            }
        }
        return false;
    }
}