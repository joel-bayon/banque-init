package fr.orsys.dao;

import java.util.Collection;
import java.util.List;

public interface Dao<T,K> {
	public K save(T entity);	
	public T load(K primaryKey) ;
	public List<T> LoadAll() ;
	public void delete(T entity) ;
	public void deleteAll(Collection<T> entities) ;
	public T update(T entity)  ;
}
