package class07.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/*
 * T一定要是非基础类型，有基础类型需求包一层
 */
public class HeapGreater<T> {

	private ArrayList<T> heap;
	private HashMap<T, Integer> indexMap;
	private int heapSize;
	private Comparator<? super T> comp;

	public HeapGreater(Comparator<? super T> c) {
		heap = new ArrayList<>();
		indexMap = new HashMap<>();
		heapSize = 0;
		comp = c;
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public int size() {
		return heapSize;
	}

	public boolean contains(T obj) {
		return indexMap.containsKey(obj);
	}

	public T peek() {
		return heap.get(0);
	}

	public void push(T obj) {
		heap.add(obj);
		indexMap.put(obj, heapSize);
		heapInsert(heapSize++);
	}

	public T pop() {
		T ans = heap.get(0);
		swap(0, heapSize - 1);
		indexMap.remove(ans);
		heap.remove(--heapSize);
		heapify(0);
		return ans;
	}

	public void remove(T obj) {
		// 找到最后一个
		T replace = heap.get(heapSize - 1);
		// 拿到要remove的下标
		int index = indexMap.get(obj);
		// 从map中移除
		indexMap.remove(obj);
		// 从heap中移除
		heap.remove(--heapSize);

		// 如果要移除的对象不是最后一个
		if (obj != replace) {
			// 将index的位置放上最后一个
			heap.set(index, replace);
			// 反向所以表中加入
			indexMap.put(replace, index);
			// 重置
			resign(replace);
		}
	}

	public void resign(T obj) {
		heapInsert(indexMap.get(obj));
		heapify(indexMap.get(obj));
	}

	// 请返回堆上的所有元素
	public List<T> getAllElements() {
		List<T> ans = new ArrayList<>();
		for (T c : heap) {
			ans.add(c);
		}
		return ans;
	}

	private void heapInsert(int index) {
		while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
			swap(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	private void heapify(int index) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			// 右比左小，则返回右，所以是小根堆
			int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
			best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
			if (best == index) {
				break;
			}
			swap(best, index);
			index = best;
			left = index * 2 + 1;
		}
	}

	private void swap(int i, int j) {
		T o1 = heap.get(i);
		T o2 = heap.get(j);
		heap.set(i, o2);
		heap.set(j, o1);
		indexMap.put(o2, i);
		indexMap.put(o1, j);
	}

}
