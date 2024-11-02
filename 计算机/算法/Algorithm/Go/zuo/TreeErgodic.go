package main

import "fmt"

type TreeNode struct {
	Value int
	Left  *TreeNode
	Right *TreeNode
}

//递归遍历树
func main() {
	treeNode := &TreeNode{Value: 1}
	treeNodeLeft1 := &TreeNode{Value: 2}
	treeNodeRight1 := &TreeNode{Value: 3}
	treeNodeLeft2 := &TreeNode{Value: 4}
	treeNodeRight2 := &TreeNode{Value: 5}
	treeNodeLeft3 := &TreeNode{Value: 6}
	treeNodeRight3 := &TreeNode{Value: 7}

	treeNode.Left = treeNodeLeft1
	treeNode.Right = treeNodeRight1
	treeNodeLeft1.Left = treeNodeLeft2
	treeNodeLeft1.Right = treeNodeRight2
	treeNodeRight1.Left = treeNodeLeft3
	treeNodeRight1.Right = treeNodeRight3

	fmt.Println("---先序遍历---")
	preOrder(treeNode)
	fmt.Println("---中序遍历---")
	inOrder(treeNode)
	fmt.Println("---后序遍历---")
	posOrder(treeNode)
}

// 先序遍历
func preOrder(head *TreeNode) {
	if head == nil {
		return
	}
	fmt.Println(head.Value)
	preOrder(head.Left)
	preOrder(head.Right)
}

// 中序遍历
func inOrder(head *TreeNode) {
	if head == nil {
		return
	}

	inOrder(head.Left)
	fmt.Println(head.Value)
	inOrder(head.Right)
}

// 后序遍历
func posOrder(head *TreeNode) {
	if head == nil {
		return
	}

	posOrder(head.Left)
	posOrder(head.Right)
	fmt.Println(head.Value)
}
