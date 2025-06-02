function solution(array, commands) {
    var answer = new Array(commands.length);
    for(let i = 0; i < commands.length; i++) {
        const start = commands[i][0]-1;
        const end = commands[i][1];
        const arr = array.slice(start,end).sort(
            function(a,b) {
                return a-b;
            }
        );
        answer[i] = arr[commands[i][2]-1];
    }
    return answer;
}