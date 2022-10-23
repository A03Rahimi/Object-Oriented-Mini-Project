import java.util.Scanner;

// a class to get user input from console in different types with exception handling using loops
public final class ConsoleScanner{

	private final static Scanner scanner = new Scanner(System.in);

	// get user response in type string given a message
	public static String string(String message){
		String response;
		while (true){
			try{
				System.out.print(message);
				response = scanner.nextLine();
				return response;
			} catch (java.util.InputMismatchException e) {
				System.out.println("The value you entered is not in type string. Please try again entering a string value.");
				scanner.next(); // Move to next to prevent exception
			}
		}
	}

	// get user response in type integer given a message
	public static int integer(String message){
		int response;
		while (true){
			try{
				System.out.print(message);
				response = scanner.nextInt();
				scanner.nextLine();  // Consume newline left-over
				return response;
			} catch (java.util.InputMismatchException e){
				System.out.println("The value you entered is not in type integer. Please try again entering an integer value.");
				scanner.next(); // Move to next to prevent exception
			}
		}
	}

	// get user response in type boolean given a message
	public static boolean booleanVal(String message){
		while (true){
			try{
				System.out.print(message);
				boolean response = scanner.nextBoolean();
				scanner.nextLine();  // Consume newline left-over
				return response;
			} catch (java.util.InputMismatchException e) {
				System.out.println("The value you entered is not in type boolean. Please try again entering a boolean value.");
				scanner.next(); // Move to next to prevent exception
			}
		}
	}

	// get user response in type double given a message
	public static double doubleVal(String message) {
		while (true) {
			try {
				System.out.print(message);
				double response = scanner.nextDouble();
				scanner.nextLine();  // Consume newline left-over
				return response;
			} catch (java.util.InputMismatchException e) {
				System.out.println("The value you entered is not in type double. Please try again entering a double value.");
				scanner.next(); // Move to next to prevent exception
			}
		}
	}

}