import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExpressions {
    public static void main (String[] args) {
        void1();
        consumerInterface();
    }

    // STATIC
    //https://stackoverflow.com/questions/413898/what-does-the-static-keyword-do-in-a-class

    private static ArrayList<String> listOfPeople;

    public static void void1 () {
        listOfPeople = new ArrayList<>();
        listOfPeople.add("Paul");
        listOfPeople.add("Jake");
        listOfPeople.add("Sam");
        listOfPeople.forEach(p -> System.out.println(p));
    }

    // USE JAVA'S Consumer INTERFACE TO STORE LAMBDA EXPRESSION IN A VARIABLE
    // RULES:
    // 1. the interface has only one method
    // 2. lambda expression should have the same no. of params and the same return type as that method.
    // Consumer interface one built in provided by Java util package
    public static void consumerInterface() {

        // lambda expression applied to object of Consumer type is used to define its accept function.
        // accept function applies the given operation to its argument.
        // Consumers are useful when its not needed to return any value
        // void accept (T t) t is the input argument of type T

        Consumer<String> shout = str -> System.out.println(str + "!");

        shout.accept("Hello");

    }

    public static void consumerInterfaceAndThen () {

        // andThen returns a composed Consumer wherein the parameterised Consumer will be executed
        // after the first one. If evaluation of either function throws an error,
        // it is relayed to the caller of the composed operation

        // Consumer to multiply 2 to every integer of a list
        Consumer<List<Integer> > modify = list ->
        {
            for (int i = 0; i < list.size(); i++)
                list.set(i, 2 * list.get(i));
        };

        // Consumer to display a list of integers
        Consumer<List<Integer> >
                dispList = list -> list.stream().forEach(a -> System.out.print(a + " "));

        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(1);
        list.add(3);

        // using addThen()
        modify.andThen(dispList).accept(list);
        ;

    }

    interface StringFunction {
        String run (String str) ;
    }

    public static void lambda2 () {
        // method that takes a lambda expression as a parameter
        // This is just like the Consumer interface, but here it can return a value (String), whereas accept in Consumer was a void
        StringFunction exclaim = str -> str + "!";
        StringFunction question = str -> str + "?";
        printStr("Hello", exclaim);
        printStr("Hello", question);

    }

    public static void printStr(String input, StringFunction function ) {
        System.out.println(function.run(input));
    }

}