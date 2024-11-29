package org.elias.fastmath;

public class MathUtil {
    private static final int INITIAL_TABLE_SIZE = 4096;
    private static float[] trigTable;
    private static float radToIndex;

    // Method to initialize the trigonometric table dynamically
    public static void initialize(int tableSize) {
        trigTable = new float[tableSize];
        radToIndex = tableSize / (2.0f * (float) Math.PI);
        for (int j = 0; j < tableSize; ++j) {
            trigTable[j] = toFloat(Math.sin(j * 2.0 * Math.PI / tableSize));
        }
    }

    // Method to return both sine and cosine values
    public static float[] sinAndCos(float radians) {
        if (trigTable == null) {
            initialize(INITIAL_TABLE_SIZE);  // Initialize with default size if not already initialized
        }
        int sinIndex = (int) (radians * radToIndex) & (trigTable.length - 1);
        int cosIndex = (int) (radians * radToIndex + (float) trigTable.length / 4) & (trigTable.length - 1);
        return new float[]{trigTable[sinIndex], trigTable[cosIndex]};
    }

    private static float toFloat(double d) {
        return (float) (Math.round(d * 1.0E8D) / 1.0E8D);
    }
}
