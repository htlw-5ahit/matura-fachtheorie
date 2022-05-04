package server;

import common.Lesson;
import common.networking.Command;
import common.networking.CommandType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DatabaseHandler db;

    public ClientHandler(Socket socket, DatabaseHandler db) {
        this.socket = socket;
        this.db = db;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            boolean running = true;

            System.out.println(Thread.currentThread().getId() + ": New Connection from " + socket.getInetAddress());

            // hold connection open till close command
            while (running) {

                // waiting for command from client
                Command cmd = (Command) ois.readObject();

                System.out.println(Thread.currentThread().getId() + ": Command received: " + cmd.getType());

                // return command to client
                Command retCmd;

                switch (cmd.getType()) {
                    case ADD_COLLECTION:
                       // try to insert lessons
                        try {
                            // insert lessons into database
                            db.insertLesson((Collection<Lesson>) cmd.getValue());

                            // get all lessons from database
                            Collection<Lesson> retLessons = db.getAllLessons();

                            // return success command and all lessons from database to client
                            retCmd = new Command(CommandType.SUCCESS, retLessons);
                        } catch (Exception e) {
                            // print stack trace for debugging
                            e.printStackTrace();

                            // send error command to client
                            retCmd = new Command(CommandType.ERROR, e.getMessage());
                        }
                        break;
                    case CLOSE:
                        // return close command as acknowledgment
                        retCmd = new Command(CommandType.CLOSE, null);

                        // stop waiting for new commands => finally from catch will close the connection
                        running = false;
                        break;
                    default:
                        throw new RuntimeException("Command not implemented.");
                }

                // write answer to client
                oos.writeObject(retCmd);
                oos.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!socket.isClosed())
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
