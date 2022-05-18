/**
 * @author guiyong
 * @date 2022/5/12 13:30
 * 10，11，12，3，5，6，7，4，5，6，7，8
 * 3，5，6，7 == 》
 * 1，2，10，11，12，3，5，6，7，4，5，6，7
 */
public class Main4 {
    public static void main(String[] args) {

    }

    public int[] getIncrArr(int[] arr) {

        int left = 0, right = arr.length - 1;

        //找到左右的index
        while (left < arr.length - 1) {
            if (arr[left] < arr[left + 1]) {
                left++;
            }
        }

        if (left == arr.length - 1) {
            return arr;
        }

        while (right > 0) {
            if (arr[right] > arr[right - 1]) {
                right--;
            }
        }
        if (arr[arr.length - 1] < arr[left]) {
            //返回0 到 left
            return null;
        }

        //比较left和right两边的数据
        while (right < arr.length - 1) {
            if (arr[left] > arr[right]) {
                right++;
            }
        }

        return null;
    }
}
