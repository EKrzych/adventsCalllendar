public class FuelCounter {

    static double countFuel(double mass) {
        return Math.floor(mass/3)-2d;
    }

    static double countFuelForFuel(Double mass) {
        double fuelMass = 0;
        double temp = countFuel(mass);
        while(temp > 0) {
            fuelMass += temp;
            temp = countFuel(temp);
        }
        return fuelMass;
    }

    public static void main(String[] args) {
        double sum = PuzzelInput.readModules("src/main/resources/starModules").stream().map(FuelCounter::countFuel).reduce(0d, Double ::sum);
        System.out.println(sum);

        double accurateSum = PuzzelInput.readModules("src/main/resources/starModules").stream().map(FuelCounter::countFuelForFuel).reduce(0d, Double ::sum);
        System.out.println(accurateSum);

    }
}
