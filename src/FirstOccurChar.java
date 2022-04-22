import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sunguiyong
 * @date 2022/4/11 5:18 下午
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
public class FirstOccurChar {
    public char firstUniqChar(String s) {
        if (s == null || "".equals(s)) {
            return ' ';
        }

        Map<Character, Integer> chMap = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            int times = 1;
            if (chMap.containsKey(s.charAt(i))) {
                times = -1;
            }
            chMap.put(s.charAt(i), times);
        }

        for (int i = 0; i < s.length(); i++) {
            if (chMap.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(new FirstOccurChar().firstUniqChar(""));
        System.out.println(new FirstOccurChar().firstUniqChar("abaccdeff"));
        System.out.println(new FirstOccurChar().firstUniqChar("abdabd"));
    }
}
