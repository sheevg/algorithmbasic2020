package class07.test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 这个T如果是基本类型，则
 * @param <T>
 */
public class Test02_HeapGreater<T extends Comparable<T>> {

    private ArrayList<T> heap; // list是堆的本质
    private HashMap<T,Integer> indexMap; // 反向索引表
    private Integer heapSize;

    public Test02_HeapGreater() {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public Integer size(){
        return heapSize;
    }

    public boolean contains(T t){
        return indexMap.containsKey(t);
    }

    // 返回根顶元素
    public T peek(){
        return heap.get(0);
    }

    // 入堆
    public void push(T t){

    }

    // 对堆中index位置的元素进行heapInsert
    private void heapInsert(int index){
        while(heap.get(index).compareTo(heap.get((index-1)/2)) >0 ){
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    // 对堆中index位置的元素进行heapify
    private void heapify(int index){
        // 找到左右孩子中最大的，左孩子是一定存在的
        int left = 2*index;
        // 出口是左孩子下标等于或者大于heapSize
        while( left < heapSize){
            // 找到左右孩子中最大的
            int biggest = left+1 < heapSize && heap.get(left+1).compareTo(heap.get(left)) > 0 ? left+1 : left;
            // 比较子节点和父节点
            if(heap.get(index).compareTo(heap.get(biggest)) > 0){
                break;
            }
        }
    }

    private void swap(int i,int j){
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        // 交换heap中位置
        heap.set(i,t2);
        heap.set(j,t1);
        // 交换indexMap中的位置
        indexMap.put(t1,j);
        indexMap.put(t2,i);
    }
}
