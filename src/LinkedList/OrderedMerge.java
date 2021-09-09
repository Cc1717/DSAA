package LinkedList;

import java.util.Arrays;
import java.util.List;

public class OrderedMerge {
	public static void main(String[] args) {
		List<Integer> firstValues = Arrays.asList(100, 400, 800, 1500);
		LinkedListNode firstNode = mock(firstValues, firstValues.size() - 1, null);
		List<Integer> secondValues = Arrays.asList(300, 700, 1800, 2100);
		LinkedListNode secondNode = mock(secondValues, secondValues.size() - 1, null);
		merge(firstNode, secondNode);
		System.out.println(firstNode);
	}

	private static LinkedListNode mock(List<Integer> values, int index, LinkedListNode node) {
		if (index < 0) {
			return node;
		}
		LinkedListNode newNode;
		if (node == null) {
			newNode = new LinkedListNode(values.get(index), null);
		} else {
			newNode = new LinkedListNode(values.get(index), node);
		}
		return mock(values, index - 1, newNode);
	}

	private static void merge(LinkedListNode firstNode, LinkedListNode secondNode) {

	}
}

class LinkedListNode {
	private int value;
	private LinkedListNode nextNode;

	public LinkedListNode(int value, LinkedListNode nextNode) {
		this.value = value;
		this.nextNode = nextNode;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setNextNode(LinkedListNode nextNode) {
		this.nextNode = nextNode;
	}

	public int getValue() {
		return value;
	}

	public LinkedListNode getNextNode() {
		return nextNode;
	}

	@Override
	public String toString() {
		if (nextNode == null) {
			return value + "";
		} else {
			return value + " -> " + nextNode.toString();
		}
	}
}