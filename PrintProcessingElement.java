package com.mycompany.sampleproject;

/**
 *
 * @author Carter Rows
 */
import java.util.List;

class PrintProcessingElement implements ProcessingElement {
    static public List<Entry> process(List<Entry> input) {
        input.forEach(entry -> {
            if(entry instanceof LocalEntry){
                System.out.println("Name: " + entry.getName());
                System.out.println("Length: " + entry.getLength());
                System.out.println("Path" + entry.toString());
            } else if(entry instanceof RemoteEntry) {
                RemoteEntry entry1 = (RemoteEntry) entry;
                System.out.println("Name: " + entry1.getName());
                System.out.println("Length: " + entry1.getLength());
                System.out.println("entryID: " + entry1.getEntryID());
            }
        });

        return input;
    }
}