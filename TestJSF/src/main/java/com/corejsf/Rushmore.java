package com.corejsf;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
// or import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named("rushmore") // or @ManagedBean
@RequestScoped
public class Rushmore {
	private String outcome = null;
	private final static String REDIRECT = "?faces-redirect=true";
	private Rectangle washingtonRect = new Rectangle(70, 30, 40, 40);
	private Rectangle jeffersonRect = new Rectangle(115, 45, 40, 40);
	private Rectangle rooseveltRect = new Rectangle(135, 65, 40, 40);
	private Rectangle lincolnRect = new Rectangle(175, 62, 40, 40);

	List<String> dependentScopeList = new ArrayList<String>();
	List<String> sessionScopeList = new ArrayList<String>();
	List<String> applicationScopeList = new ArrayList<String>();
	List<String> requestScopeList = new ArrayList<String>();

	public void handleMouseClick(ActionEvent e) {

		FacesContext context = FacesContext.getCurrentInstance();
		String clientId = e.getComponent().getClientId(context);
		Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();

		int x = new Integer(requestParams.get(clientId + ".x")).intValue();
		int y = new Integer(requestParams.get(clientId + ".y")).intValue();

		outcome = null;

		if (washingtonRect.contains(new Point(x, y))) {
			outcome = "/pages/ch8/washington" + REDIRECT;
		}

		if (jeffersonRect.contains(new Point(x, y))) {
			outcome = "/pages/ch8/jefferson" + REDIRECT;
		}

		if (rooseveltRect.contains(new Point(x, y))) {
			outcome = "/pages/ch8/roosevelt" + REDIRECT;
		}

		if (lincolnRect.contains(new Point(x, y))) {
			outcome = "/pages/ch8/lincoln" + REDIRECT;
		}

		printCdiBeans();

		//		throw new IllegalArgumentException("除算の分母がゼロで不正です");
	}

	public String navigate() {
		return outcome;
	}

	private void printCdiBeans() {
		CDI<Object> cdi = CDI.current();
		BeanManager beanManager = cdi.getBeanManager();
		Set<Bean<?>> beans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {
		});
		List<String> dependentScopeList = new ArrayList<String>();
		List<String> sessionScopeList = new ArrayList<String>();
		List<String> applicationScopeList = new ArrayList<String>();
		List<String> requestScopeList = new ArrayList<String>();

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
			}
			//			System.out.println("bean=" + bean.getBeanClass().getSimpleName() +
			//					", scope=" + bean.getScope().getSimpleName());

		}
	}
}
