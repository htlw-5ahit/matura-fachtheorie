package client.model.exporter;

import common.Lesson;
import common.networking.Command;
import common.networking.CommandType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

public class NetworkExporter implements Exporter {

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String address;
    private int port;

    public NetworkExporter(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void init() throws IOException {
        socket = new Socket(address, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void insert(Lesson lesson) throws Exception {

    }

    @Override
    public void insert(Collection<Lesson> lessons) throws Exception {
        oos.writeObject(new Command(CommandType.ADD_COLLECTION, lessons));
    }

    @Override
    public Collection<Lesson> getAll() throws Exception {
        Command cmd = (Command) ois.readObject();
        if (cmd.getType() == CommandType.SUCCESS) {
            return (Collection<Lesson>) cmd.getValue();
        }
        throw new Exception();
    }

    @Override
    public void close() throws Exception {
        // send close command to client before closing socket
        oos.writeObject(new Command(CommandType.CLOSE));
        ois.readObject();

        if (oos != null) oos.close();
        if (ois != null) ois.close();
        if (socket != null) socket.close();
    }
}
