function solution(name, yearning, photo) {
    /*
    그리움 점수를 모두 더하면 추억점수
    추억점수를 구해라
    */
    var answer = [];
    let map = new Map();
    let idx = 0;
    name.forEach((element) => map.set(element, yearning[idx++]));
    for(let i = 0; i < photo.length; i++) {
        let sum = 0;
        photo[i].forEach((element) => {
            if(map.has(element)) {
                sum += map.get(element);
            }
        });
        answer.push(sum);
    }
    return answer;
    
}