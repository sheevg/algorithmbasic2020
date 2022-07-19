package class04.test;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 思想：
 * 让左边有序，让右边有序，合并
 */
public class Test01_MergeSort {
    // 递归实现
    public static void mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int L,int R){
        if(L==R){
            return;
        }

        int mid = L + ((R-L)>>1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr,int L,int M,int R){
        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int i = 0;
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=M){
            help[i++] = arr[p1++];
        }
        while(p2<=R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
    }

    /**
     * 非递归实现
     *
     * 步长mergeSize从1开始，每经过一次循环，mergeSize * 2，mergeSize要小于数组长度N，可以等于
     * 每一次循环中：
     * 找到左组的左边界，中点，右组右边界，进行merge，使每一组有序
     *
     */
    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while(mergeSize < N){
            // 每一个左组的左边界
            int L = 0;
            // 处理每一组，之后更新L。L不能超过N
            while(L < N){
                // 如果在小循环中L+mergeSize >= N了，这时是最后一组的左组不够一组了，或者说刚好一组，此时是不需要merge的
                if(L+mergeSize >= N){
                    break;
                }
                // 如果够一组，就接着确定M和R
                int M = L + mergeSize -1;
                // 左边够一组，右边可能不够一组，R取值有两个可能
                int R = Math.min(arr.length-1,M+mergeSize);
                // 进行merge
                merge(arr,L,M,R);
                // 更新L
                L = R+1;
            }
            // 当一个步长处理完了之后，要更新mergeSize，这里提前判断2倍的mergeSize是否会溢出
            // 这里mergeSize = N/2的时候，就是数组两边都是有序的了，还需要最后一次，所以这里不能带等号
            // 下一次是mergeSize = N
            if(mergeSize > N/2){
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void mergeSort3(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        int mergeSize = 1; // 步长，半个组（左组）的长度
        int N = arr.length;
        while(mergeSize < N){ // 这里要考虑当mergeSize = N时，还要往下走吗



        }
    }

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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            //Arrays.sort(arr2);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
