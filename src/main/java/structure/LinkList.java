package structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Linked list class, written in Java.
 * This is <b>not</b> intended to be a usable List, and it is probably
 * not going to be optimal in terms of performance; it is in part
 * here to remind you how much work the existing List implementations do.
 * <br/>
 * For a production-ready version, consider subclassing AbstractSequentialList.
 * @deprecated	Supplanted by LinkedList
 * @author	Ian Darwin
 */
@Deprecated
// BEGIN main
public class LinkList<T> implements List<T> {

	/* A TNode stores one node or item in a Linked List */
	private static class TNode<T> {
		TNode<T> next;
		T data;
		TNode(T o) {
			data = o;
			next = null;
		}
		@Override
		public String toString() {
			return String.format("TNode: data='%s', next='%d'", data, 
					next == null ? 0 : next.hashCode());
		}
	}

	private boolean DIAGNOSTIC = true;
	
	/** The root or first TNode in the list. */
	protected TNode<T> first;
	/** The last TNode in the list */
	protected TNode<T> last;

	/** Construct a LinkList: initialize the first and last nodes */
	public LinkList() {
		clear();
	}

	/** Construct a LinkList given another Collection.
	 * This method is recommended by the general contract of List.
	 */
	public LinkList(Collection<T> c) {
		this();
		addAll(c);
	}

	/** Set the List (back) to its initial state.
	 * Any references held will be discarded.
	 */
	public void clear() {
		first = new TNode<T>(null);
		last = first;
	}

	/** Add one object to the end of the list. Update the "next"
	 * reference in the previous end, to refer to the new node.
	 * Update "last" to refer to the new node. 
	 */
	public boolean add(T o) {
		last.next = new TNode<T>(o);
		last = last.next;
		return true;
	}

	public void add(int where, T o) {
		TNode<T> t = first;
		for (int i=0; i<=where; i++) {
			t = t.next;
			if (t == null) {
				throw new IndexOutOfBoundsException(
					"'add(n,T) went off end of list");
			}
			if (DIAGNOSTIC) {
				System.out.printf("add(int,T): i = %d, t = %s%n", i, t);
			}
		}
		TNode<T> t2 = t;
		t.next = new TNode<T>(o);
		t.next.next = t2;
		if (DIAGNOSTIC) {
			System.out.printf("add(int,T): t=%s\n", t);
			dump();
		}
	}

	public int size() {
		TNode<T> t = first;
		int i;
		for (i=0; ; i++) {
			if (t == null)
				break;
			t = t.next;
		}
		return i - 1;	// subtract one for mandatory head node.
	}

	public boolean isEmpty() {
		return first == last;
	}

	public T get(int where) {
		TNode<T> t = first;
		int i=0; 
		// If we get to the end of list before 'where', error out
		while (i<=where) {
			i++;
			if ((t = t.next) == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		return t.data;
	}

	public T set(int i, T o) {
		return null;
	}
	
	public boolean contains(Object o) {
			TNode<T> t = first;
		while ((t = t.next) != null) {
			if (t.data.equals(o)) {
				return true;
			}
		}
		return false;
	}
	public boolean addAll(Collection<? extends T> c) {
		c.forEach(o -> add((T) o));
		return false;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			TNode<T> t = first.next;
			public boolean hasNext() {
				return t != last;
			}
			@SuppressWarnings("unchecked")
			public T next() {
				if (t == last)
					throw new IndexOutOfBoundsException();
				return (T) (t = t.next);
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		};
	}

	@SuppressWarnings("unchecked")
	public T[] toArray(Object[] data) {
		// First is an empty anchor, start at its next
		TNode<T> p = first.next;
		for (int i = 0; p != null && i < data.length; i++) {
			data[i] = p.data;
			p = p.next;
		}
		return (T[]) data;
	}

	public Object[] toArray() {
		Object[] data = new Object[size()];
		return toArray(data);
	}
	// END main

	private void dump() {
		if (!DIAGNOSTIC) {
			return;
		}
		TNode<T> p = first;
		do {
			if (p == p.next) {
				System.err.println("SELF-POINTER AT " + p);
				return;
			}
			System.err.printf("cur=%d data=%s next=%d\n", p.hashCode(), p.data, p.next.hashCode());
			p = p.next;
		} while (p.next != null);
	}

	// THE FOLLOWING METHODS ARE NOT YET IMPLEMENTED!

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public T remove(int i) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	public boolean addAll(int i, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("listIterator");
	}

	public ListIterator<T> listIterator(int where) {
		throw new UnsupportedOperationException();
	}

	public List<T> subList(int sub1, int sub2) {
		throw new UnsupportedOperationException();
	}
}
