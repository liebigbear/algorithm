const input = require('fs')
    .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./input.txt")
    .toString()
    .trim()
    .split(" ");
var a = parseInt(input[0]);
var b = parseInt(input[1]);
var c = parseInt(input[2]);
console.log(a + b + c);