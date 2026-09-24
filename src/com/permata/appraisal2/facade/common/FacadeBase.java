package com.permata.appraisal2.facade.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class FacadeBase<W, E> {

	protected Logger logger = Logger.getLogger(getClass());

	public abstract W toWrapper(E e);

	public abstract E toEntity(W w);

	public List<W> toListWrapper(List<E> listE) {
		List<W> listW = new ArrayList<W>();
		for (E e : listE) {
			listW.add(toWrapper(e));
		}
		return listW;
	}


	public List<E> toListEntity(List<W> listW) {
		List<E> listE = new ArrayList<E>();
		for (W w : listW) {
			listE.add(toEntity(w));
		}
		return listE;
	}

//	protected W toWrapper(E e, boolean lazy){
//		return null;
//	}
}