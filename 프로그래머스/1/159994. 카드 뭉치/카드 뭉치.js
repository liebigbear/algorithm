function solution(cards1, cards2, goal) {
    /*
    카드 뭉치에서 순서대로 카드 한장 사용 순서변경x, 카드 넘어가기x
    */
    var answer = '';
    let idx1 = 0;
    let idx2 = 0;
    let isPos = true;
    goal.forEach((element) => {
        if(idx1 < cards1.length && element === cards1[idx1]) {
            idx1++;
        }
        else if(idx2 < cards2.length && element === cards2[idx2]) {
            idx2++;
        } else {
            isPos = false;
        }
    });
    if(isPos) answer = "Yes";
    else answer = "No";
    return answer;
}