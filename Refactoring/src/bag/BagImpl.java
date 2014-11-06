package bag;

final class BagImpl implements Bag {
	Object o;

	public Object get()
     {
        return o;
     }

	public void set(Object o)
     {
        this.o = o;
     }
}