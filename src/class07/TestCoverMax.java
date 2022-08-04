package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TestCoverMax {
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
        Line[] lineArr = new Line[lines.length];
        // 将lines放入lineArr中
        for (int i = 0; i < lines.length; i++) {
            Line line = new Line(lines[i][0], lines[i][1]);
            lineArr[i] = line;
        }
        // 按照线段开始的端点排序
        Arrays.sort(lineArr);
        // 最大重合线段数
        int maxCover = 0;
        // 准备一个堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lineArr.length; i++) {
            while(!heap.isEmpty() && heap.peek() <= lineArr[i].start){
                heap.poll();
            }
            heap.add(lineArr[i].end);
            maxCover = Math.max(maxCover,heap.size());
        }
        return maxCover;
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

            if (ans1 != ans2 ) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}

class Line implements Comparable<Line>{
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
