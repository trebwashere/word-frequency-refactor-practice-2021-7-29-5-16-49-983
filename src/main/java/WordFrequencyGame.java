import java.util.Arrays;
import java.util.Collections;
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
            List<WordInfo> wordsMappedWithFrequency = getWordsSortedDescViaFrequency(splittedSentence);
            return appendWordFrequency(wordsMappedWithFrequency);

        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordInfo> getWordsSortedDescViaFrequency(List<String> splittedSentence) {
        return splittedSentence.stream()
                .distinct()
                .map(word -> new WordInfo(word, Collections.frequency(splittedSentence, word)))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(Collectors.toList());
    }

    private String appendWordFrequency(List<WordInfo> wordsMappedWithFrequency) {
        return wordsMappedWithFrequency.stream()
                .map(wordInfoInput -> wordInfoInput.getValue() + " " + wordInfoInput.getWordCount())
                .collect(Collectors.joining("\n"));
    }
}
