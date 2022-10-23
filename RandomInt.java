import java.util.Random;

// Return a random int between 0 (inclusive) and bound (exclusive)
public class RandomInt{
    public static int get(int bound){
        Random r = new Random(); // built-in type
        return r.nextInt(bound); // built-in method
    }
}

