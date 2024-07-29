
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        ReentrantLockOrnegi rlo = new ReentrantLockOrnegi();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                rlo.thread1Fonksiyonu();
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                rlo.thread2Fonksiyonu();
            }
        });

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        rlo.sonuc();
    }

}
