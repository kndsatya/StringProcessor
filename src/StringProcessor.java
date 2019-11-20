import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringProcessor {

  private String processedString;
  private String sortedString;
  private List<String> resultList;

  public StringProcessor() {
    this.resultList = new ArrayList<>();
  }

  public String getProcessedString() {
    if (processedString == null) {
      StringBuilder result = new StringBuilder("");
      for (int i = 0; i < resultList.size() - 1; i++) {
        result.append(resultList.get(i)).append('\n');
      }
      if (resultList.size()>=1){
        result.append(resultList.get(resultList.size() - 1));
      }
      this.processedString = result.toString();
    }
    return this.processedString;
  }

  public String getSortedString() {

    if (sortedString == null) {
      StringBuilder result = new StringBuilder("");
      List<String> sortedList = new ArrayList<>();
      sortedList.addAll(resultList);
      Collections.sort(sortedList, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
          int o1HyphenCount  = getHyphenCount(o1);
          int o2HyphenCount = getHyphenCount(o2);
          String s1 = o1.substring(o1HyphenCount,o1.length());
          s1 = s1.trim().toLowerCase();
          String s2 = o2.substring(o2HyphenCount,o2.length());
          s2 = s2.trim().toLowerCase();
          if(s1.equals(s2)){
             return (o1HyphenCount - o2HyphenCount);
          }

          return s1.compareTo(s2);
        }

        private int getHyphenCount(String s){
          int result =0;
          for(int i=0;i<s.length();i++){
              if(s.charAt(i)=='-'){
                result++;
              }else{
                break;
              }
          }
          return result;
        }
      });
      for (int i = 0; i < sortedList.size() - 1; i++) {
        result.append(sortedList.get(i)).append('\n');
      }
      if(sortedList.size()>=1){
        result.append(sortedList.get(sortedList.size() - 1));
      }

      this.sortedString = result.toString();
    }

    return this.sortedString;
  }

  public void processString(String s) {

    if (s == null || s.length() < 2) {
      throw new IllegalArgumentException("Intput string should atleast be of length 2");
    }

    if (s.length() >= 2 && s.charAt(0) != '{' && s.charAt(s.length() - 1) != '}') {
      throw new IllegalArgumentException("Input shpuld be of foramt \"{}\"");
    }

    StringBuilder tempString = new StringBuilder("");
    int hyphenCount = 0;
    for (int i = 1; i < s.length() - 1; i++) {

      if (s.charAt(i) == ',') {
        if (tempString.length() > 0) {
          storeResult(tempString.toString(), hyphenCount);
          tempString = new StringBuilder("");
        }
      } else if (s.charAt(i) == '{') {
        if (tempString.length() > 0) {
          storeResult(tempString.toString(), hyphenCount);
          tempString = new StringBuilder("");
          hyphenCount++;
        }
      } else if (s.charAt(i) == '}') {
        if (tempString.length() > 0) {
          storeResult(tempString.toString(), hyphenCount);
          tempString = new StringBuilder("");
          hyphenCount--;
        }
      } else {
        tempString.append(s.charAt(i));
      }
    }

    if (tempString.length() != 0) {
      storeResult(tempString.toString(), hyphenCount);
    }

  }

  private void storeResult(String s, int hyphenCount) {
    StringBuilder result = new StringBuilder("");
    for (int i = 0; i < hyphenCount; i++) {
      result.append("-");
    }
    result.append(s);
    resultList.add(result.toString());
  }
}
