package com.mycompany.sampleproject;

/**
 *
 * @author Carter Rows
 */
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class LocalEntry implements Entry {

    private Path path;

    public LocalEntry(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public String getName() {
        return path.getFileName().toString();
    }

    @Override
    public long getLength() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public boolean isDirectory() {
        return Files.isDirectory(path);
    }

    public String getPath() {
        return path.toString();
    }

    public static List<Entry> GenerateListOfEntry(String rootPath) {
        ArrayList<String> subNodePaths = new ArrayList<>();
        File root = new File(rootPath);
        File[] subNodes = root.listFiles();
        if (subNodes != null) {
            for (File subNode : subNodes) {
                subNodePaths.add(subNode.getAbsolutePath());
            }
        }
        List<Entry> list = new ArrayList<Entry>();
        for(String p : subNodePaths) {
            Entry a = new LocalEntry(p);
            list.add(a);
        }
        return list;
    }
}