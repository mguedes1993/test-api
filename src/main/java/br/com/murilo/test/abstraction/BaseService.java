package br.com.murilo.test.abstraction;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T extends BaseEntity> {
	@Autowired
	private EntityManager entityManager;

	public abstract BaseRepository<T> getRepository();
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
