
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReentrantLockOrnegi {
    
    private int say;
    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    
    
    public void artir(){
        
        for(int i=0; i<10000; i++){
            say++;
        }
      
    }
    
    public void thread1Fonksiyonu(){
          
        lock.lock();
        //burada sınıfın anahtarını kilitliyoruz.
        
        System.out.println("Thread 1 çalışıyor \n thread 1 uyandırılmayı bekliyor.");
        
        try {
            condition.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(ReentrantLockOrnegi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Thread 1 uyandığı yerden devam ediyor");
        
        artir();
        lock.unlock();
        /*try{
            artir();
        }
        
        finally{
            lock.unlock();
        }*/
        
        //burada sınıfın anahtarını salıyoruz.
        
        /*tabi böyle durumlarda artir fonksiyonu gibi fonskiyonlar exception 
        yollayabilir ve hata sonuçlanbilir ve unlock çalışmayabilir bunun için
        try ve finally ekliyoruz. böyle daha güvenli oluyor.*/
    }
    
    public void thread2Fonksiyonu(){
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReentrantLockOrnegi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lock.lock();
        //burada sınıfın anahtarını kilitliyoruz.
        System.out.println("Thread 2 çalışıyor");
        artir();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Devam etmesi için bir input giriniz");
        scanner.nextLine();
        
        condition.signal();
        System.out.println("Thread 2 iş bitti ve gidiyor...");
        
        lock.unlock();
        //burada sınıfın anahtarını salıyoruz.
    }
    
    
    public void sonuc(){
        System.out.println("Sayı değişkeninin değeri:"+this.say);
    }
}
