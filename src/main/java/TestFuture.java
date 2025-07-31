import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFuture {
    public static void main(String[] args) {
       ExecutorService pool = Executors.newCachedThreadPool();
       pool.submit(()->print("3: "));
       CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
           print("1: ");
       },pool);
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()->{
            print("2: ");
        },pool);
        pool.shutdown();
    }

    static void function(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    static void print(String m){
        for (int i = 0; i < 20; i++) {
            System.out.println(m + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
