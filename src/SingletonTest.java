public class SingletonTest {
    public static void main(String[] args) {
        Logger log1 = Logger.getInstance();
        Logger log2 = Logger.getInstance();

        System.out.println("Are both instances the same? " + (log1 == log2));
    }
}