package class05.test;

import java.util.Arrays;
import java.util.Collections;

public class Test02_PartitionAndQuickSort {
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // arr[L..R]上，以arr[R]位置的数做划分值
    public static int partition(int[] arr,int L ,int R){
        if(L > R){
            return -1;
        }
        if(L == R){
            return L;
        }
        int lessEqual = L-1;
        int index = L;
        while(index < R){
            if(arr[index] <= arr[R]){
                swap(arr,index,++lessEqual);
            }
            index++;
        }
        swap(arr,++lessEqual,R);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        return lessEqual;
    }

    // 荷兰国旗划分
    // <arr[R], ==arr[R], >arr[R]
    public static int[] netherlandsFlags(int[] arr,int L,int R){
        if(L > R){
            return new int[]{-1, -1};
        }
        if(L == R){
            return new int[]{L, L};
        }
        // 分界线
        int less = L-1;
        int more = R;
        // 以arr[R]为标准
        int index = L;
        while(index < more){
            if(arr[index] < arr[R]){
                swap(arr,++less,index++);
            }else if(arr[index] == arr[R]){
                index ++;
            }else{
                // 在大于的时候，index是不变的
                swap(arr,--more,index);
            }
        }
        swap(arr,more,R);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]+" ");
//        }
        return new int[]{less+1,more};
    }

    public static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int L,int R){
        if(L >= R){ // 为什么这里要有>
            return;
        }
        swap(arr,L + (int)(Math.random()*(R-L+1)),R);
        int[] ints = netherlandsFlags(arr, L, R);
        process(arr,L,ints[0]-1);
        process(arr,ints[1]+1,R);
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

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            //int[] arr2 = copyArray(arr1);
            int[] arr2 = copyArray(arr1);
            Arrays.sort(arr1);
            quickSort(arr2);
            if (!isEqual(arr1, arr2) ) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
