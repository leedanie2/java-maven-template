package edu.ttap.compression;

import java.io.IOException;

/**
 * The driver for the Grin compression program.
 */
public class Grin {
    /**
     * Decodes the .grin file denoted by infile and writes the output to the
     * .grin file denoted by outfile.
     * @param infile the file to decode
     * @param outfile the file to ouptut to
     * @throws IOException 
     */
    public static void decode(String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);

        int magic = in.readBits(32);
        if(magic != 0x736) {
            throw new IllegalArgumentException("Not a valid .grin file");
        }

        HuffmanTree tree = new HuffmanTree(in);
        tree.decode(in, out);
        in.close();
        out.close();
    }

    /**
     * The entry point to the program.
     * @param args the command-line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.out.println("Need exactly two arguments");
            System.exit(1);
        }
        System.out.println("Usage: java Grin <infile> <outfile>");
        decode(args[0], args[1]);
    }
}