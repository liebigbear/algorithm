import java.util.*;
class Stage implements Comparable<Stage> {
    int idx;
    double fail;
    Stage(int idx, double fail) {
        this.idx = idx;
        this.fail = fail;
    }
    public int compareTo(Stage s) {
        if(s.fail == fail)
            return Integer.compare(idx, s.idx);
        return Double.compare(s.fail, fail);
    }
}
class Solution {
    public int[] solution(int N, int[] stages) {
        /*
        스테이지 도달했으나 클리어하지 못한 수 / 스테이지에 도달한 플레이어 수 = 실패율
        실패율이 높은 스테이지부터 내림차순으로 스테이지 번호가 담긴 배열을 구해라
        */
        int[] cnt = new int[200_001];
        for(int i = 0; i < stages.length; i++) {
            cnt[stages[i]]++;
        }
        int totalCnt = stages.length;
        PriorityQueue<Stage> pq = new PriorityQueue<>();
        for(int i = 1; i < N + 1; i++) {
            if(cnt[i] == 0) {
                pq.add(new Stage(i,0.0));
            } else {
                double fail = cnt[i];
                fail /= totalCnt;
                totalCnt -= cnt[i];
                pq.add(new Stage(i,fail));
            }
        }
        
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) answer[idx++] = pq.poll().idx;
        return answer;
    }
}