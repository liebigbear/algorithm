function solution(players, callings) {
    /*
    해설진이 a 선수를 부르면 a선수가 추월했다
    선수들의 이름이 1등부터 등수 순서대로 주어진다
    해설진이 부른 이름 배열이 주어지고 경주가 끝났을 때 등수를 구해라
    */
    
    //[순위] = 이름 배열
    let rank = [...players];
    // 이름 : 순위
    let map = new Map();
    for(let i = 0; i < players.length; i++) {
        map.set(players[i], i);
    }
    callings.forEach((element) => {
        //현재 순위
        let first = map.get(element);
        let second = Number(first)-1;
        
        const temp = rank[first];
        rank[first] = rank[second];
        rank[second] = temp;
        
        //맵 객체 삭제 후 재설정
        map.delete(rank[first]);
        map.delete(rank[second]);
        map.set(rank[first], first);
        map.set(rank[second], second);
    });
    
    var answer = [...rank];
    return answer;
}