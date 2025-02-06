import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        var names = new ArrayList<String>(); // var
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        names.add("Eve");

        // Enhanced for loop (more idiomatic, even though not a Java 21 specific change)
        for (var name : names) { // var makes sense here
            System.out.println("Hello, " + name + "!");
        }

        var day = today();
        System.out.println("Today is " + day);


        // Enhanced switch (Java 14+, preferred style)
        var result = switch (day) { // var
            case "MONDAY" -> "Have a good start of the workweek!";
            case "WEDNESDAY" -> "Hump day, working towards the weekend.";
            case "FRIDAY" -> "FriYay!";
            case "SATURDAY" -> "Enjoy your weekend!";
            default -> "It's a normal day, " + day + "."; // Always have a default branch or exhaustive cases
        };

        System.out.println(result);

        Person person = new Person("John Doe", "100 Linda Ln.");

        System.out.println(person);

        Person secondPerson = new Person("John Doe", "100 Linda Ln.");
        Person thirdPerson = new Person("Jane Doe", "100 Linda Ln.");

        if(person.equals(secondPerson)) {
            System.out.println(person.getName() + " and " + secondPerson.getName() + " are the same person.");
        }

        if(!person.equals(thirdPerson)) {
            System.out.println(person.getName() + " and " + thirdPerson.getName() + " are not the same person.");
        }

    }

    private static String today() {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().toString(); // Returns "MONDAY", "TUESDAY", etc.
    }

}
