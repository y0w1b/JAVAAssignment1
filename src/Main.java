import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 作业内容
        pureRandomGenerator();

        // 关于作业所使用的方法的测试和分析
        getTestResult();
    }

    // 生成6个不重复的1~33之间的红球数字及一个1~16之间的蓝球数字并印出。另：由于题目未写明，假定蓝球数字可与红球重复。
    private static void pureRandomGenerator() {
        // 创建Random用来生成随机数字，创建ArrayList记录已生成数字。
        Random random = new Random();
        ArrayList<Integer> redBalls = new ArrayList<>(6);
        System.out.print("红球: ");
        // 生成并打印六个红球数字
        for (int i = 0; i < 6; i++) {
            // 从1~33中生成随机数字，直到生成的数字不与已生成的数字重复为止
            while(true) {
                //生成数字
                int nextRed = random.nextInt(33) + 1;
                // 若生成数字不重复，记录在ArrayList中、打印并跳出while循环
                if (!redBalls.contains(nextRed)) {
                    redBalls.add(nextRed);
                    System.out.print(nextRed + " ");
                    break;
                }
            }
        }
        // 生成蓝球数字并打印
        int blue = random.nextInt(16) + 1;
        System.out.println("\n蓝球: " + blue );
    }

    // 由于本题目使用了随机性方法，以下方法用于测试随机性方法的效率和概率。
    // 在已生成5个红球数字的情况下，第六个生成的红球数字与前面冲突的可能性为 5/33 = 0.15151515...
    // 5次冲突后依然没能生成不重复数字的可能性为 (5/33)^5 = 7.9851*10^(-5)
    // 10次冲突后依然没能生成不重复的数字的可能性为 (5/33)^10 = 6.37618*10^(-9)
    // 因为n次冲突的可能行为(5/33)^n，多次冲突的可能性会成指数降低，所以多次冲突的可能性微乎其微
    // 另，根据个人数次测试以下方法，10万次执行所得到的最多的循环执行次数均小于20次（冲突总数小于 20 - 6 = 14 次）
    // 因此，可得出结论，以上得出1~33之间的六个不重复随机数字的方法的效率是可以保证的。
    private static void getTestResult() {
        int largestCount = 0;
        int loopCount;
        // 运行方法10万次并记录内循环执行次数最多的一次。
        for (int i = 0; i < 100000; i++) {
            loopCount = generatorTest();
            if (largestCount < loopCount) largestCount = loopCount;
        }
        System.out.println("\n\n运行10万次所记录的最多的循环执行次数为" + largestCount + "次");
    }

    // 重复了 pureRandomGenerator() 里的红球数字生成循环，
    // 但去掉了所有的print来增加运行速度，并加上了loopCount来记录并返回内循环执行次数。
    private static int generatorTest() {
        Random random = new Random();
        ArrayList<Integer> redBalls = new ArrayList<>(6);
        int loopCount = 0;
        for (int i = 0; i < 6; i++) {
            while(true) {
                int nextRed = random.nextInt(33) + 1;
                loopCount++;
                if (!redBalls.contains(nextRed)) {
                    redBalls.add(nextRed);
                    break;
                }
            }
        }
        return loopCount;
    }
}
