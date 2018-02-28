import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class W05Practical {
    private static List<String> aliceSentences = new ArrayList<>();
    public static void main(String[] args) {
        String filePath = args[0];
        //"/Users/benlawrence859/Documents/University/First Year/CS1003/W05Practical/W05-Practical/src/alice.txt"
        String inputText = args[1];
        //"The King turned pale, and shut his note-book hastily"
        SentenceReader readFile = new SentenceReader();
        try {
            aliceSentences = readFile.readAllSentences(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoredResult[] resultsToOutput = getSortedList(inputText);
        for (int i = 0; i < 50; i++) {
            System.out.println(resultsToOutput[i].getResult()+" "+resultsToOutput[i].getScore()+"\n");
        }
        /*System.out.println(getUnion("The King turned pale, and shut his note-book hastily","The King turned pale, and shut his note-book hastily"));
        System.out.println(getIntersection("The King turned pale, and shut his note-book hastily","The King turned pale, and shut his note-book hastily"));
        *//*System.out.println("INTERSECTION: "+getIntersection("CHAPTER X","As she said this she looked down at her hands, and was surprised to see\n" +
                "that she had put on one of the Rabbit's little white kid gloves while\n" +
                "she was talking "));
        System.out.println("UNION: "+getUnion("CHAPTER X","As she said this she looked down at her hands, and was surprised to see\n" +
                "that she had put on one of the Rabbit's little white kid gloves while\n" +
                "she was talking "));*/
    }

    public static int getUnion(String inputSentence, String aliceSentence) {
        int loopAmount;
        ArrayList<String> unionList = new ArrayList<>();
        ArrayList<String> inputSentenceBiGrams = stringToBiGram(inputSentence);
        ArrayList<String> aliceSentenceBiGrams = stringToBiGram(aliceSentence);
        /*System.out.println(inputSentenceBiGrams);
        System.out.println(aliceSentenceBiGrams);*/
        if (inputSentenceBiGrams.size() <= aliceSentenceBiGrams.size()) {
            loopAmount = inputSentenceBiGrams.size()-1;
        } else {
            loopAmount = aliceSentenceBiGrams.size()-1;
        }
        unionList.addAll(aliceSentenceBiGrams);
        for (int i = 0; i < loopAmount; i++) {
            if (!unionList.contains(inputSentenceBiGrams.get(i))) {
                unionList.add(inputSentenceBiGrams.get(i));
            }
        }
        /*for (int i = 0; i < unionList.size(); i++) {
            System.out.println(unionList.get(i));
        }*/
        //System.out.println(unionList);
        return unionList.size();
    }

    public static int getIntersection(String inputSentence, String aliceSentence) {
        List<String> longestArray;
        List<String> shortestArray;
        List<String> intersectionList = new ArrayList<>();
        int intersectionCount = 0;
        ArrayList<String> inputSentenceBiGrams = stringToBiGram(inputSentence);
        ArrayList<String> aliceSentenceBiGrams = stringToBiGram(aliceSentence);
        /*System.out.println("Intersection");
        System.out.println(inputSentenceBiGrams);
        System.out.println(aliceSentenceBiGrams);*/
        if (inputSentenceBiGrams.size() >= aliceSentenceBiGrams.size()) {
            longestArray = inputSentenceBiGrams;
            shortestArray = aliceSentenceBiGrams;
        } else {
            longestArray = aliceSentenceBiGrams;
            shortestArray = inputSentenceBiGrams;
        }
        for (int i = 0; i < shortestArray.size(); i++) {
            if (longestArray.contains(shortestArray.get(i)) /*&& !intersectionList.contains(shortestArray.get(i))*/) {
                intersectionList.add(shortestArray.get(i));
            }
        }
        /*for (int i = 0; i < intersectionList.size(); i++) {
            System.out.println(intersectionList.get(i));
        }*/
        //System.out.println(intersectionList);
        //System.out.println("----\n----\n----\n----\n----\n");
        return intersectionList.size();
    }

    public static ScoredResult[] getSortedList(String inputSentence) {
        ScoredResult<String>[] scoredResultArray = new ScoredResult[aliceSentences.size()];
        for (int i = 0; i < aliceSentences.size(); i++) {
            /*System.out.println("UNION: "+ getUnion(inputSentence,aliceSentences.get(i)));
            System.out.println("Input Bigrams: "+stringToBiGram(inputSentence));
            System.out.println("INTERSECTION: "+((double)getIntersection(inputSentence,aliceSentences.get(i))));
            System.out.println("Output Bigrams: "+stringToBiGram(aliceSentences.get(i)));*/
            double jaccardIndex = ((double)getIntersection(inputSentence,aliceSentences.get(i)))/getUnion(inputSentence,aliceSentences.get(i));
            //System.out.println(jaccardIndex);
            scoredResultArray[i] = new ScoredResult(aliceSentences.get(i),jaccardIndex);
            /*if (i!=0) {
                Arrays.sort(scoredResultArray, ScoredResult<String>);
            }*/
        }
        Arrays.sort(scoredResultArray,ScoredResult.byScore());
        return scoredResultArray;
        //ScoredResult orderer = new ScoredResult();
        //Arrays.sort(scoredResultArray,scoredResultArray.get(i::compareTo)
    }

    public static ArrayList<String> stringToBiGram(String sentence) {
        ArrayList<String> biGramArray = new ArrayList<>();
        for(int i = 0; i < sentence.length()-1; i++) {
            biGramArray.add(sentence.substring(i,i+2));
        }
        return biGramArray;
    }
}
