/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 import java.util.PriorityQueue;
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
        (a,b) -> Integer.compare(a.val, b.val)
        );

        for(ListNode node: lists){
            if(node != null){
                pq.offer(node);
            }
        }
        ListNode ans = new ListNode(0);
        ListNode tail = ans;

        while(!pq.isEmpty()){
            ListNode minNode = pq.poll();
            tail.next =  minNode;
            tail = tail.next;

            if(minNode.next != null){
                pq.offer(minNode.next);
            }
        }
        return ans.next;

        
    }
}