import java.util.HashMap;
import java.util.List;

class Printer {
    private String output;
    private HashMap<String, Integer> apples, cookies, bread, milk;
    private int errors;

    Printer (){
        apples = new HashMap<>();
        cookies = new HashMap<>();
        bread = new HashMap<>();
        milk = new HashMap<>();
        errors = 0;
        output = "";
    }
    String print (HashMap<String, List<String>> inputMap) {
        inputMap.forEach(this::increment);
        output += "Apple Prices:\n";
        apples.forEach((key, value) -> output += key + ", " + value + "\n");

        output += "Cookie Prices:\n";
        cookies.forEach((key, value) -> output += key + ", " + value + "\n");

        output += "Bread Prices:\n";
        bread.forEach((key, value) -> output += key + ", " + value + "\n");

        output += "Milk Prices:\n";
        milk.forEach((key, value) -> output += key + ", " + value + "\n");

        output += "Errors: " + errors;
        return output;
    }

    private void increment(String key, List<String> value) {
        switch (key){
            case "Apples":
                for (String i : value){
                    if (!apples.containsKey(i)){
                        apples.put(i, 1);
                    } else {
                        apples.replace(i, apples.get(i) + 1);
                    }
                }
                break;
            case "Cookies":
                for (String i : value){
                    if (!cookies.containsKey(i)){
                        cookies.put(i, 1);
                    } else {
                        cookies.replace(i, cookies.get(i) + 1);
                    }
                }
                break;
            case "Bread":
                for (String i : value){
                    if (!bread.containsKey(i)){
                        bread.put(i, 1);
                    } else {
                        bread.replace(i, bread.get(i) + 1);
                    }
                }
                break;
            case "Milk":
                for (String i : value){
                    if (!milk.containsKey(i)){
                        milk.put(i, 1);
                    } else {
                        milk.replace(i, milk.get(i) + 1);
                    }
                }
                break;
            case "Errors":
                errors += Integer.parseInt(value.get(0));
                break;
        }
    }
}
