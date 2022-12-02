import sun.misc.Signal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CountDown implements Runnable {
    int i;
    Boolean flag = true;
    private static final int MAX_T = 2;
    static ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

    public CountDown(int i){
        this.i = i;
    }
    @Override
    public void run() {
        Signal.handle(new Signal("INT"),  // SIGINT
                signal -> stop());
        while(true) {
            if (!flag) return;
            if (i>0)  { tick(); --i; }
            if (i==0) { beep(); return;}
        }
    }
    public void stop() {
        System.out.println("call stop");
        flag = false;
    }
    public void tick(){
        System.out.println("tick");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ignored){}
    }

    public void beep(){
        System.out.println("beep");
    }
}
