package hard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NumberOfValidWordsForEachPuzzle {
  public static void main(String[] args) {
    List<String[]> testCase1 = new ArrayList<>();
    try {
      File myObj = new File("C:\\Users\\Admin\\IdeaProjects\\leetcode\\src\\hard\\testCase");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        data = data.substring(1, data.length()-1);
        String[] cols = data.split(",");
        testCase1.add(cols);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    List<String[]> testCase2 = new ArrayList<>();
    testCase2.add(new String[]{"aaaa","asas","able","ability","actt","actor","access"});
    testCase2.add(new String[]{"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"});

    long start = System.currentTimeMillis();
    System.out.println(new NumberOfValidWordsForEachPuzzle().findNumOfValidWords(testCase2.get(0), testCase2.get(1)));
    long end = System.currentTimeMillis();
    System.out.println("Run time: " + (end-start)/1000.0 + "s");

    start = System.currentTimeMillis();
    System.out.println(new NumberOfValidWordsForEachPuzzle().findNumOfValidWords(testCase1.get(0), testCase1.get(1)));
    end = System.currentTimeMillis();
    System.out.println("Run time: " + (end-start)/1000.0 + "s");
  }

  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    PuzzleDict puzzleDict = new PuzzleDict(puzzles);
    WordDict wordDict = new WordDict(words, puzzleDict.getNonExistedChars());
    List<Integer> counts = new ArrayList<>();
    for (String puzzle : puzzles) {
      CharSet puzCharSet = new CharSet(puzzle);
      Character firstChar = puzzle.charAt(0);
      counts.add(wordDict.findWord(puzCharSet, firstChar));
    }
    return counts;
  }

  public class PuzzleDict {
    private CharSet[] words;
    private Set<Character> nonExistedChars = new HashSet<>();
    public PuzzleDict(String[] strs) {
      for (short i = 0; i < 26; i++) {
        nonExistedChars.add((char)((short)'a'+i));
      }
      words = new CharSet[strs.length];
      for (int i = 0; i < strs.length; i++) {
        String str = strs[i];
        CharSet word = new CharSet(str);
        words[i] = word;
        nonExistedChars.removeAll(word.getCharacters());
      }
    }
    public Set<Character> getNonExistedChars() {
      return this.nonExistedChars;
    }

    public CharSet[] getWords() {
      return this.words;
    }
  }

  public class WordDict {
    private List<CharSet> words;
    private Map<Character, List<CharSet>> pos = new HashMap<>();
    public WordDict(String[] strs, Set<Character> nonExistedChars) {
      words = new ArrayList<>();
      for (int i = 0; i < strs.length; i++) {
        String str = strs[i];
        CharSet word = new CharSet(str);
        if (word.containsAny(nonExistedChars)) {
          continue;
        }
        boolean hasAnagram = false;
        CharSet anagram = null;
        for (int j = 0; j < words.size(); j++) {
          anagram = words.get(j);
          if (anagram.isAnagram(word)) {
            hasAnagram = true;
            break;
          }
        }
        if (!hasAnagram) {
          words.add(word);
        } else {
          anagram.setNoAnagrams(anagram.getNoAnagrams()+1);
        }
      }
      for (CharSet word : words) {
        for (Character c : word.getCharacters()) {
          this.put(c, word);
        }
      }
    }

    public int findWord(final CharSet puzzle, final Character firstChar) {
      int count = 0;
      if (pos.get(firstChar) == null) {
        return count;
      }
      List<CharSet> positions = pos.get(firstChar);
      for (CharSet word : positions) {
        if (word.getSize() > puzzle.getSize()) {
          continue;
        }
        if (puzzle.containsAll(word.getCharacters())) {
          count+=word.getNoAnagrams();
        }
      }
      return count;
    }

    private void put(Character c, CharSet position) {
      if (pos == null) {
        pos = new HashMap<>();
      }
      if (pos.get(c) == null) {
        pos.put(c, new ArrayList<>());
      }
      pos.get(c).add(position);
    }
  }

  public class CharSet {
    private Set<Character> characters;
    private Integer noAnagrams=1;
    public CharSet(final String puzzle) {
      characters = new HashSet<>();
      for (int i = 0; i < puzzle.length(); i++) {
        characters.add(puzzle.charAt(i));
      }
    }
    public int getSize() {
      return characters.size();
    }
    public Set<Character> getCharacters() {
      return this.characters;
    }
    public boolean containsAll(final Set<Character> chars) {
      return this.characters.containsAll(chars);
    }

    public boolean containsAny(Set<Character> chars) {
      for (Character c : chars) {
        if (this.characters.contains(c)) {
          return true;
        }
      }
      return false;
    }

    public Integer getNoAnagrams() {
      return this.noAnagrams;
    }

    public void setNoAnagrams(Integer noAnagrams) {
      this.noAnagrams = noAnagrams;
    }

    public boolean isAnagram(CharSet charSet) {
      return this.characters.containsAll(charSet.getCharacters())
          && this.getSize() == charSet.getSize();
    }
  }
}
