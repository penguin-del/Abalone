package utilities;

public class Pair<K, V>
{
	protected K _lhs;
	protected V _rhs;
	
    public Pair(K lhs, V rhs)
    {
    	_lhs = lhs;
    	_rhs = rhs;    	
    }
    
    public K getFirst() { return _lhs; }
    public V getSecond() { return _rhs; }
}
