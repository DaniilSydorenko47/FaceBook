import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

public class FutureSend {
    CompletableFuture<String> future;

    PrintWriter pw;

    String message;

    public FutureSend(PrintWriter pw, String message) {
        this.pw = pw;
        this.message=message;
    }
    public void send(){
        CompletableFuture.runAsync(() -> {
            pw.println(message);
            pw.flush();
        }).thenRun(() -> {
            System.out.println("Сообщение отправлено: " + message);

        }).exceptionally(ex -> {
            System.err.println("Ошибка при отправке: " + ex.getMessage());
            return null;
        });
    }
}
