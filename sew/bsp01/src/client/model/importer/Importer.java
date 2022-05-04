package client.model.importer;

import common.Lesson;

import java.io.IOException;
import java.util.HashSet;

public interface Importer {

    public HashSet<Lesson> start() throws IOException;

}
