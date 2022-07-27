package class06.test;

import class06.code.Code02_Heap;

public class Test02_Heap {
    /**
     * 大根堆
     *
     * - 构造方法
     * - isEmpty
     * - isFull
     * - push：入堆
     * - pop：弹出最大值
     */
    public static class MaxRootHeap{
        private int[] heap;
        private int heapSize; // 堆的当前容量
        private final int limit; // 堆的最大容量

        public MaxRootHeap(int limit){
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public void push(int num){

        }

        public int pop(int num){

            return 0;
        }

        // 上浮
        // 注意这是个工具方法，不是push
        // 新加进来的数，停在了index位置，在完全二叉树中，子节点下标index和父节点下标的关系为：(index-1)/2
        public void heapInsert(int num ,int index){
            // 1)当子节点没有父节点大时会退循环
            // 2)当index=0时，(index-1)/2也为0，即根节点的父节点就是它自己，这时也会退循环
            while(heap[index] > heap[(index-1)/2]){
                swap(heap,index,(index-1)/2);
                index = (index-1)/2;
            }
        }

        // 一个数和它的子节点比较，不断下沉
        public void heapify(){

        }

        public static void swap(int[] arr,int i,int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public static void main(String[] args) {
            int i = -1/2;
            System.out.println(i);
        }


    }
}
