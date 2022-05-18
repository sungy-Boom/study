import java.util.ArrayList;
import java.util.List;

/**
 * @author guiyong
 * @date 2022/5/12 10:33
 */
public class Main2 {

    /*List<String> chList = new ArrayList<String>() {{
        add("");
        add("A");
        add("B");
        add("C");
        add("D");
        add("E");
        add("F");
        add("G");
        add("H");
        add("I");
        add("G");
        add("K");
    }};*/

    public static void main(String[] args) {
        System.out.println(new Main2().convert(1));
        System.out.println(new Main2().convert(3));
        System.out.println(new Main2().convert(27));
        System.out.println(new Main2().convert(29));
        System.out.println(new Main2().convert(53));
        System.out.println(new Main2().convert(701));
        System.out.println(new Main2().convert(702)); //
    }

    public String convert(int n) {
        if (n <= 0 || n >= Integer.MAX_VALUE) {
            return "";
        }

        List<Integer> res = new ArrayList<>();
        while (true) {
            n -= 1;
            if (n <= 26) {
                res.add(n);
                break;
            }
            int divRes = n % 26;
            res.add(divRes);
            n = n / 26;
        }

        StringBuilder strBuilder = new StringBuilder();
        char ch = 'A';
        for (int i = res.size() - 1; i >= 0; i--) {
            ch = (char) (res.get(i) + ch);
            strBuilder.append(ch);
        }
        return strBuilder.toString();
    }
}
