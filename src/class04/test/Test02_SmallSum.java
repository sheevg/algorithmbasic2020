package class04.test;

// 小和问题
public class Test02_SmallSum {
    public static int smallSum(int[] arr){
        if(arr == null || arr.length<2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    // 递归
    public static int process(int[] arr,int L,int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R-L)>>1);
        return process(arr,L,M)+
        process(arr,M+1,R)+
        merge(arr,L,M,R);
    }

    // merge
    public static int merge(int[] arr,int L,int M,int R){
        // L ~ M,M+1 ~ R
        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int i = 0;
        int res = 0;
        while(p1<=M && p2<=R){
            // 当左边小的时候产生小和，会产生 R-p2+1 个小和
            if(arr[p1] < arr[p2]){
                res += arr[p1] * (R-p2+1);
            }
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 遍历完arr
        while(p1<= M){
            help[i++] = arr[p1++];
        }
        while(p2<= R){
            help[i++] = arr[p2++];
        }
        // 复制回去
        for(i =0;i<help.length;i++){
            arr[L+i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
