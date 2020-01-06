class Stack {
	class Node {
		int value;
		Node next;
	}

	Node top;

	Stack() {
		this.top = null;
	}

	public void push(int x) {
		Node temp = new Node();
		if (temp == null) {
			return;
		}
		temp.value = x;
		temp.next = top;
		top = temp;
	}

	public boolean isEmpty() {

		return top == null;

	}

	public int top() {

		if (!isEmpty()) {
			return top.value;
		} else {
			System.out.println("Stack be empty my nigga");
			return -1;
		}
	}
	
	public int pop() {
		int popped = top.value;
		top = top.next;
		
		return popped;
	}
	
	
}