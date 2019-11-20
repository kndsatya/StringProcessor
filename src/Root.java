public class Root {

  public static void main(String[] args){
      String[] inputStrings = new String[]{
        "{id,operator,collectedWhen{id,dateTag,collectedWhen{id},uploadedWhen},data}",
              "{id,operator,data{id,collectedWhen{mid{test1, test2},OK},collectedWhen{id},dateTag}," +
                      "time{uploadedWhen,collectedWhen}}",
              "{id{test1,test2},time{uploadedWhen,collectedWhen}}",
              "{id{test1,test2},time}",
              "{}","{,}","{}","{id{test1,test2},time{uploadedWhen,collectedWhen}",
             "{id{test1,test2}time{uploadedWhen,collectedWhen}}"," ","  "
      };

      for(String s:inputStrings){
        System.out.println("-------Processing new String------------------");
        StringProcessor stringProcessor = new StringProcessor();
        try {
          stringProcessor.processString(s);
        }catch (Exception e){
          System.out.println("Unable to process input String: "+s+" due to below error:\n"+e.getMessage());
        }
        System.out.println("unSorted result: ");
        System.out.println(stringProcessor.getProcessedString());
        System.out.println();
        System.out.println("Sorted result: ");
        System.out.println(stringProcessor.getSortedString());
        System.out.println("---------Ended Processing string------------");
      }
  }

}
