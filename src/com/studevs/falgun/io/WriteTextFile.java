/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.falgun.io;

import java.util.Formatter;

/**
 *
 * @author ashik
 */
public class WriteTextFile {

    private Formatter formatter;
    private String filePath;
    private boolean isApended;

    public WriteTextFile(String filePath) {

        try {

            this.filePath = filePath;
            this.isApended = false;
            this.formatter = new Formatter(this.filePath);
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public void setFilePath(String filePath) {

        try {

            this.filePath = filePath;
            this.isApended = false;
            this.formatter = new Formatter(this.filePath);
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public boolean reset() {

        try {

            this.isApended = false;
            this.formatter = new Formatter(this.filePath);
            return true;
        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    public boolean write(String... textToWrite) {

        try {

            for (String text : textToWrite) {

                this.formatter.format("%s", text);
            }
            return true;
        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    public boolean writeln(String... textToWrite) {

        try {

            for (String text : textToWrite) {

                text += "\n";
                this.formatter.format("%s", text);
            }
            return true;
        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    private void apendLine(String... textToWrite) {

        try {

            String[] currentLines;

            if (!this.isApended) {

                ReadTextFile readTextFile = new ReadTextFile(this.filePath);

                currentLines = readTextFile.readNextLinesFrom(0);

                readTextFile.close();

                this.isApended = true;

                this.writeln(currentLines);
            }

            this.writeln(textToWrite);
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public boolean apendLast(String... textToWrite) {

        try {

            this.apendLine(textToWrite);
            return true;
        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    public boolean apendFrom(int initialLineNumber, String... textToWrite) {

        try {

            this.close();

            ReadTextFile readTextFile = new ReadTextFile(this.filePath);

            String[] firstHalfLines = readTextFile.readNextLinesTo(initialLineNumber);
            String[] secondHalfLines = readTextFile.readNextLinesFrom(initialLineNumber + 1);

            readTextFile.close();

            this.reset();

            this.writeln(firstHalfLines);
            this.writeln(textToWrite);
            this.writeln(secondHalfLines);

            this.isApended = true;

            return true;
        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    public boolean apendFirst(String... textToWrite) {

        try {

            this.close();

            ReadTextFile readTextFile = new ReadTextFile(this.filePath);

            String[] secondHalfLines = readTextFile.readNextLinesFrom(0);

            readTextFile.close();

            this.reset();

            this.writeln(textToWrite);
            this.writeln(secondHalfLines);

            this.isApended = true;

            return true;
        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    public void close() {

        try {

            this.isApended = false;
            this.formatter.close();
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }
}
