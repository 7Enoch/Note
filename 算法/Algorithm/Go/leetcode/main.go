package main

type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1.Val == 0 && l2.Val == 0 {
		return &ListNode{0, nil}
	} else if l1.Val == 0 {
		return l2
	} else if l2.Val == 0 {
		return l1
	}

	result := &ListNode{Val: 0, Next: nil}
	//有一个链表next指针不为空
	head := result
	for l1 != nil || l2 != nil {

		//进位
		var Cur int
		//留数
		var BitNum int
		BitNum = 0
		if l1 == nil {
			if Cur != 0 {
				result.Next = &ListNode{l2.Val + Cur, nil}
				Cur = 0
			} else {
				result.Next = &ListNode{l2.Val, nil}
			}

		}
		if l2 == nil {
			if Cur != 0 {
				result.Next = &ListNode{l1.Val + Cur, nil}
				Cur = 0
			} else {
				result.Next = &ListNode{l1.Val, nil}
			}

		}
		if Cur != 0 {
			//当前位大于9就+1
			if l1.Val+l2.Val+Cur > 9 {
				Cur = 1
				BitNum = l1.Val + l2.Val + Cur - 10
				result.Next = &ListNode{BitNum, nil}
			} else {
				BitNum = l1.Val + l2.Val + Cur
				result.Next = &ListNode{BitNum, nil}
			}

		} else {
			//当前位大于9就+1
			if l1.Val+l2.Val > 9 {
				Cur = 1
				BitNum = l1.Val + l2.Val - 10
				result.Next = &ListNode{BitNum, nil}
			} else {
				BitNum = l1.Val + l2.Val
				result.Next = &ListNode{BitNum, nil}
			}
		}
		result = result.Next
		l1 = l1.Next
		l2 = l2.Next
	}
	return head.Next
}
