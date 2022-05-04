package server;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (DatabaseHandler db = new DatabaseHandler("lessons.properties");
             ServerSocket serverSocket = new ServerSocket(6400)) {

            while (true) {
                executorService.execute(new ClientHandler(serverSocket.accept(), db));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
