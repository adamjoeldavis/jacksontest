package jacksontest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataList<D extends DataObject> implements List<D>
{
	List<D> backingList_;
	List<D> originalBackingList_;
	
	@JsonCreator
	private DataList(List<D> completeList)
	{
		originalBackingList_ = completeList;
		backingList_ = new ArrayList<>(originalBackingList_);
		
		backingList_.removeIf(DataObject::isDeleted);
	}
	
	public DataList()
	{
		originalBackingList_ = new ArrayList<>();
		backingList_ = new ArrayList<>();
	}
	
	@Override
	public int size()
	{
		return backingList_.size();
	}

	@Override
	public boolean isEmpty()
	{
		return backingList_.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return backingList_.contains(o);
	}

	@Override
	public Iterator<D> iterator()
	{
		return backingList_.iterator();
	}

	@Override
	public Object[] toArray()
	{
		return backingList_.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return backingList_.toArray(a);
	}

	@Override
	public boolean add(D e)
	{
		return backingList_.add(e);
	}

	@Override
	public boolean remove(Object o)
	{
		return backingList_.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return backingList_.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends D> c)
	{
		return backingList_.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends D> c)
	{
		return backingList_.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		return backingList_.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return backingList_.retainAll(c);
	}

	@Override
	public void clear()
	{
		backingList_.clear();
	}

	@Override
	public D get(int index)
	{
		return backingList_.get(index);
	}

	@Override
	public D set(int index, D element)
	{
		return backingList_.set(index, element);
	}

	@Override
	public void add(int index, D element)
	{
		backingList_.add(index, element);
	}

	@Override
	public D remove(int index)
	{
		return backingList_.remove(index);
	}

	@Override
	public int indexOf(Object o)
	{
		return backingList_.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return backingList_.lastIndexOf(o);
	}

	@Override
	public ListIterator<D> listIterator()
	{
		return backingList_.listIterator();
	}

	@Override
	public ListIterator<D> listIterator(int index)
	{
		return backingList_.listIterator(index);
	}

	@Override
	public List<D> subList(int fromIndex, int toIndex)
	{
		return backingList_.subList(fromIndex, toIndex);
	}
	
	public String toString()
	{
		return "backing list: " + backingList_.toString() + "\noriginal list: " + originalBackingList_.toString();
	}
	
	public static void main(String...args) throws Exception
	{
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(DataList.class, Person.class);
		
		DataList<Person> list = new DataList<>();
		Person p1 = new Person();
		p1.setValue("Value 1");
		list.add(p1);
		Person p2 = new Person();
		p2.setValue("Value 2");
		p2.setDeleted(true);
		list.add(p2);
		
		String serialized = objectMapper.writeValueAsString(list);
		System.out.println(serialized);
		
		DataList<Person> deserialized = objectMapper.readValue(serialized, type);
		System.out.println(deserialized);
	}

}
