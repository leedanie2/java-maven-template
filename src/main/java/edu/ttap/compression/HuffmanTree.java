package edu.ttap.compression;

/**
 * A HuffmanTree derives a space-efficient coding of a collection of byte
 * values.
 *
 * The huffman tree encodes values in the range 0--255 which would normally
 * take 8 bits.  However, we also need to encode a special EOF character to
 * denote the end of a .grin file.  Thus, we need 9 bits to store each
 * byte value.  This is fine for file writing (modulo the need to write in
 * byte chunks to the file), but Java does not have a 9-bit data type.
 * Instead, we use the next larger primitive integral type, short, to store
 * our byte values.
 */
public class HuffmanTree {

    /**
     * Represents a node in a binary tree.
     */
    public static class Node {
        public short value;

        public Node left;

        public Node right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        Node(short value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    
    private Node root;

    /**
     * Constructs a new HuffmanTree from the given file.
     * @param in the input file (as a BitInputStream)
     */
    public HuffmanTree(BitInputStream in) {
        root = treeH(in);
    }

    /**
     * Recursively reads tree bits and returns nodes in order
     * @param in the input file (as a BitInputStream)
     * 
     * @return node the node newly created
     */
    public Node treeH(BitInputStream in) {
        int bit = in.readBit();
        if (bit == -1) {
            throw new IllegalArgumentException();
        } else if (bit == 0) { // is a leaf
            short value = (short) in.readBits(9);
            return new Node(value, null, null);
        } else { // is a node, has children
            Node left = treeH(in);
            Node right = treeH(in);
            return new Node((short) 0, left, right);
        }
    }

    /**
     * Decodes a stream of huffman codes from a file given as a stream of
     * bits into their uncompressed form, saving the results to the given
     * output stream. Note that the EOF character is not written to out
     * because it is not a valid 8-bit chunk (it is 9 bits).
     * @param in the file to decompress.
     * @param out the file to write the decompressed output to.
     */
    public void decode(BitInputStream in, BitOutputStream out) {
        Node cur = root;
        int bit;
        while ((bit = in.readBit()) != -1) {
            if (bit == 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            if (cur.right == null && cur.left == null) { // leaf
                if (cur.value == 256) { // eof
                    break;
                }
                out.writeBits(cur.value, 8);
                cur = root;
            }
        }
    }
}