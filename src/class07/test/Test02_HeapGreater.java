package class07.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个泛型里面不能是基本类型，如果是基本类型，则要求堆中不能有重复元素
 *
 * 相比于普通的堆，会多一个反向索引表
 */
public class Test02_HeapGreater<T extends Comparable<T>> {

    private ArrayList<T> heap; // list是堆的本质

    private HashMap<T,Integer> indexMap; // 反向索引表
    private int heapSize; // 堆大小，这个其实就是和heap.size()一样

    public Test02_HeapGreater() {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
    }

    // 公有方法
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

    public void push(T t){
        heap.add(t);
        indexMap.put(t,heapSize);
        heapInsert(heapSize++);
    }

    public T pop(){
        T root = heap.get(0);
        swap(heapSize-1,0);
        indexMap.remove(root);
        heap.remove(--heapSize);
        heapify(0);
        return root;
    }

    /**
     * 可以继续优化，也就是说看是否要加这个resign
     * 如果移除的是最后一个元素，那么就是不需要resign的
     *
     * 我觉的我实现的比左神好
     */
    public void remove(T t){
        // 拿到要移除对象的下标
        Integer tIndex = indexMap.get(t);
        // 和队尾交换
        swap(heapSize-1,tIndex);
        // 从堆中移除
        heap.remove(heapSize--);
        // 从索引表中移除
        indexMap.remove(t);

        if(tIndex != heapSize){
            // 重置
            resign(tIndex);
        }
    }

    // 私有方法

    /**
     * heapify方法需要一个堆中的一个下标，以及堆的大小
     * 在下标的这个元素需要往下沉到其正确的位置上
     * 循环的终点是左孩子的下标已经超出的堆大小
     */
    private void heapify(int index){
        // 拿到左孩子的下标
        int left = 2* index +1;
        // 循环下沉
        while (left < heapSize){
            // 左右孩子中最小
            int min = left+1 < heapSize && heap.get(left+1).compareTo(heap.get(left)) < 0 ? left+1 : left;
            // 找到三者中最小的
            min = heap.get(min).compareTo(heap.get(index)) < 0 ? min : index;
            // 父节点一定要比最小的子节点还要小，才不会继续下沉，否则就和最小的交换
            if(min == index){
                break;
            }else{
                // 这一步交换的时候，其实也把索引表更新
                swap(min,index);
                index = min;
                left = 2*index+1;
            }
        }
    }

    /**
     * heapInsert方法需要一个下标，不需要堆的大小
     * 在下标的这个元素需要不断往上浮
     * 无论是heapInsert还是heapify，传的这个index实际上是要处理的元素
     *
     */
    private void heapInsert(int index){
        // 拿到其父节点的下标，小的往上浮
        int parent = (index-1)/2;
        while(heap.get(index).compareTo(heap.get(parent)) < 0){
            swap(index,parent);
            index = parent;
            parent = (index-1)/2;
        }
    }

    private void resign(int index){
        heapify(index);
        heapInsert(index);
    }
    /**
     * 交换堆中的两个元素
     * 交换后，要同时更新反向索引表的内容
     * 反向索引表：元素 -> 下标
     *
     */
    private void swap(int h1,int h2){
        // 交换再堆中的位置
        T t1 = heap.get(h1);
        T t2 = heap.get(h2);
        heap.set(h1,t2);
        heap.set(h2,t1);
        // 更新反向索引表
        indexMap.put(t1,h2);
        indexMap.put(t2,h1);
    }


}
