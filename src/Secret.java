/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Secret {

    public static void main(String[] args) {
        System.out.println(new Secret().numDecodings("0"));
        System.out.println(new Secret().numDecodings("123"));
        System.out.println(new Secret().numDecodings("0226"));
        System.out.println(new Secret().numDecodings("6781020030"));
        System.out.println(new Secret().numDecodings("1234560789"));
    }

    public int numDecodings(String s) {
        if ("0".equals(s)) {
            return 0;
        }
        int count = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0'){
                continue;
            }
            if (s.charAt(i) <= '2' && s.charAt(i + 1) <= '6') {
                count++;
            }
        }
        return count;
    }
}
