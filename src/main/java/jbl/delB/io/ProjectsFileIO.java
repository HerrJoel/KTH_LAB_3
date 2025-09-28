package jbl.delB.io;

import jbl.delB.model.Project;

import java.io.*;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, List<Project> data) throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);

            // Skriv hela listan till filen
            oos.writeObject(data);

        } finally {
            if (oos != null) {
                oos.close(); // viktigt: stäng alltid strömmen
            }
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            FileInputStream fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);

            // Läs tillbaka listan
            return (List<Project>) ois.readObject();

        } finally {
            if (ois != null) {
                ois.close(); // viktigt: stäng alltid strömmen
            }
        }
    }

    // Privat konstruktor för att förhindra instansiering
    private ProjectsFileIO() {}
}