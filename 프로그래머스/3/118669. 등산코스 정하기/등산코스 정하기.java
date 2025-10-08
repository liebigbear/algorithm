import java.util.*;

class Node implements Comparable<Node> {
    int to;
    int weight;
    Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
    public int compareTo(Node n) {
        return Integer.compare(this.weight, n.weight);
    }
}
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        /*
        노드 1부터 ~ n까지 가중치가 있는 양방향 그래프가 있다
        쉼터, 산봉우리 노드에선 휴식을 취할 수 있으며 코스에 한번만 포함되어야 한다
        intensity는 해당 등산코스의 휴식없이 이동해야하는 가상 긴 시간이다
        intensity가 최소가 되도록 등산코스를 정해라
        */
        
        /**
        다익스트라
        출발노드를 지정한 뒤 도착노드에 도착할때까지의 intensity를 저장
        */
        
        Arrays.sort(summits);
        int[] answer = new int[2];
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < paths.length; i++) {
            graph.get(paths[i][0]).add(new Node(paths[i][1], paths[i][2]));
            graph.get(paths[i][1]).add(new Node(paths[i][0], paths[i][2]));
        }
        
        int summitNo = -1;
        int intensity = 10_000_001;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        //가중치의 합이 아닌 최대값
        int[] maxWeight = new int[n+1];
        Arrays.fill(maxWeight, intensity);
        for(int start : gates) {
            pq.add(new Node(start, 0));
            maxWeight[start] = 0;
        }
        //산봉우리 저장set
        HashSet<Integer> set = new HashSet<>();
        for(int i : summits) set.add(i);
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            //산봉우리면 값 저장 후 종료
            if(set.contains(cur.to)) continue;
            //현재 최대 가중치보다 더 낮은 가중치로 현재 노드에 도달할 수 있다면 종료
            if(cur.weight > maxWeight[cur.to]) continue;
            for(Node next : graph.get(cur.to)) {
                //현재 최대 가중치와 다음노드로 가는 가중치 중 더 큰 것
                int max = Math.max(maxWeight[cur.to], next.weight);
                //처음 탐색
                if(maxWeight[next.to] == 10_000_001) {
                    maxWeight[next.to] = max;
                    pq.add(new Node(next.to, max));
                //더 작은 값으로 갱신된다면
                } else if(maxWeight[next.to] > max) {
                    maxWeight[next.to] = max;
                    pq.add(new Node(next.to, max));
                }
            }
        }
        answer[0] = -1;
        answer[1] = 1000000001;
        for(int i = 0; i < summits.length; i++) {
            if(maxWeight[summits[i]] < answer[1]) {
                answer[0] = summits[i];
                answer[1] = maxWeight[summits[i]];
            }
        }
        
        return answer;
    }
}