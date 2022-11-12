package utils;

import java.io.*;


/**
 * Data Utility for the storage and persistence of objects and other associated material.
 */
public class DataUtils {

    /**
     * Saves the object data for persistence, via serialization.
     * All classes involved need to implement serilaizable.
     * @param o the object to be saved/serialized
     * @param name the name of the object to be saved/serialized
     * @return the error code of the method
     */
    public static int saveData(Object o, String name){

        try{
            FileOutputStream fileOut = new FileOutputStream("Data/" + name, false);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(o);
            objOut.close();
            return 0;
        } catch (IOException iex) {
            iex.printStackTrace();
            return 1;
        }
    }

    /**
     * Loads the object data from saved state, via deserialization.
     * All classes involved need to implement serilaizable.
     * @param name the name of the object to be read
     * @return the object being loaded/deserialized
     */
    public static Object loadData(String name){
        if(!checkFile(name)){
            return null;
        }
        
        try{
            FileInputStream fileIn = new FileInputStream("Data/" + name);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Object o = objIn.readObject();
            objIn.close();
            fileIn.close();
            return o;
        } catch (IOException iex){
            iex.printStackTrace();
            return null;
        } catch (ClassNotFoundException cex){
            cex.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if the file already exists in the Data location
     * @param name the file name to be checked
     * @return  the boolean value of the exisitence of the file
     */
    public static boolean checkFile(String name){
        File directory = new File("Data/");
        if(!directory.exists()){
            directory.mkdir();
            return false;
        } else {
            File file = new File("Data/" + name);
            return file.exists();
        }

    }
}
