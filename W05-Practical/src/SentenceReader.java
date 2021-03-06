
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.Object;

public class SentenceReader {

    /**
     * Given a file path, this method will read the entire contents of the file,
     * split the text into sentences, and return a list of sentences.
     *
     * The sentence splitting is very basic: we just split on the full-stop character.
     *
     * The contents of each sentence are sanitised as well.
     * Sanitisation is done by replacing each character with the corresponding lower case character,
     * removing all punctuation characters, etc.
     *
     * @param filepath The file path for the input file
     * @return A list of all sentences in the file
     * @throws IOException May throw an IOException while reading the file
     */
    public List<String> readAllSentences(String filepath) throws IOException {
        String fileContents = new Scanner(new File(filepath)).useDelimiter("\\Z").next();
        //String[] sentenceArray = fileContents.split('.');
        List<String> outputSentences = new ArrayList<>(Arrays.asList(fileContents.split("\\.")));
        /*for (int i = 0; i < outputSentences.size(); i++) {
            System.out.println("\nNEW SENTENCE");
            System.out.println(sanitiseSentence(outputSentences.get(i)));
        }*/


        return outputSentences;
    }

    /**
     * Given a string, this method will sanitise it.
     * Sanitisation is done by replacing each character with the corresponding lower case character,
     * removing all punctuation characters, etc.
     *
     * @param sentence The input string
     * @return The output string
     */
    public String sanitiseSentence(String sentence) {
        List<String> words = new ArrayList<>();
        for (String word : sentence.split("\\s+")) {
            String sanitised = word.toLowerCase().replaceAll("[^a-z]+", "");
            if (!sanitised.isEmpty()) {
                words.add(sanitised);
            }
        }

        return joinWords(words);
    }

    /**
     * This is a private method to join a list of words into a sentence.
     *
     * @param words The list of words
     * @return A string which contains the words separated by spaces
     */
    private String joinWords(List<String> words) {
        String joined = "";
        if (words.size() > 0) {
            joined = words.get(0);
        }
        for (int i = 1; i < words.size(); i++) {
            joined += " " + words.get(i);
        }
        return joined;
    }

}
