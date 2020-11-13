package pattern;

/**
 * 模式匹配
 *
 * @author tonysu
 * @version 1.0
 * @since 2020/11/13 5:02 下午
 */
public class PatternMatch {

    /**
     * 暴力解法
     *      https://blog.csdn.net/qq_42267300/article/details/86695859
     *      主要思想：从主串的第一个元素开始，与模式串第一个元素相比较，相等则逐一比较，若有不同元素，主串回溯至下一个元素，与模式串的一个元素相比较，依次循环。
     *      步骤：
     *          1、首先，我们需要将主串进行遍历。
     *          2、主串的每一次遍历中，与模式串进行比较，若相同比较下一个元素。
     *          3、如果模式串比较结束，说明模式串成功匹配，返回主串当前下标。
     *          4、如果两元素不同，说明此处匹配失败，主串继续遍历下一个元素。
     *          5、若主串遍历结束，仍未成功匹配，则说明主串中无该模式串，返回-1。
     * @param str
     * @param pattern
     * @return
     */
    public static int bruteForceStringMatch(String str, String pattern) {
        //如果主串长度不小于模式串，则进入模式匹配
        if (str.length() >= pattern.length()) {
            //获取两串的字符数组，以便遍历
            char strOfChars[] = str.toCharArray();
            char patternOfChars[] = pattern.toCharArray();

            //两个循环控制变量
            int loopOfStr, loopOfPattern;
            //遍历主串，任意一串遍历结束，则匹配结束
            for (loopOfStr = 0, loopOfPattern = 0 ; loopOfStr < str.length() && loopOfPattern < pattern.length() ;) {
                //如果两元素相同，比较下一个元素
                if (strOfChars[loopOfStr] == patternOfChars[loopOfPattern]) {
                    loopOfStr++;
                    loopOfPattern++;
                } else {
                    loopOfStr -= loopOfPattern - 1;//主串下标回溯
                    loopOfPattern = 0;//模式串下标重置
                }
            }

            //模式串匹配结束，表示匹配成功
            if (loopOfPattern == pattern.length()) {
                return loopOfStr - loopOfPattern;//主串中模式串第一次出现的位置
            }
        }

        //模式匹配失败
        return -1;
    }

    /**
     * KMP算法
     *       https://blog.csdn.net/qq_42267300/article/details/86695859
     *       主要解决了BF算法的回溯问题，从而降低了时间复杂度。他的时间复杂度为O(m+n)。
     *       主要思想：KMP算法的关键是利用匹配失败后的信息， 尽量减小两串的匹配次数，以达到快速匹配的目的。
     *               通过一个next[]数组寻找最长且相同的前缀和后缀，以减少匹配次数。
     *       步骤：
     *          1、首先，我们需要将主串进行遍历。
     *          2、主串的每一次遍历中，与模式串进行比较，若相同比较下一个元素。
     *          3、如果模式串比较结束，说明模式串成功匹配，返回主串当前下标。
     *          4、如果两元素不同，说明此处匹配失败，模式串下标更新至next[]值的位置，主串继续遍历下一个元素。
     *          5、若主串遍历结束，仍未成功匹配，则说明主串中无该模式串，返回-1。：
     * @param str
     * @param pattern
     * @return
     */
    public static int KMP(String str, String pattern) {
        //如果主串长度不小于模式串，则进入模式匹配
        if (str.length() >= pattern.length()) {
            //获取next数组
            int next[] = getNext(pattern);

            //获取两串的字符数组，以便遍历
            char strOfChars[] = str.toCharArray();
            char patternOfChars[] = pattern.toCharArray();

            //两个循环控制变量
            int loopOfStr, loopOfPattern;
            //遍历主串，任意一串遍历结束，则匹配结束
            for (loopOfStr = 0, loopOfPattern = 0 ; loopOfStr < str.length() && loopOfPattern < pattern.length() ;) {
                //如果两元素相同，或模式串全部匹配失败，比较下一个元素
                if (loopOfPattern == -1 || strOfChars[loopOfStr] == patternOfChars[loopOfPattern]) {
                    loopOfStr++;
                    loopOfPattern++;
                } else {
                    //模式串下标置为next值
                    loopOfPattern = next[loopOfPattern];
                }
            }

            //模式串匹配结束，表示匹配成功
            if (loopOfPattern == pattern.length()) {
                //主串中模式串第一次出现的位置
                return loopOfStr - loopOfPattern;
            }
        }

        //模式匹配失败
        return -1;
    }

    private static int[] getNext(String pattern)
    {
        //获取两串的字符数组，以便遍历
        char patternOfChars[] = pattern.toCharArray();
        //创建next数组
        int[] next = new int[pattern.length()];
        int nextValue = -1, loopOfPattern = 0;//初始化next值及模式串下标
        next[0] = -1;//这里采用-1做标识
        while(loopOfPattern < pattern.length() -1)
        {
            //获取next数组
            if(nextValue == -1 || patternOfChars[loopOfPattern] == patternOfChars[nextValue])
            {
                nextValue++;
                loopOfPattern++;
                next[loopOfPattern] = nextValue;
            } else {
                nextValue = next[nextValue];
            }
        }
        return next;
    }
}
