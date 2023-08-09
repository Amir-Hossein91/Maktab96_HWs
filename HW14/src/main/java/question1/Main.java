package question1;

public class Main {
    public static void main(String[] args) {
        Rational zero = new Rational();
        Rational first = new Rational();
        Rational second = new Rational(-3, 8);
        Rational third = new Rational();
        first.setSimplifiedValue(-18,4);
        third.setSimplifiedValue(3,-4);
//        System.out.println("zero= " + zero + " ---> (default value)");
//        System.out.println("first= " + first + " ---> (created by setSimplifiedValue())");
//        System.out.println("second= " + second + " ---> (created by setValue())");
//        System.out.println("third= " + third + " ---> (created by setSimplifiedValue())");
//        System.out.println("float value of first= " + first.toFloatingPoint());
//        System.out.println("float value of second= " + second.toFloatingPoint());
//        System.out.println("float value of third= " + third.toFloatingPoint());
//        System.out.println("first + second = " + first.add(second));
//        System.out.println("first - second = " + first.sub(second));
//        System.out.println("first * second = " + first.mul(second));
//        System.out.println("first / second = " + first.div(second));
        System.out.println("complex form of first = " + first.toComplexNumber());
//        System.out.println("complex form of second = " + second.toComplexNumber());
//        System.out.println("complex form of third = " + third.toComplexNumber());
//        System.out.println(first.div(second).add(third));
//        System.out.println(first.div(second).add(third));

    }
}
