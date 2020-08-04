package br.edu.ifrn.laj.pdcorp.apisea.validators;

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;

public interface BusinessRule {

	boolean check();
	ApiException getException();
}
