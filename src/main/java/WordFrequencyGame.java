import java.util.*;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String sentence){

        final String BLANK_SPACE = "\\s+";
        final String DEFAULT_APPEND = " 1";


        if (sentence.split(BLANK_SPACE).length==1) {
            return sentence + DEFAULT_APPEND;
        } else {

            try {
                //split the input string with 1 to n pieces of spaces
                String[] splittedSentence = sentence.split(BLANK_SPACE);
                
                List<Input> wordsMappedWithFrequency = getWordsMappedWithFrequency(splittedSentence);
                removeDuplicateWordInList(wordsMappedWithFrequency);
                return appendWordFrequency(wordsMappedWithFrequency);

            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<Input> getWordsMappedWithFrequency(String[] splittedSentence) {
        return Arrays.stream(splittedSentence)
                .map(word -> new Input(word, Collections.frequency(Arrays.asList(splittedSentence), word)))
                .sorted((w1, w2) -> w2.getWordCount() - w1.getWordCount())
                .collect(Collectors.toList());
    }

    private void removeDuplicateWordInList(List<Input> wordsMappedWithFrequency) {
        HashSet<String> wordsHashSet=new HashSet<>();
        wordsMappedWithFrequency
                .removeIf(e->!wordsHashSet.add(e.getValue()));
    }

    private String appendWordFrequency(List<Input> wordsMappedWithFrequency) {
        return wordsMappedWithFrequency.stream()
                .map(wordInput -> wordInput.getValue() + " " + wordInput.getWordCount())
                .collect(Collectors.joining("\n"));
    }
}
