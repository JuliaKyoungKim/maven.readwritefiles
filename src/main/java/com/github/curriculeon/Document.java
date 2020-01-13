package com.github.curriculeon;

import java.io.*;
import java.util.List;

/**
 * @author leon on 16/11/2018.
 */
public class Document implements DocumentInterface {

    private final FileWriter fileWriter;
    private final File file;
    private final FileReader fileReader;

    public Document(String fileName) throws IOException {
        this(new File(fileName));
    }

    public Document(File file) throws IOException {
        this.file = file;
        this.fileWriter = new FileWriter(file);
        this.fileReader = new FileReader(file);
    }

    @Override
    public void write(String contentToBeWritten)  {
        try {
            fileWriter.write(contentToBeWritten);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten)  {

    }



    @Override
    public String read(Integer lineNumber) {
        BufferedReader br = new BufferedReader(fileReader);
        String line="";
        try {
            for (int i=0;i<lineNumber;i++) {
                br.readLine();
            }
            line= br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public String read() {
        StringBuilder sb = new StringBuilder();
        String readString = "";
        try {
            int readChar = fileReader.read();
            while (readChar != -1) {
                readString = readString + Character.toString((char)readChar);
                readChar = fileReader.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readString;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) {
        String readData = this.read();
        String replacedall = readData.replaceAll(stringToReplace, replacementString);

        this.write(replacedall);
    }

    @Override
    public void overWrite(String content) {
    }

    public List<String> toList() {
        return null;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        String tostring = new String(file.getPath());
        return tostring+"{"+this.read()+"}";
    }
}
