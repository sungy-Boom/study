/**
 * @author guiyong
 * @date 2022/5/6 09:44
 * n jobs
 * platform total tokens
 * <p>
 * First day: job i cost token_i
 * every day job i create 1 token
 * <p>
 * most times of the platform can run
 * <p>
 * 3 7
 * 1 2 2
 * <p>
 * day 1: 1 + 2 + 2 = 5
 * day 2: 2 + 3 + 3 = 8
 * day 3: 3 + 4 + 4 = 11
 * day 4: 4 + 5 + 5 = 14
 * ...
 *
 * 有n个job，每天可用资源数m
 * 数组arr为每个job第一天执行时候需要占用资源个数，每多一天，需要的资源数加一。
 * 求一共可以执行多少次job。
 * 例子：
 * 3，7
 * 1，2，2
 *
 * 解释：
 * 有3个job，每天可用资源总数为7
 * day1：1 + 2 + 2 = 5 第一天三个job都执行，共需要资源5个，总总资源有7。所以三个job都可执行
 * day2：2 + 3 + 3 = 8 第二天三个job都执行，共需要资源8个，总总资源有7。所以只有个job可执行
 * day3：3 + 4 + 4  第三天前两个job执行，耗尽资源，所以有两个job可执行
 * day4：4 + 5 + 5  第四天有一个job可执行
 * day5：5 + 6 + 6  第五天 1个job
 * day6：6 + 7 + 7  第六天 1个job
 * day7：7 + 8 + 8  第七天 1个job
 * day8：8 + 9 + 9  第八天 第一个job耗资源就大于总资源数，所以0个job
 *
 * 所以在给定条件下一共有3+2+2+1+1+1+1 = 11 个job可执行
 * <p>
 * n (1 - 1e5)
 * tokens (1 - 1e9)
 */
public class Main {
    public static void main(String[] args) {
        int job = 3, token = 7;
        int[] tokenArr = new int[]{1, 2, 2};
        int res = new Main().totalTime(job, token, tokenArr);
        System.out.println(res);
    }

    private int totalTime2(int job, int token, int[] eachJobToken) {
        if (job <= 0 || token <= 0 || eachJobToken == null || eachJobToken.length == 0) {
            return 0;
        }

        //排序
        sort(eachJobToken, 0, eachJobToken.length - 1); //升序

        /*//计算第一天需要的token
        dayoneJob
                dayOneToken

        while (true) {
            //计算 k
            runTimes = (totalToken - dayOneToken) / dayoneJob;

            //runTimes == 0 计算 k-1 job 第一次执行
            if (runTimes == 0) {

            }
        }*/
        return 0;
    }

    private int totalTime(int job, int token, int[] eachJobToken) {
        if (job <= 0 || token <= 0 || eachJobToken == null || eachJobToken.length == 0) {
            return 0;
        }

        //排序
        sort(eachJobToken, 0, eachJobToken.length - 1); //升序

        //时间
        int day = 0;
        int maxNum = 0;

        while (true) { //每一次循环是一天

            //eachJobToken 累加
            int sum = 0;
            int j;
            for (j = 0; j < job; j++) {
                int curUseToken = eachJobToken[j] + day;
                sum += curUseToken;
                if (sum > token) {
                    sum -= curUseToken;
                    break;
                }
            }

            if (sum == 0) {
                break;
            }

            day++;
            maxNum += j;
        }
        return maxNum;
    }

    //quick sort
    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = quickSort(arr, left, right);
        sort(arr, 0, mid);
        sort(arr, mid + 1, right);

    }

    private int quickSort(int[] arr, int left, int right) {
        int tmp = arr[left];
        while (left < right) {
            while (left < right && tmp <= arr[right]) {
                right--;
            }

            if (left < right) {
                arr[left] = arr[right];
            }

            while (left < right && tmp >= arr[left]) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = tmp;
        return left;
    }
}
