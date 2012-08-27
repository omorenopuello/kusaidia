package org.kusaidia.util.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Singleton class to expose the {@linkplain AutowireCapableBeanFactory} as
 * part of objects created outside the scope of Spring managed beans. This
 * class could be used by other objects that need access to this {@linkplain
 * org.springframework.beans.factory.BeanFactory} in order to attach or
 * inject dependencies to newly created objects.
 *
 * @author Angel L. Villalain Garcia
 * @since 1.0.0
 * @version 1.0.0
 */
@Component
public final class AutowireCapableBeanFactorySingleton {
    /**
     * the exposed {@linkplain AutowireCapableBeanFactory} bean factory
     */
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    /**
     * Class used to implement a secure thread-safe mechanism for creating
     * this object. This is based on the Initialization on demand holder idiom
     * proposed by Bill Pugh.
     */
    private static class LazyHolder {
        private static final AutowireCapableBeanFactorySingleton INSTANCE =
                new AutowireCapableBeanFactorySingleton();
    }

    /**
     * Method to retrieve the instance for this class.
     *
     * @return returns the existing configured instance of this object or
     * creates a new one.
     */
    public static AutowireCapableBeanFactorySingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Private method used internally to create an instance of this class.
     */
    private AutowireCapableBeanFactorySingleton() {}


    /**
     * Returns the {@linkplain AutowireCapableBeanFactory} associated with
     * this object.
     * @return the exposed {@linkplain AutowireCapableBeanFactory} bean
     * factory.
     * @throws IllegalStateException if this object was not created as part
     * of the Spring application context.
     */
    public AutowireCapableBeanFactory getBeanFactory() {
        if (beanFactory == null) {
            throw new IllegalStateException("AutowireCapableBeanFactorySingleton " +
                    "must be declared as a bean in the spring context");
        }
        return this.beanFactory;
    }
}
