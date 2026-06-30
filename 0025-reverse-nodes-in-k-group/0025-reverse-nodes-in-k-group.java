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
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        // Dummy node to simplify handling the new head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prevGroupEnd = dummy;
        ListNode curr = head;
        
        while (curr != null) {
            // Check if there are at least k nodes left to reverse
            ListNode groupEnd = prevGroupEnd;
            for (int i = 0; i < k; i++) {
                groupEnd = groupEnd.next;
                if (groupEnd == null) {
                    // Less than k nodes left, leave them as they are
                    return dummy.next;
                }
            }
            
            // Keep track of the next group's starting node
            ListNode nextGroupStart = groupEnd.next;
            
            // Reverse the current group of k nodes
            ListNode prev = nextGroupStart;
            ListNode currNode = curr;
            while (currNode != nextGroupStart) {
                ListNode nextNode = currNode.next;
                currNode.next = prev;
                prev = currNode;
                currNode = nextNode;
            }
            
            // Connect the previous group's end to the new head of this reversed group
            ListNode temp = prevGroupEnd.next; // This was the old group start, now it's the end
            prevGroupEnd.next = groupEnd;
            prevGroupEnd = temp;
            
            // Move to the next group
            curr = nextGroupStart;
        }
        
        return dummy.next;
    }
}