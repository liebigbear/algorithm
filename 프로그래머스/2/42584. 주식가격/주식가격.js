function solution(prices) {
    var answer = new Array(prices.length);
    for(let i = 0; i < prices.length; i++) {
        answer[i] = prices.length-1-i;
    }
    let stack = [];
    let idx = [];
    for(let i = 0; i < prices.length; i++) {
        if(stack.length === 0 || stack[stack.length-1] <= prices[i]) {
            stack.push(prices[i]);
            idx.push(i);
            continue;
        }
        if(stack[stack.length-1] > prices[i]) {
            while(stack.length > 0 && stack[stack.length-1] > prices[i]) {
                let prev = stack.pop();
                let prevIdx = idx.pop();
                answer[prevIdx] = i-prevIdx;
            }
            stack.push(prices[i]);
            idx.push(i);
        }
    }
    return answer;
}