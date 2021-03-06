/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.common;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jboss.logging.Logger;

/**
 * This class loads the code fragments that are measured on this benchmark
 */
public class BenchmarkFactory {
	private static final Logger log = Logger.getLogger( BenchmarkFactory.class );

	public static final String[] VERSION_SUPPORT_IMPL_NAMES = {
			"org.hibernate.benchmarks.Version5Support",
			"org.hibernate.benchmarks.Version6Support"
	};

	public static HibernateVersionSupport buildHibernateVersionSupport() {
		return findHibernateVersionSupportToUse();
	}

	private static HibernateVersionSupport findHibernateVersionSupportToUse() {
		for ( String implClassName : BenchmarkFactory.VERSION_SUPPORT_IMPL_NAMES ) {
			try {
				final Class<?> implClass = BenchmarkFactory.class.getClassLoader().loadClass( implClassName );

				try {
					return (HibernateVersionSupport) implClass.getConstructor().newInstance();
				}
				catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(
							"Found HibernateVersionSupport class [" + implClassName + "], but could not locate appropriate ctor",
							e
					);
				}
			}
			catch (ClassNotFoundException e) {
				log.debugf( "Could not locate HibernateVersionSupport impl : " + implClassName );
			}
		}
		throw new RuntimeException( "Could not locate HibernateVersionSupport impl" );
	}

	public static MethodHandle getMethodHandle(Class<?> theClass, String methodName, Class<?>... arguments) {
		try {
			Method theMethod = theClass.getDeclaredMethod( methodName, arguments );
			theMethod.setAccessible( true );
			return MethodHandles.lookup().unreflect( theMethod );
		}
		catch ( NoSuchMethodException | IllegalAccessException e ) {
			e.printStackTrace();
			return null;
		}
	}
}
