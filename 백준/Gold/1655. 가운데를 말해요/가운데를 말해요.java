import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
class Heap {
	int[] arr;
	int index;
	public Heap(int N) {
		arr = new int[N];
		index = 0;
	}
	
	public void add(int n) {
		arr[index] = n;
		addHeapify(index);
		index++;
	}
	
	public int poll() {
		if(index == 0)
			return 0;
		int result = arr[0];
		arr[0] = arr[--index];
		pollHeapify(0);
		return result;
	}
	public int peek() {
		return arr[0];
	}
	public void addHeapify(int idx) {
		int parent = (idx - 1) >> 1; // /2를 하면 음수일때 0으로 구분이 안된다
		if(parent < 0)
			return;
		if(arr[parent] > arr[idx]) {
			swap(parent, idx);
			addHeapify(parent);
		}
	}
	
	public void pollHeapify(int idx) {
		int p = idx;
		int l = (idx << 1) + 1;
		int r = (idx << 1) + 2;
		
		if(l < index && arr[p] > arr[l])
			p = l;
		if(r < index && arr[p] > arr[r])
			p = r;
		
		if(idx != p) {
			swap(p, idx);
			pollHeapify(p);
		}
	}
	public void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public int size() {
		return index;
	}
}
public class Main {
 	public static void main(String arg[]) throws IOException {
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringBuilder sb = new StringBuilder();
 		int N = Integer.parseInt(br.readLine());
 		Heap heap = new Heap(N); //최소힙
 		PriorityQueue<Integer> heapq = new PriorityQueue<>(Collections.reverseOrder());
 		int first = Integer.parseInt(br.readLine());
 		System.out.println(first);
 		heapq.add(first); //처음은 최대힙
 		for(int i = 1; i < N; i++) {
 			//미드값은 항상 최대힙의 루트노드이다.
 			int a = Integer.parseInt(br.readLine());
 			if(heapq.size() != heap.size()) { //사이즈가 같지 않다면 둘의 크기를 맞춰주기 위해 최소힙에 넣음
 				if(heapq.peek() > a) { //여기서 들어오는 수가 미드값보다 작다면
 					heap.add(heapq.poll()); //최대힙의 루트노드를 최소힙으로 옮겨주고
 					heapq.add(a); //최대힙에 새로들어온 값을 넣음
 				}
 				else
 					heap.add(a);
 			}
 			else { //사이즈가 같으면 최대힙에 넣는다
 				if(heap.peek() < a) { //최소힙의 루트노드는 중간값에서 큰값이다 이 값보다 들어오는 수가 크면 둘을 바꿔준다
 					heapq.add(heap.poll()); //최소힙의 루트노드를 최대힙에 넣는다 이 값은 바로 최대힙의 루트노드가 됨
 					heap.add(a); //최소힙에 새로운 값을 넣어줌
 				}
 				else
 					heapq.add(a);
 			}
 			sb.append(heapq.peek()).append('\n');
 		}
 		System.out.println(sb);
 	}
}