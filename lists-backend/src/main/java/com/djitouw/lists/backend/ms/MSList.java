package com.djitouw.lists.backend.ms;

import java.util.List;

import com.djitouw.lists.backend.objects.ListElement;

/**
 * @author Djitouw
 *
 * @param <E> type of the list element (Movie, Book...)
 * @param <D> type of the details of the list element
 */
public interface MSList<E extends ListElement, D> {

	/**
	 * @param username the username
	 * @return the list of elements stored
	 */
	public List<E> getList(String username);


	/**
	 * @param id the id of the element
	 * @return the details of one element
	 */
	public D getDetails(String id);
}
