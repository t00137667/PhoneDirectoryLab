package com.company;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayBasedPD implements PhoneDirectory {
    // phone directory and related items
    private final int MAXENTRIES = 200; // maximum number of entries in phone directory
    private PhoneDirectoryEntry[] phoneDirectory = new PhoneDirectoryEntry[MAXENTRIES];
    private int numberOfEntries = 0; // number of entries
    private int currentEntry = -1; //index of entry currently displayed
    private int entriesOnLoad;

    public void loadData(String filename) {
        // TODO Auto-generated method stub
        try {
            File inputFile = new File(filename);
            Scanner in = new Scanner(inputFile);
            String name;
            String number;
            int index = 0;

            while (in.hasNext()){
                name = in.next();
                number = in.next();
                phoneDirectory[index] = new PhoneDirectoryEntry(name, number);
                index++;
            }

            numberOfEntries = index;
            entriesOnLoad = numberOfEntries;
            //now call Arrays.sort() to sort phoneDirectory
            Arrays.sort(phoneDirectory,0,numberOfEntries);

            in.close();

        } catch (IOException exc) {
            System.out.println("File does not exist");
        }
    }

    public void saveData(String filename) {
        // TODO Auto-generated method stub
        if (numberOfEntries == entriesOnLoad) {
            try {
                PrintWriter output = new PrintWriter(filename);

                for (int i = entriesOnLoad; i < numberOfEntries; i++) {
                    output.println(phoneDirectory[i].toString());
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!");
            }
        }
    }

    public void addEntry(String name, String number) {
        // TODO Auto-generated method stub

        if (numberOfEntries != MAXENTRIES){
            phoneDirectory[numberOfEntries] = new PhoneDirectoryEntry(name, number);
        }
        numberOfEntries++;
        Arrays.sort(phoneDirectory,0,numberOfEntries);
    }

    public PhoneDirectoryEntry getFirst() {
        // TODO Auto-generated method stub
        if (numberOfEntries != 0) {
            currentEntry = 0;
            return phoneDirectory[0];
        } else
            return null;
    }

    public PhoneDirectoryEntry getNext() {
        // TODO Auto-generated method stub
        if (currentEntry != numberOfEntries - 1) {
            currentEntry++;
            return phoneDirectory[currentEntry];
        } else
            return null;

    }

    public PhoneDirectoryEntry getPrevious() {
        // TODO Auto-generated method stub
        if (currentEntry != 0) {
            currentEntry--;
            return phoneDirectory[currentEntry];
        } else
            return null;
    }

    public String search(String name) {
        // TODO Auto-generated method stub
        PhoneDirectoryEntry temp = new PhoneDirectoryEntry(name,"");
        int result = Arrays.binarySearch(phoneDirectory,0,numberOfEntries,temp);
        if (result < 0)
        {
            return "That name was not found";
        } else{
            return phoneDirectory[result].getNumber();
        }
    }
}
