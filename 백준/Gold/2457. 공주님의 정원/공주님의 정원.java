import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Flower implements Comparable<Flower> {
	int sday;
	int eday;
	Flower(int sday, int eday) {
		this.sday = sday;
		this.eday = eday;
	}
	@Override
	public int compareTo(Flower o) {
		// TODO Auto-generated method stub
		if(this.sday == o.sday) {
			return Integer.compare(o.eday, this.eday);
		}
		return Integer.compare(sday, o.sday);
	}
	@Override
	public String toString() {
		return this.sday+" "+this.eday;
	}
}
public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * N개의 꽃은 같은 해에 피어서 같은 해에 진다
		 * 꽃은 피는날과 지는 날이 정해져 있다
		 * 3월 1일부터 11월 30일 까지 매일 꽃이 한 가지 이상 피어야한다
		 * 정원의 꽃들은 최소한으로 심는다
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Flower> flowers = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ss = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			int es = Integer.parseInt(st.nextToken());
			int ee = Integer.parseInt(st.nextToken());
			flowers.add(new Flower(ss * 100 + se, es * 100 + ee));
		}
		Collections.sort(flowers);
		int start = 301;
		int end = 1201;
		int idx = 0;
		int answer = 0;
		while(start < end) {
			int maxDay = 0;
			boolean find = false;
			for(int i = idx; i < flowers.size(); i++) {
				if(start < flowers.get(i).sday) break;
				if(maxDay > flowers.get(i).eday) continue;
				maxDay = flowers.get(i).eday;
				find = true;
				idx = i;
			}
			if(!find) {
				answer = 0;
				break;
			}
			start = flowers.get(idx).eday;
			answer++;
			idx++;
		}
		System.out.println(answer);
	}
}
