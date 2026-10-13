class Solution {
    int count = 0;
    public ListNode removeNthFromEnd(ListNode head, int n) {
       if(head == null){
        return null;
       }
       head.next = removeNthFromEnd(head.next, n);
       count++;

       if(count == n){
        return head.next;
       }
       return head;
    }
}