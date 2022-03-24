/**
 * @author sunguiyong
 * @date 2022/3/18 5:46 下午
 * https://leetcode-cn.com/problems/aseY1I/
 */
public class MaxMultiOfTwoStr {

    public int maxProduct2(String[] words) {
        int maxMulti = 0;
        boolean isSame = false;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                isSame = isSameOfTwoStr(words[i], words[j]);
                if (!isSame) {
                    maxMulti = Math.max(maxMulti, words[i].length() * words[j].length());
                }
            }
        }
        return maxMulti;
    }

    public boolean isSameOfTwoStr(String str1, String str2) {
        int[] chArr = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            chArr[str1.charAt(i) - 'a'] = 1;
            for (int j = 0; j < str2.length(); j++) {
                if (chArr[str2.charAt(j) - 'a'] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int[] chIntArr = new int[words.length];

        //获取每个串对应的数字
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                chIntArr[i] = chIntArr[i] | (1 << word.charAt(j) - 'a');
            }
        }

        int maxMulti = 0;
        for (int i = 0; i < chIntArr.length - 1; i++) {
            for (int j = i + 1; j < chIntArr.length; j++) {
                if ((chIntArr[i] & chIntArr[j]) == 0) {
                    maxMulti = Math.max(maxMulti, words[i].length() * words[j].length());
                }
            }
        }
        return maxMulti;
    }
}
