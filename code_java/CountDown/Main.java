public class Main {
    final static int N = 10;
    public static void main(String[] args) {
        Thread counter = new Thread(new CountDown(N));
        counter.start();
    }
}