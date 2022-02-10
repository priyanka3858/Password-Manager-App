package com.passwordmanager.data.utils;
import java.io.*;

public class SerializationDemo {

    public static class PasswordReadWrite implements  Serializable{
        private final String username;
        private final String password;
        private final String website;

        public PasswordReadWrite(String Username, String Password, String Website) {
            username = Username;
            password = Password;
            website = Website;
        }
        public String toString() {
            return " username = " +  username +   " password =  " + password + " website = " + website;
        }
    }
    public void serialization() {
        try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("" +
                "../../../masterPasswordList.dat"))) {
            for (int i = 0; i < 3; i++) {
                outfile.writeObject(new PasswordReadWrite("username1", "website1", "password1"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream("" +
                "../../../masterPasswordList.dat"))) {
            for (int i = 0; i < 3; i++) {
                System.out.printf("%s%n", infile.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}






