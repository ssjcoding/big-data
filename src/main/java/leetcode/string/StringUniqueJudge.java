package leetcode.string;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *      输入: s = "leetcode"
 *      输出: false
 *
 * 示例 2：
 *      输入: s = "abc"
 *      输出: true
 *
 * 限制：
 *      0 <= len(s) <= 100
 *      如果你不使用额外的数据结构，会很加分。
 *
 * 实现思路：
 *      （1）将String转换成char数组
 *      （2）对char数组进行归并排序
 *      （3）遍历排序后的char数组，并判断相邻位置是否相同，如果相同则返回false， 否则最后返回true
 *
 * @author tonysu
 * @version 1.0
 * @since 2020/9/3 4:53 下午
 */
public class StringUniqueJudge {
    public boolean isUnique(String str) {
        char[] chars = str.toCharArray();
        mergeSortRecursive(chars, new char[chars.length], 0, chars.length-1);
        for(int i=0; i<chars.length-1; i++){
            if(chars[i] == chars[i+1]){
                return false;
            }
        }
        return true;
    }

    // 归并排序（Java-递归版）
    private static void mergeSortRecursive(char[] arr, char[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

    public static void main(String[] args) {
        String input = "abc";
        StringUniqueJudge stringUniqueJudge = new StringUniqueJudge();
        System.out.println(stringUniqueJudge.isUnique(input));

    }
}
