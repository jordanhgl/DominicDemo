package com.dominic.jtestlib.models;

import com.dominic.jtestlib.utils.Log;

public class LinkedListTest {

    public LinkNode reverseLinkList(LinkNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkNode reverseHead = reverseLinkList(head.next);
        Log.d("head = "+head.value+" & reversehead is " + reverseHead.value);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }


    public static void main(String[] args) {
        LinkNode node3 = new LinkNode(3, new LinkNode(4, null));
        LinkNode node2 = new LinkNode(2, node3);
        LinkNode node1 = new LinkNode(1, node2);

        LinkedListTest ins = new LinkedListTest();
        LinkNode retNode = ins.reverseLinkList(node1);
        Log.dLink(retNode);
    }
}
