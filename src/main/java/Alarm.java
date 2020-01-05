import java.util.ArrayList;
import java.util.stream.IntStream;


public class Alarm {
    private static void checkOperationAllowed(int operation) {
        int [] allowedValues = {1, 2, 99};
        boolean isAllowed = IntStream.of(allowedValues).anyMatch(n -> n == operation);
        if(!isAllowed) {
            throw new RuntimeException("Operation not allowed");
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> numbers = PuzzelInput.readvalues("src/main/resources/Alarm");

        for (int i = 0; (i < numbers.size() - 4) ; i+=4) {
            int position = i;
            System.out.println("Position: " + position);
            int operation = numbers.get(i);
            int firstPosition = numbers.get(position+1);
            int secondPosition = numbers.get(position+2);
            int resultPosiotion = numbers.get(position+3);

            checkOperationAllowed(operation);

            switch(operation) {
                case 1:
                    numbers.set(resultPosiotion,numbers.get(firstPosition) + numbers.get(secondPosition));
                    break;
                case 2:
                    numbers.set(resultPosiotion,numbers.get(firstPosition) * numbers.get(secondPosition));
                    break;
                case 99:
                    i=numbers.size();
                    break;
            }
            System.out.println(numbers.toString());
        }
        System.out.println(numbers.get(0));

    }
}
