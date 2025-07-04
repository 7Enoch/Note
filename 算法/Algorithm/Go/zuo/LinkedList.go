package main

import (
	"fmt"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

type DoubleListNode struct {
	Val  int
	Last *DoubleListNode
	Next *DoubleListNode
}

func main() {
	head := &ListNode{Val: 1}
	head.Next = &ListNode{Val: 2}
	head.Next.Next = &ListNode{Val: 3}
	head = reverseList(head)
	for head != nil {
		fmt.Println(head.Val)
		head = head.Next
	}
	fmt.Println("=====分隔符=====")
	doubleHead := &DoubleListNode{Val: 1}
	doubleHead.Next = &DoubleListNode{Val: 2}
	doubleHead.Next.Last = doubleHead
	doubleHead.Next.Next = &DoubleListNode{Val: 3}
	doubleHead.Next.Next.Last = doubleHead.Next
	doubleHead = reverseDoubleList(doubleHead)
	for doubleHead != nil {
		fmt.Println(doubleHead.Val)
		doubleHead = doubleHead.Next
	}
}

// 反转单链表
func reverseList(head *ListNode) *ListNode {
	var pre *ListNode
	var next *ListNode

	for head != nil {
		next = head.Next
		head.Next = pre
		pre = head
		head = next
	}
	return pre
}

// 反转双链表
func reverseDoubleList(head *DoubleListNode) *DoubleListNode {
	var pre *DoubleListNode
	var next *DoubleListNode

	for head != nil {
		next = head.Next
		head.Last = next
		head.Next = pre
		pre = head
		head = next
	}
	return pre
}
