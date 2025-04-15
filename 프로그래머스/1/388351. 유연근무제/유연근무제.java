class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        /*
        출근희망시간+10분 안에 출근한 직원의 수
        토요일 일요일은 제외
        */
        for(int i = 0; i < schedules.length; i++) {
            int curDay = startday;
            int cnt = 0;
            for(int j = 0; j < timelogs[i].length; j++) {
                if(isPrize(schedules[i], timelogs[i][j], curDay)) {
                    cnt++;
                }
                curDay++;
            }
            if(cnt == 5) answer++;
        }
        return answer;
    }
    static boolean isPrize(int sc, int time, int day) {
        if(sc % 100 >= 50) {
            sc = (sc / 100 + 1) * 100 + (sc + 10) % 10;
        } else {
            sc += 10;
        }
        if(day % 7 != 0 && day % 7 != 6 && sc >= time) return true;
        return false;
    }
}