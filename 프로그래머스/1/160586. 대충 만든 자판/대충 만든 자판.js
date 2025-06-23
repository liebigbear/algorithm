function solution(keymap, targets) {
    /*
    키의 개수 1~100까지 있는 휴대폰 자판이 있다.
    같은 문자가 자판 전체에 여러 번 할당된 경우도 있다. 같은 문자가 여러 번 할당된 경우도 있다
    특정 문자열을 작성할 때 키를 최소 몇번 눌러야 하는가
    */
    var answer = new Array(targets.length);
    let alphabet = new Array(26).fill(Infinity);

    for(let i = 0; i < keymap.length; i++) {
        for(let j = 0; j < keymap[i].length; j++) {
            const code = keymap[i][j].charCodeAt()-65;
            alphabet[code] = Math.min(alphabet[code], j+1);
        }
    }
    for(let i = 0; i < targets.length; i++) {
        let sum = 0;
        for(let j = 0; j < targets[i].length; j++) {
            const code = targets[i][j].charCodeAt()-65;
            let tmp =  Number(alphabet[code]);
            if(tmp === Infinity) {
                sum = -1;
                break;
            }
            sum += tmp;
        }
        answer[i] = sum;
    }
    return answer;
}