package com.webapp.challenge.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Ein Bean, dass die View-Scope von JSF bei Spring bekannt macht.
 * 
 * <p>Diese Scope wird standardmäßig in <code>/alf-jsf/src/main/resources/alf-jsf-beans.xml</code> registiert.</p>
 * 
 * <p>Ohne dieser Datei kann man die Scope auf folgende Weise in Spring registrieren:
 * <pre>{@code
 *    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
 *        <property name="scopes">
 *            <map>
 *                <entry key="view">
 *                    <bean class="de.wirthedv.alf.jsf.FacesViewScope" />
 *                </entry>
 *            </map>
 *        </property>
 *    </bean>
 * }</pre>
 * </p>
 * 
 *
 */
public class ViewScope implements Scope, Serializable, HttpSessionBindingListener {
	
	public static final String NAME = "view";
	
	
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
//		private static final Logger LOGGER = LoggerFactory.getLogger(ViewScope.class);
		
	    private final WeakHashMap<HttpSession, Set<ViewScopeViewMapListener>> sessionToListeners = new WeakHashMap<HttpSession, Set<ViewScopeViewMapListener>>();

	    @Override
	    public Object get(String name, @SuppressWarnings("rawtypes") ObjectFactory objectFactory) {
	        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	        //noinspection SynchronizationOnLocalVariableOrMethodParameter
	        synchronized (viewMap) {
	            if (viewMap.containsKey(name)) {
	                return viewMap.get(name);
	            } else {
//	                LOGGER.debug("Creating bean {}", name);
	                Object object = objectFactory.getObject();
	                viewMap.put(name, object);

	                return object;
	            }
	        }
	    }

	    @Override
	    public Object remove(String name) {
	        throw new UnsupportedOperationException();
	    }

	    @Override
	    public String getConversationId() {
	        return null;
	    }

	    @Override
	    public void registerDestructionCallback(String name, Runnable callback) {
//	        LOGGER.debug("registerDestructionCallback for bean {}", name);
	        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	        ViewScopeViewMapListener listener =
	                new ViewScopeViewMapListener(viewRoot, name, callback, this);

	        viewRoot.subscribeToViewEvent(PreDestroyViewMapEvent.class, listener);

	        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	        final Set<ViewScopeViewMapListener> sessionListeners;
	        synchronized (sessionToListeners) {
	            if (!sessionToListeners.containsKey(httpSession)) {
	                sessionToListeners.put(httpSession, new HashSet<ViewScopeViewMapListener>());
	            }
	            sessionListeners = sessionToListeners.get(httpSession);
	        }
	        //noinspection SynchronizationOnLocalVariableOrMethodParameter
	        synchronized (sessionListeners) {
	            Set<ViewScopeViewMapListener> toRemove = new HashSet<ViewScopeViewMapListener>();
	            for (ViewScopeViewMapListener viewMapListener : sessionListeners) {
	                if (viewMapListener.checkRoot()) {
	                    toRemove.add(viewMapListener);
	                }
	            }
	            sessionListeners.removeAll(toRemove);
	            sessionListeners.add(listener);
	        }
	        if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("sessionBindingListener")) {
	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionBindingListener", this);
	        }

	    }

	    @Override
	    public Object resolveContextualObject(String key) {
	        return null;
	    }

	    @Override
	    public void valueBound(HttpSessionBindingEvent event) {
//	        LOGGER.debug("Session event bound {}", event.getName());
	    }

	    @Override
	    public void valueUnbound(HttpSessionBindingEvent event) {
//	        LOGGER.debug("Session event unbound {}", event.getName());
	        final Set<ViewScopeViewMapListener> listeners;
	        synchronized (sessionToListeners) {
	            if (sessionToListeners.containsKey(event.getSession())) {
	                listeners = sessionToListeners.get(event.getSession());
	                sessionToListeners.remove(event.getSession());
	            } else {
	                listeners = null;
	            }
	        }
	        if (listeners != null) {
	            for (ViewScopeViewMapListener listener : listeners) {
	                listener.doCallback();
	            }
	        }
	    }

	    public void clearFromListener(ViewScopeViewMapListener listener) {
//	        LOGGER.debug("Removing listener from map");
	        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        if (httpSession != null) {
	            synchronized (sessionToListeners) {
	                if (sessionToListeners.containsKey(httpSession)) {
	                    sessionToListeners.get(httpSession).remove(listener);
	                }
	            }
	        }
	    }


//    @Override
//    public Object get(String name, ObjectFactory<?> objectFactory) {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        if (facesContext == null) {
//        	throw new IllegalStateException("FacesContext.getCurrentInstance() returned null");
//        }
//        
//        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
//
//        if (viewMap.containsKey(name)) {
//            return viewMap.get(name);
//        } else {
//            Object object = objectFactory.getObject();
//            viewMap.put(name, object);
//
//            return object;
//        }
//    }
//
//    @Override
//    public Object remove(String name) {
//        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
//    }
//
//    @Override
//    public String getConversationId() {
//        return null;
//    }
//
//    @Override
//    public void registerDestructionCallback(String name, Runnable callback) {
//    	// Not supported by JSF for view scope
//    }
//
//    @Override
//    public Object resolveContextualObject(String key) {
//        return null;
//    }
}
