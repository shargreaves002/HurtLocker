import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
    private List<String> list;
    private String text, delimiter;
    private Map<String, List<String>> itemList;
    private int numOfErrors;
    private ArrayList<String> cookies, bread, milk, apples, errors;

    Parser(String text, String delimiter) {
        this.text = text;
        this.delimiter = delimiter;
        itemList = new HashMap<>();
        cookies = new ArrayList<>();
        bread = new ArrayList<>();
        milk = new ArrayList<>();
        apples = new ArrayList<>();
        errors = new ArrayList<>();
    }

    private void parseToList(){
        fix("(?i)(name)", "Name");
        fix("(?i)(c..kies)", "Cookies");
        fix("(?i)(bread)", "Bread");
        fix("(?i)(milk)", "Milk");
        fix("(?i)(apples)", "Apples");
        fix("(?i)(price)", "Price");
        fix("(?i)(food)", "Food");
        fix("(?i)(expiration)", "Expiration");
        this.list = new ArrayList<>(Arrays.asList(this.text.split(this.delimiter)));
    }

    private void fix(String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        this.text = matcher.replaceAll(replacement);
    }

    Map<String, List<String>> parseToMap(){
        parseToList();
        ArrayList<String> items;
        for (String i : list) {
            String type = "";
            items = new ArrayList<>(Arrays.asList(i.split("[;^*@%]")));
            for (String j : items){
                boolean flag = j.endsWith(":") || j.startsWith(":");
                if (flag) {
                    numOfErrors++;
                } else {
                    String key = j.substring(0, j.indexOf(':'));
                    if (key.equals("Name")) type = j.substring(j.indexOf(':') + 1);
                    if (key.equals("Price")) {
                        String price = j.substring(j.indexOf(':') + 1);
                        switch (type) {
                            case "Cookies":
                                cookies.add(price);
                                break;
                            case "Bread":
                                bread.add(price);
                                break;
                            case "Milk":
                                milk.add(price);
                                break;
                            case "Apples":
                                apples.add(price);
                        }
                    }
                }
            }
        }
        errors.add(Integer.toString(numOfErrors));
        itemList.put("Cookies", cookies);
        itemList.put("Bread", bread);
        itemList.put("Milk", milk);
        itemList.put("Apples", apples);
        itemList.put("Errors", errors);
        return itemList;
    }
}
