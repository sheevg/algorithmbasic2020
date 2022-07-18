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

    // 非递归实现
    public static void mergeSort2(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        int N = arr.length;
        // 起始步长为1
        int mergeSize = 1;
        // 每次循环之后，步长*2
        while(mergeSize < N){
            // 左组左边界下标，在每一次大循环中，左边界都从0开始
            int L = 0;

            // 一次大循环里面，需要很多小循环进行merge，L可以等于N-1
            while(L < N){
                // 确定中点位置，存在左组不满足一个组的情况
                if(mergeSize >= N-L){ // L+mergeSize > N ,防溢出，L是从0开始的，所以这里必须是要>
                    break;
                }
                // 左组右边界
                int M = L + mergeSize -1;
                // 右组右边界，也存在右组不够的情况
                int R = Math.min(L+2*mergeSize-1,N-1);
                merge(arr,L,M,R);
                L = R+1;
            }
            if(mergeSize > N/2){
                break;
            }


            mergeSize = mergeSize<<1;
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
