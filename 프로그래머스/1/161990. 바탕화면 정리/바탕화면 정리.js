function solution(wallpaper) {
    /*
    격자에 #와 .이 주어진다
    #이 모두 포함되는 최소한의 사각형을 구해라
    */
    var answer = [];
    let minX = 51, minY =51, maxX = -1, maxY = -1;
    for(let i =0; i < wallpaper.length; i++) {
        for(let j = 0; j < wallpaper[i].length; j++) {
            if(wallpaper[i][j] === '#') {
                minX = Math.min(minX, i);
                minY = Math.min(minY, j);
                maxX = Math.max(maxX, i);
                maxY = Math.max(maxY, j);
            }
        }
    }
    answer[0] = minX;
    answer[1] = minY;
    answer[2] = maxX+1;
    answer[3] = maxY+1;
    return answer;
}