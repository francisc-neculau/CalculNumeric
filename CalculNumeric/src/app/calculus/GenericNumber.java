package app.calculus;

public abstract class GenericNumber<T extends Number>
{
	private T number;

	public GenericNumber(T number)
	{
		this.number = number;
	}

	public abstract GenericNumber<T> add(GenericNumber<T> otherNumber);
	public abstract GenericNumber<T> subtract(GenericNumber<T> otherNumber);
	public abstract GenericNumber<T> multiply(GenericNumber<T> otherNumber);
	public abstract GenericNumber<T> divide(GenericNumber<T> otherNumber);
	
	public abstract boolean compareTo(GenericNumber<T> anotherNumber);
	
	@Override
	public String toString()
	{
		return number.toString();
	}

}
