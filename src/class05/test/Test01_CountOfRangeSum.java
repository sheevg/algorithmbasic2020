package class05.test;

public class Test01_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        // 极端情况
        if(nums == null || nums.length == 0){
            return 0;
        }
        // 求前缀和
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }

        return process(sum,0,sum.length-1,lower,upper);

    }

    public static int process(long[] sum,int L ,int R,int lower,int upper){
        if(L == R){
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R-L) >> 1);
        return process(sum,L,M,lower,upper)+
                process(sum,M+1,R,lower,upper)+
                merge(sum,L,M,R,lower,upper);
    }

    public static int merge(long[] arr,int L ,int M ,int R,int lower,int upper){
        int windowL = L;
        int windowR = L;
        int res = 0;
        // 左边取[windowL,windowR)
        for(int i = M+1; i<=R; i++){
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            // 移动左右边框
            while(windowR <= M && arr[windowR] <= max ){
                windowR++;
            }
            // 注意这里是小于min，这样最后windowL是可以取到的。这里不是>=min一定要想清楚，左指针是遍历到满足条件时停下
            while(windowL <= M && arr[windowL] < min){
                windowL ++ ;
            }
            res += windowR-windowL;
        }

        // 经典归并
        long[] help = new long[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int i =0;
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2147483647,-2147483648,-1,0};
        int lower = -1;
        int upper = 0;
        int i = countRangeSum(arr,-1,0);
        System.out.println(i);
    }
}
