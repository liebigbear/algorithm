function solution(park, routes) {
    var answer = [];
    
    const map = [park.length][park[0].length];
    let start;
    for(let i = 0; i < park.length; i++) {
        for(let j = 0; j < park[0].length; j++) {
            if(park[i][j] === 'S') {
                start = [i,j];
            }
        }
    }
    const dir = {
        N: [-1,0],
        S: [1,0],
        W: [0,-1],
        E: [0,1],
    }
    routes.forEach((element) => {
        let pl = 0;
        const maxPl = Number(element[2]);
        let tx = start[0];
        let ty = start[1];
        while(pl < maxPl) {
            tx += dir[element[0]][0];
            ty += dir[element[0]][1];
            if(tx < 0 || tx >= park.length || ty < 0 || ty >= park[0].length || park[tx][ty] === 'X') break;
            pl++;
        }
        if(pl === maxPl) start = [tx, ty];
    });
    
    answer[0] = start[0];
    answer[1] = start[1];
    return answer;
}