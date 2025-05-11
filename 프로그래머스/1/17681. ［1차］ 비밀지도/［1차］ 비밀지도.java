class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        /*
        한 변의 길이가 최대 16인 정사각형 배열이 주어진다.
        각 칸의 값은 ""(0)과 #(1) 종류로 이루어져 있다.
        각 행을 이진수로 변환했을 때 두 arr이 모두 0인 곳만 공백이다.
        */
        String[] answer = new String[n];
        for(int i = 0; i < arr1.length; i++) {
            String tmp = "";
            int[] row1 = new int[n];
            int[] row2 = new int[n];
            int idx = n-1;
            while(arr1[i] > 0) {
                row1[idx--] = arr1[i] % 2;
                arr1[i] /= 2;
            }
            idx = n-1;
            while(arr2[i] > 0) {
                row2[idx--] = arr2[i] % 2;
                arr2[i] /= 2;
            }
            for(int j = 0; j < n; j++) {
                if(row1[j] == 0 && row1[j] == row2[j])
                    tmp += " ";
                else
                    tmp += "#";
            }
            answer[i] = tmp;
        }
        return answer;
    }
}