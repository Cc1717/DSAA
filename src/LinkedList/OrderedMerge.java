package LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderedMerge {
	public static void main(String[] args) {
		List<Integer> firstValues = Arrays.asList(100, 400, 800, 1500);
		LinkedListNode firstNode = mock(firstValues, firstValues.size() - 1, null);
		List<Integer> secondValues = Arrays.asList(50, 800, 1800, 2100);
		LinkedListNode secondNode = mock(secondValues, secondValues.size() - 1, null);
		//System.out.println(mergeByRebuild(firstNode, secondNode));
		System.out.println(mergeBySimple(firstNode, secondNode));
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

	private static LinkedListNode mergeByRebuild(LinkedListNode firstNode, LinkedListNode secondNode) {
		List<Integer> values = new ArrayList<>();
		while (firstNode != null) {
			values.add(firstNode.getValue());
			firstNode = firstNode.getNextNode();
		}
		while (secondNode != null) {
			values.add(secondNode.getValue());
			secondNode = secondNode.getNextNode();
		}
		Collections.sort(values);
		return mock(values, values.size() - 1, null);
	}

	private static LinkedListNode mergeBySimple(LinkedListNode firstNode, LinkedListNode secondNode) {
		LinkedListNode result = null;
		if (firstNode.getValue() < secondNode.getValue()) {
			result = firstNode;
			doublePointer(firstNode, secondNode, 1);
		} else {
			result = secondNode;
			doublePointer(firstNode, secondNode, 2);
		}
		return result;
	}

	private static void doublePointer(LinkedListNode firstNode, LinkedListNode secondNode, int model) {
		LinkedListNode tempNode = null;
		switch (model) {
			case 1:
				while (firstNode.getValue() <= secondNode.getValue()) {
					tempNode = firstNode;
					firstNode = firstNode.getNextNode();
					if (firstNode == null) {
						tempNode.setNextNode(secondNode);
						return;
					}
				}
				tempNode.setNextNode(secondNode);
				doublePointer(firstNode, secondNode, 2);
				return;
			case 2:
				while (firstNode.getValue() >= secondNode.getValue()) {
					tempNode = secondNode;
					secondNode = secondNode.getNextNode();
					if (secondNode == null) {
						tempNode.setNextNode(firstNode);
						return;
					}
				}
				tempNode.setNextNode(firstNode);
				doublePointer(firstNode, secondNode, 1);
		}
	}
}

class LinkedListNode {
	private final int value;
	private LinkedListNode nextNode;

	public LinkedListNode(int value, LinkedListNode nextNode) {
		this.value = value;
		this.nextNode = nextNode;
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