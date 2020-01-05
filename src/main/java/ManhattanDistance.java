import java.util.*;

public class ManhattanDistance {
    static ArrayList<Coordinates> usedCoordinates = new ArrayList<>();
    static ArrayList<Coordinates> junctions = new ArrayList<>();
    static LinkedList <LinkedList<String>> directions = PuzzelInput.read("src/main/resources/ManhattanDistances");

    private static void proceedWithCoordinates(Coordinates coordinatsToCheck) {
        if(usedCoordinates.contains(coordinatsToCheck)) {
            Coordinates coordinates = usedCoordinates.stream().filter(n -> n.equals(coordinatsToCheck)).findFirst().get();
            if(coordinates.getWire() != coordinatsToCheck.getWire()) {
                junctions.add(coordinatsToCheck.addDelay(coordinates));
            }
        }
        usedCoordinates.add(coordinatsToCheck);
    }

    public static void main(String[] args) {

        for (int wire = 0; wire < directions.size() ; wire++) {
            int row = 0;
            int collumn = 0;
            int delay = 0;

            for (int i = 0; i < directions.get(wire).size(); i++) {
                String coordinates = directions.get(wire).get(i);
                String firstLetter = coordinates.substring(0, 1);
                int value = Integer.valueOf(coordinates.substring(1));


                switch (firstLetter) {
                    case "R":
                        for (int k = 0; k <= value; k++) {
                            Coordinates coordinatsToCheck = new Coordinates(row, collumn + k, wire, delay+k);
                            proceedWithCoordinates(coordinatsToCheck);
                        }
                        collumn += value;
                        break;
                    case "U":
                        for (int k = 0; k <= value; k++) {
                            Coordinates coordinatsToCheck = new Coordinates(row + k, collumn, wire,delay+k);
                            proceedWithCoordinates(coordinatsToCheck);
                        }
                        row += value;
                        break;
                    case "L":
                        for (int k = 0; k <= value; k++) {
                            Coordinates coordinatsToCheck = new Coordinates(row, collumn - k, wire,delay+k);
                            proceedWithCoordinates(coordinatsToCheck);
                        }
                        collumn -= value;
                        break;
                    case "D":
                        for (int k = 0; k <= value; k++) {
                            Coordinates coordinatsToCheck = new Coordinates(row - k, collumn, wire,delay+k);
                            proceedWithCoordinates(coordinatsToCheck);
                        }
                        row -= value;
                        break;
                }
                delay += value;
            }
        }
        System.out.println("Min: " + junctions.stream().filter(n->(n.getRow()!=0 && n.getCollumn()!=0)).map(n -> n.getAbsSum()).filter(n->n>0).min(Integer::compareTo));
        System.out.println("Min delay: " + junctions.stream().filter(n->(n.getRow()!=0 && n.getCollumn()!=0)).map(n -> n.getDelay()).filter(n->n>0).min(Integer::compareTo));
//        junctions.stream().forEach(n->System.out.println(n.toString()));
//        System.out.println("used coordinates: ");
//        usedCoordinates.stream().forEach(n->System.out.println(n.toString()));
    }
}

class Coordinates {
    private int row;
    private int collumn;
    private int wire;
    private int delay;

    public int getWire() {
        return wire;
    }

    public int getRow() {
        return row;
    }

    public int getCollumn() {
        return collumn;
    }

    public int getDelay() {
        return delay;
    }

    public Coordinates(int row, int collumn, int wire, int delay) {
        this.row = row;
        this.collumn = collumn;
        this.wire = wire;
        this.delay = delay;
    }

    public int getAbsSum() {
        return Math.abs(row) + Math.abs(collumn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return row == that.row &&
                collumn == that.collumn;
    }

    public Coordinates addDelay(Coordinates coordinates) {
        this.delay += coordinates.getDelay();
        return this;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "row=" + row +
                ", collumn=" + collumn +
                ", wire=" + wire +
                ", delay=" + delay +
                '}';
    }
}