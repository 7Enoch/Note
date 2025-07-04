package main

import (
	"fmt"
)

//返回两个无环链表相交的第一个节点

/*
p . *p , &p 三个符号
p是一个指针变量的名字，表示此指针变量指向的内存地址.
*p表示此指针指向的内存地址中存放的内容.
&是取地址运算符，&p就是取指针p的地址
*/
func main() {
	//创建两个链表 他们有相交节点
	h1 := createList([]int{1, 3, 5, 7, 9, 11, 12, 13})
	h2 := createList([]int{2, 4, 6, 8, 10, 11, 12, 13})
	mergeList(h1, h2)
	printListAddress(h1)
	printListAddress(h2)
	// result := getIntersectionNode(&h1, &h2)
	// fmt.Print(result)

}

// 节点结构
type ListNode struct {
	val  int
	next *ListNode
}

// 解决函数
func getIntersectionNode(h1, h2 *ListNode) *ListNode {
	//判断是否为空 如果两个节点有一个为空就直接返回
	if h1 == nil || h2 == nil {
		return nil
	}
	//变量a从h1出发
	//变量b从h2出发
	var a *ListNode = h1
	b := h2
	var diff int = 0
	//将a跳转到1链表末尾
	for a.next != nil {
		a = a.next
		diff++
	}

	//将b跳转到2链表结尾
	for b.next != nil {
		b = b.next
		diff--
	}
	//判断a链表的最后一个节点跟b节点的最后一个节点是否相同
	if a != b {
		//不相同则不相交
		return nil
	}

	//判断diff的长度 将让a指向长链表
	if diff >= 0 {
		//如果长度大于0 说明h1链表比h2链表长
		a = h1
		b = h2
	} else {
		//如果长度小于0 说明h1链表比h2链表短
		a = h2
		b = h1
		//取diff绝对值
		diff = -diff
	}
	//让a链表先走diff步
	for diff != 0 {
		a = a.next
		diff--
	}
	//a跟b未相交就一直走下去
	for a != b {
		a = a.next
		b = b.next
	}
	//返回相交的那个节点
	return a
}

// 创建链表
func createList(arr []int) *ListNode {
	//数组为空就返回nil
	if arr == nil {
		return nil
	}
	//将数组的值当作节点值创建链表
	//创建头节点
	var head ListNode = ListNode{val: arr[0]}
	var len int = len(arr)
	var cur *ListNode = &head
	//遍历数组
	var i int
	for i = 1; i < len; i++ {
		cur.next = &ListNode{val: arr[i]}
		cur = cur.next
	}

	return &head

}

// 将值相同的链表节点合并成一个节点
func mergeList(h1, h2 *ListNode) {
	//如果有一个为空就返回
	if h1 == nil || h2 == nil {
		return
	}
	temp := *h2
	//如果h1的下一个节点不为空
	for h1 != nil {
		for h2 != nil {
			//如果链表值相等
			if h2.val == h1.val {
				//h2就指向h1
				*h1 = *h2
				*h2 = *h1
				fmt.Print("内存地址", &h1.val, &h2.val)
				fmt.Println("值", h1.val, h2.val)
			}
			//h2向下指
			h2 = h2.next
		}
		//还原
		h2 = &temp
		//h1向下指
		h1 = h1.next
	}

}

// 打印链表值
func printListValue(list *ListNode) {
	if list == nil {
		return
	}
	for list != nil {
		fmt.Print(list.val, " ")
		list = list.next
	}
	fmt.Println()
}

// 打印链表地址
func printListAddress(list *ListNode) {
	if list == nil {
		return
	}
	for list != nil {
		fmt.Println(&list.val)
		list = list.next
	}
	fmt.Println()
}
