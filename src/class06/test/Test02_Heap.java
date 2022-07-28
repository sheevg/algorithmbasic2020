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
            if(heapSize == limit){
                throw new RuntimeException("堆已满");
            }
            heap[heapSize] = num;
            heapInsert(heap,heapSize++);
        }

        public int pop(){
            if(heapSize == 0){
                throw new RuntimeException("堆已空");
            }
            int num = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return num;
        }

        // 上浮
        // 注意这是个工具方法，不是push
        // 新加进来的数，停在了index位置，在完全二叉树中，子节点下标index和父节点下标的关系为：(index-1)/2
        public void heapInsert(int[] arr ,int index){
            // 1)当子节点没有父节点大时会退循环
            // 2)当index=0时，(index-1)/2也为0，即根节点的父节点就是它自己，这时也会退循环
            while(arr[index] > arr[(index-1)/2]){
                swap(arr,index,(index-1)/2);
                index = (index-1)/2;
            }
        }

        // 一个节点不断找它的左右孩子，进行下沉
        public void heapify(int[] arr,int index,int heapSize){
            int left = index*2+1; // 左孩子一定有，右孩子不一定有
            // 让左孩子和heapSize进行比较
            // 如果一个节点的左孩子 大于或者等于了heapSize，则说明左孩子已经越界了，就不用继续下沉了
            while(left < heapSize){
                // 左，右节点中最大的下标
                int largest = left + 1< heapSize && arr[left+1] > arr[left] ? left+1 : left;
                // 父，子节点中最大的下标
                largest = arr[largest] > arr[index] ? largest : index;
                // 如果最大的下标就是index，则退出循环
                if(largest == index){
                    break;
                }
                // 不是则交换左右中最大和当前节点
                swap(arr,largest,index);
                // 更新index和left
                index = largest;
                left = index*2+1;
            }
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
