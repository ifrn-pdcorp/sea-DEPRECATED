package br.edu.ifrn.laj.pdcorp.apisea.validators;

import org.springframework.stereotype.Component;

public abstract class AbstractValidationMediator<E> {

	protected BusinessInvalidation businessInvalidation;

	public AbstractValidationMediator(BusinessInvalidation businessInvalidation) {
		this.businessInvalidation = businessInvalidation;
	}

	public BusinessInvalidation getBusinessInvalidation() {
		return businessInvalidation;
	}

	protected abstract BusinessInvalidation verify(E e);

	public final boolean isInvalid(E e) {
		return this.verify(e).exists();
	}

}
