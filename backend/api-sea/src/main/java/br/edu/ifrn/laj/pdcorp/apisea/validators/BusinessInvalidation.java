package br.edu.ifrn.laj.pdcorp.apisea.validators;

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;

public interface BusinessInvalidation {

	boolean exists();
	BusinessInvalidation withRule(BusinessRule rule);
	ApiException getCause(); 
}
