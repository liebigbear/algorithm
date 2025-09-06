import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Counsel {
	int time;
	int earn;
	Counsel(int time, int earn) {
		this.time = time;
		this.earn = earn;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * N일 동안 최대한 많은 상담을 잡아놓았다
		 * 상담 완료 시간 T와 받을 수 있는 금액 P
		 * 상담을 적절히 했을 때 얻을 수 있는 금액
		 */
		
		/**
		 * i번째 상담을 하는 경우와 안하는 경우를
		 * 완전탐색
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Counsel> cnsl = new ArrayList<>();
		//빈 값
		cnsl.add(new Counsel(0,0));
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cnsl.add(new Counsel(a,b));
		}
		
		int idx = 0;
		
		LinkedList<Counsel> q = new LinkedList<>();
		q.add(new Counsel(1, 0));
		int maxEarn = 0;
		
		while(!q.isEmpty()) {
			Counsel cur = q.poll();
			//퇴사한 경우 돈 계산
			if(cur.time >= N+1) {
				maxEarn = Math.max(cur.earn, maxEarn);
				continue;
			}
			//현재 상담 일정
			Counsel schdl = cnsl.get(cur.time);
			
			//상담을 하는 경우 현재일자 + 상담일자 <= 퇴사날
			if(cur.time + schdl.time <= N+1) {
				q.add(new Counsel(cur.time + schdl.time, cur.earn + schdl.earn));
			}
			//상담을 무시하는 경우 다음날로 넘어간다
			q.add(new Counsel(cur.time + 1, cur.earn));
		}
		
		System.out.println(maxEarn);
	}
}
