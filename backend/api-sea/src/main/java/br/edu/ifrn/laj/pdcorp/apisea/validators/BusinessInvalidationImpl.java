package br.edu.ifrn.laj.pdcorp.apisea.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;

public class BusinessInvalidationImpl implements BusinessInvalidation {
	
	private List<BusinessRule> rules;
	private ApiException cause;
	
	public BusinessInvalidationImpl() {
		this.rules = new ArrayList<BusinessRule>();
	}

	@Override
	public boolean exists() {
	    rules.stream().forEach(r -> {
			if(r.check())
				this.cause = r.getException();
		});
	    
	    return ObjectUtils.isEmpty(cause);
	}

	@Override
	public BusinessInvalidation withRule(BusinessRule rule) {
		this.rules.add(rule);
		return this;
	}
	
	@Override
	public ApiException getCause() {
		return cause;
	}

}
