package com.example.demo;

class NodeOld {
	int data;
	NodeOld left, right;

	NodeOld(int data) {
		this.data = data;
		left = right = null;
	}

	public NodeOld find(int v) {
		NodeOld current = NodeOld.this;
		
		while (current != null) {
		
			if (current.data == v) {
				return current;
			}
			current = v > current.data ? current.right : current.left;
		}
		
		return null;
	}

	public NodeOld find1(int v) {
		NodeOld current = this;

		while (current != null && current.data != v) {
			current = v < current.data ? current.left : current.right;
		}
		return current;
	}

	public static void main(String args[]) {
		NodeOld root = new NodeOld(9);

		root.left = new NodeOld(7);
		root.left.left = new NodeOld(5);
		root.left.left.left = new NodeOld(2);
		root.left.right = new NodeOld(6);
		root.left.right = new NodeOld(8);
		
		root.right = new NodeOld(13);
		root.right.right = new NodeOld(17);
		root.right.right.left = new NodeOld(16);

		int key = 8;

		NodeOld NodeOld = root.find(key);

		System.out.println("NodeOld" + NodeOld.data);

	}
}
