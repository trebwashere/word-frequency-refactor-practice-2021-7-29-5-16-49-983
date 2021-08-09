import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String sentence){

        final String BLANK_SPACE = "\\s+";
        final String DEFAULT_APPEND = " 1";

        if (sentence.split(BLANK_SPACE).length==1) {
            return sentence + DEFAULT_APPEND;
        }
        try {
            List<String> splittedSentence = Arrays.asList(sentence.split(BLANK_SPACE));
            List<Input> wordsMappedWithFrequency = getSorted(splittedSentence);
            removeDuplicateWordInList(wordsMappedWithFrequency);
            return appendWordFrequency(wordsMappedWithFrequency);

        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<Input> getSorted(List<String> splittedSentence) {
        return splittedSentence.stream()
                .map(word -> new Input(word, Collections.frequency(splittedSentence, word)))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(Collectors.toList());
    }

    private void removeDuplicateWordInList(List<Input> wordsMappedWithFrequency) {
        HashSet<String> wordsHashSet = new HashSet<>();
        wordsMappedWithFrequency
                .removeIf(word ->!wordsHashSet.add(word.getValue()));
    }

    private String appendWordFrequency(List<Input> wordsMappedWithFrequency) {
        return wordsMappedWithFrequency.stream()
                .map(wordInput -> wordInput.getValue() + " " + wordInput.getWordCount())
                .collect(Collectors.joining("\n"));
    }
}
