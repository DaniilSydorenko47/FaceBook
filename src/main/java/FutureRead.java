import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class FutureRead {
    CompletableFuture<String> future;
    BufferedReader br;

    public FutureRead(BufferedReader br) {
        this.br = br;
    }

    public void read(){

        future = CompletableFuture.supplyAsync(() -> {
            try {
                return br.readLine(); // ждёт строку от сервера
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        future.thenAccept(message -> {
            System.out.println("Ответ сервера: " + message);
        });
        future.exceptionally(ex -> {
            System.err.println("Ошибка при чтении: " + ex.getMessage());
            return null;
        });
    }
}
