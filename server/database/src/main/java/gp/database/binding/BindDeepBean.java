package gp.database.binding;

import gp.api.internal.Identificado;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@BindingAnnotation(BindDeepBean.BeanBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BindDeepBean {

    String value() default "";

    public static class BeanBinderFactory implements BinderFactory {

        @Override
        public Binder<BindDeepBean, Object> build(Annotation annotation) {
            return new Binder<BindDeepBean, Object>() {
                @Override
                public void bind(SQLStatement<?> q, BindDeepBean bind, Object arg) {
                    final String prefix;
                    if (bind.value().isEmpty()) {
                        prefix = "";
                    } else {
                        prefix = bind.value() + ".";
                    }

                    try {
                        bind(prefix, q, arg.getClass(), arg);
                    } catch (Exception e) {
                        throw new IllegalStateException("unable to bind bean properties", e);
                    }
                }

                void bind(String prefix, SQLStatement<?> q, Class<?> klass, Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
                    BeanInfo infos = Introspector.getBeanInfo(klass);
                    PropertyDescriptor[] props = infos.getPropertyDescriptors();
                    for (PropertyDescriptor prop : props) {
                        Class<?> type = prop.getPropertyType();
                        if (type != null && prop.getReadMethod() != null && obj != null) {
                            if (Identificado.class.isAssignableFrom(type)) {
                                q.bind(prefix + prop.getName(), ((Identificado) prop.getReadMethod().invoke(obj)).getId());
                            } else if (URI.class.isAssignableFrom(type)) {
                                q.bind(prefix + prop.getName(), Objects.toString(prop.getReadMethod().invoke(obj), null));
                            } else if (type.isArray() || type.isEnum() || type.isPrimitive() || String.class.isAssignableFrom(type) ||
                                Class.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type)) {
                                q.bind(prefix + prop.getName(), prop.getReadMethod().invoke(obj));
                            } else {
                                if (prefix.isEmpty()) {
                                    bind(prop.getName() + ".", q, type, prop.getReadMethod().invoke(obj));
                                } else {
                                    bind(prefix + "." + prop.getName() + ".", q, type, prop.getReadMethod().invoke(obj));
                                }
                            }
                        }
                    }
                }
            };
        }
    }
}
