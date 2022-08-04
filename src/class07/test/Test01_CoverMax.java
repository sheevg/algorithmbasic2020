package class07.test;

import class07.code.Code01_CoverMax;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 二维数组：
 * int[][] arr = new int[n][2]
 *
 * 二维数组其实就是多个一维数组，像上面这样定义的arr，就可以看作是由n个长度为2的int[]组成
 * 所以对于一个个线段，就可以理解成是n个长度为2的数组 {{1,2},{1,4},{4,7},...} 这样
 *
 */
public class Test01_CoverMax {

    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }
    public static int maxCover2(int[][] lines){
        // 首先将线段放入一个线段数组中
        Line[] lineArr = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = new Line(lines[i][0], lines[i][1]);
            lineArr[i] = line;
        }
        // 将线段数组按照端点开始排序
        Arrays.sort(lineArr);
        // 准备一个堆，一个最大值
        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 遍历线段，对于每一个线段：
        // 先抛出堆中线段头比它小的
        for (int i = 0; i < lineArr.length; i++) {
            int start = lineArr[i].start;// 线段头
            while(!heap.isEmpty() && heap.peek() <= start){
                heap.poll();
            }
            // 抛完之后，将线段尾加入
            heap.add(lineArr[i].end);
            max = Math.max(heap.size(),max);
        }
        return max;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class StartComparator implements Comparator<Code01_CoverMax.Line> {

        @Override
        public int compare(Code01_CoverMax.Line o1, Code01_CoverMax.Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {

        Code01_CoverMax.Line l1 = new Code01_CoverMax.Line(4, 9);
        Code01_CoverMax.Line l2 = new Code01_CoverMax.Line(1, 4);
        Code01_CoverMax.Line l3 = new Code01_CoverMax.Line(7, 15);
        Code01_CoverMax.Line l4 = new Code01_CoverMax.Line(2, 4);
        Code01_CoverMax.Line l5 = new Code01_CoverMax.Line(4, 6);
        Code01_CoverMax.Line l6 = new Code01_CoverMax.Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Code01_CoverMax.Line> heap = new PriorityQueue<>(new Code01_CoverMax.StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Code01_CoverMax.Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            //int ans3 = maxCover3(lines);
            if (ans1 != ans2 /*|| ans1 != ans3*/) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}

class Line implements Comparable<Line> {
    int start;
    int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(Line line) {
        return this.start - line.start;
    }
}
