package com.corejsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named // or @ManagedBean
@RequestScoped
public class ScopeListBean {

	@Setter
	@Getter
	List<String> dependentScopeList;

	@Setter
	@Getter
	List<String> sessionScopeList;

	@Setter
	@Getter
	List<String> applicationScopeList;

	@Setter
	@Getter
	List<String> requestScopeList;

	public void init() {
		CDI<Object> cdi = CDI.current();
		BeanManager beanManager = cdi.getBeanManager();
		Set<Bean<?>> beans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {
		});

		dependentScopeList = new ArrayList<String>();
		sessionScopeList = new ArrayList<String>();
		applicationScopeList = new ArrayList<String>();
		requestScopeList = new ArrayList<String>();

		for (Bean<?> bean : beans) {
			switch (bean.getScope().getSimpleName()) {
			case "Dependent":
				dependentScopeList.add(bean.getBeanClass().getSimpleName());
				break;
			case "RequestScoped":
				requestScopeList.add(bean.getBeanClass().getSimpleName());
				break;
			case "ApplicationScoped":
				applicationScopeList.add(bean.getBeanClass().getSimpleName());
				break;
			case "SessionScoped":
				sessionScopeList.add(bean.getBeanClass().getSimpleName());
				break;
			default:
				break;
			}
			//			System.out.println("bean=" + bean.getBeanClass().getSimpleName() +
			//					", scope=" + bean.getScope().getSimpleName());

		}

		Collections.sort(dependentScopeList);
		Collections.sort(sessionScopeList);
		Collections.sort(applicationScopeList);
		Collections.sort(requestScopeList);

	}
}
