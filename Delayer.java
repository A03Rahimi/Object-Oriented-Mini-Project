// a class to make delays (used to make GUI a bit more interesting)
public class Delayer{
    // Pause the program for x milliseconds
    public static void delay(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return;
    }
}