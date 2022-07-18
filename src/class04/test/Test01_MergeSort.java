package class04.test;


public class Test01_MergeSort {
    void mergeSort(int[] arr){
        if(arr == null || arr.length < 1){
            return;
        }
        process(arr,0,arr.length-1);
    }

    void process(int[] arr,int L,int R){
        // 出口。当L+1=R时，仍要继续划分，下一轮就是L==R
        if(L == R){
            return;
        }
        int mid = L + ((R-L)>>1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    /**
     * 准备一个数组，两边开始比较，小的放数组里，将指针后移
     *
     *
     */
    void merge(int[] arr,int L,int M,int R){
        // 只需要这么大的辅助数组
        int[] help = new int[R-L+1];
        // 准备两个指针
        int p1 = L;
        int p2 = M+1;
        int i=0;
        // 两个指针都在范围内，而且也不知道遍历的次数
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 有一方遍历完，一方没遍历完时
        while(p1<=M){
            help[i++] = arr[p1++];
        }
        while(p2<=R){
            help[i++] = arr[p2++];
        }
        // 将help更新到arr上
        for(i=0;i<help.length;i++){
            arr[L+i] = help[i];
        }
    }

}
