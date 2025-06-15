function solution(n, m, section) {
    /*
    1미터 길이의 구역 n개로 나누어진 총 n미터 2차원 벽이 있다
    벽에 m미터 길이의 롤러로 페인트를 칠하려 한다.
    벽에서 롤러가 떼어질 때 한 번 칠했다고 정의
    다시 칠해질 구역이 정해질 때 페인트칠 하는 횟수를 최소화
    */
    var answer = 0;
    let start = 0;
    section.forEach((element) => {
         if(start < element) {
             answer++;
             start = Number(element) + m - 1;
         }
    });
    return answer;
}