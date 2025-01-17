package class04.test;

public class Test04_BiggerThanRightTwice {
    public static int biggerThanRightTwice(int[] arr){
        if(arr == null || arr.length <2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int L,int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R-L)>>1);

        return process(arr,L,M)+
        process(arr,M+1,R)+
        merge(arr,L,M,R);
    }

    public static int merge(int[] arr,int L,int M,int R){
//        int res = 0;
//        int windowR = M + 1;
//        // 外层控制左组L的移动，L不变，i是指针
//        for (int i = L; i <= M; i++) {
//            // 内层控制windowR的移动
//            while(windowR <= R && arr[i] > 2*arr[windowR]){
//                windowR++; // 如果右边全满足，windowR最后的值就是R+1
//            }
//            res += windowR-M-1;
//        }

        int p3 = M;
        int p4 = R;
        int res = 0;
        while(p3 >= L && p4 >= M+1){
            if(arr[p3] > 2*arr[p4]){
                p3 --;
                res += p4-M;
            }else{
                p4 --;
            }
        }


        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerThanRightTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
