// Sathvika & Anvi 12/4/2025

// Reads 2 books (txt files), capitalizes every word in the input, and writes the result.


import java.io.*;

public class Main {

    public static void main(String[] args) {
 
        long startTime = System.currentTimeMillis();

        Thread worker = new Thread(() -> {

            processFile("warandpeace.txt", "warandpeaceCAP.txt");

            processFile("confessionsofstaug.txt", "confessionsofstaugCAP.txt");

        });

        worker.start();
        try {
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total time (ms): " + (endTime - startTime));
    }


    private static void processFile(String inputName, String outputName) {
        try {
            StringBuilder text = new StringBuilder();


            BufferedReader reader = new BufferedReader(new FileReader(inputName));
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            reader.close();

            String modified = capitalizeEveryWord(text.toString());

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
            writer.write(modified);
            writer.close();

            System.out.println("Finished processing: " + inputName);

        } catch (IOException e) {
            System.err.println("Error with file: " + inputName);
        }
    }

    private static String capitalizeEveryWord(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder(text.length());

        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
