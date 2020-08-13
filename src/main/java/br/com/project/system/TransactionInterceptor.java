package br.com.project.system;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction entityTransaction = manager.getTransaction();
		boolean criador = false;

		try {
			if (!entityTransaction.isActive()) {
				// Faz rollback no que já passou
				// (senão, um futuro commit, confirmaria até mesmo operações sem transação)
				entityTransaction.begin();
				entityTransaction.rollback();
				
				//Inicia a transação
				entityTransaction.begin();
				
				criador = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (entityTransaction != null && criador) {
				entityTransaction.rollback();
			}

			throw e;
		} finally {
			if (entityTransaction != null && entityTransaction.isActive() && criador) {
				entityTransaction.commit();
			}
		}
	}
	
}
