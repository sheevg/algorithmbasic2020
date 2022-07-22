package class05.test;

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
        return lessEqual;
    }

    // 荷兰国旗划分
    // <arr[R], ==arr[R], >arr[R]
    public static int[] netherlandsFlags(int[] arr,int L,int R){

        return null;
    }
}
