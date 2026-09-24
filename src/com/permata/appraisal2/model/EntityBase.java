package com.permata.appraisal2.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityBase<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = -2108375341744114587L;

}