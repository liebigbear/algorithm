import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * 
		 */
		
		/**
		 * 1번째가 kbs1 아니면 3
		 * 2번째가 kbs2 아니면 3
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		//kbs1
		int cursor = 0;
		while(!arr[0].equals("KBS1")) {
			swap(arr, cursor, cursor+1);
			sb.append("3");
			cursor++;
			//바꾼값이 kbs1이라면 다시 위로 올려준다
			if(cursor-1 > 0 && arr[cursor-1].equals("KBS1")) {
				sb.append("2");
				cursor--;
				while(cursor-1 >= 0) {
					swap(arr, cursor, cursor-1);
					sb.append("4");
					cursor--;
				}
			}
		}
		//kbs2
		while(!arr[1].equals("KBS2")) {
			if(cursor == 0) {
				cursor++;
				sb.append("1");
			}
			swap(arr, cursor, cursor+1);
			sb.append("3");
			cursor++;
			//바꾼값이 kbs2이라면 다시 위로 올려준다
			if(cursor-1 > 1 && arr[cursor-1].equals("KBS2")) {
				sb.append("2");
				cursor--;
				while(cursor-1 >= 1) {
					swap(arr, cursor, cursor-1);
					sb.append("4");
					cursor--;
				}
			}
		}
		System.out.println(sb);
	}
	static void swap(String[] arr, int idx1, int idx2) {
		String temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
}
