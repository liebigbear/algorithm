import java.util.*;

class Node {
    Node prev;
    Node next;
    boolean remove;
    Node(Node prev, Node next, boolean remove) {
        this.prev = prev;
        this.next = next;
        this.remove = remove;
    }
    Node moveUp(int n) {
        Node node = this;
        for(int i=0; i < n; i++) {
            if(node.prev == null) break;
            node = node.prev;
        }        
        return node;
    }
    Node moveDown(int n) {
        Node node = this;
        for(int i=0; i < n; i++) {
            if(node.next == null) break;
            node = node.next;
        }
        return node;
    }
    Node delNode() {
        remove = true;
        Node prev = this.prev;
        Node next = this.next;
        if(prev != null) prev.next = next;
        if(next != null) {
            next.prev = prev;
            return next;
        }
        return prev;
    }
    void unDo() {
        remove = false;
        Node prev = this.prev;
        Node next = this.next;
        if(prev != null) prev.next = this;
        if(next != null) next.prev = this;
    }
}

class Solution {
    public String solution(int n, int k, String[] cmd) {
        /*
        n개 행을 가진 표와  처음에 선택된 행의 위치 k가 주어진다.
        cmd를 수행한 후 처음 주어진 표와 비교하여 삭제되지 않은 행은 O, 삭제된 행은 X로 표시하여 문자열로 반환
        cmd중 C는 현재 선택된 행을 삭제한 후 바로 아래행 선택 마지막이면 맨 처음 행 선택
        cmd중 Z는 최근 삭제된 행을 원래대로 복구
        */
        String answer = "";
        
        //원본
        Node[] node = new Node[n];
        node[0] = new Node(null, null, false);
        for(int i = 1; i < n; i++) {
            node[i] = new Node(node[i-1], null, false);
            node[i-1].next = node[i];
        }
        //삭제 기록
        Stack<Node> recode = new Stack<>();
        //현재 위치
        Node cur = node[k];

        for(String str : cmd) {
            if(str.charAt(0) == 'C') {
                recode.push(cur);
                cur = cur.delNode();
            } else if(str.charAt(0) == 'Z') {
                Node del = recode.pop();
                del.unDo();
            } else {
                String[] command = str.split(" ");
                int num = Integer.parseInt(command[1]);
                if(command[0].equals("U")) {
                    cur = cur.moveUp(num);
                } else {
                    cur = cur.moveDown(num);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Node no : node) {
            if(no.remove) sb.append("X");
            else sb.append("O");
        }
        return sb.toString();
    }
}