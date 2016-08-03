package com.djitouw.lists.backend.us;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.djitouw.lists.backend.objects.ListElement;

/**
 * @author Djitouw
 *
 * @param <E> type of the list element (Movie, Book...)
 * @param <D> type of the details of the list element
 */
public interface USList<E extends ListElement, D extends ListElement> {

	/**
	 * @return the list of elements stored
	 */
	public List<E> getList();


	/**
	 * @param id the id of the element
	 * @return the details of one element
	 */
	public D getDetails(@NotNull String id);
}
