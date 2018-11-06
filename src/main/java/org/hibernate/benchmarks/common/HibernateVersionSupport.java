/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.common;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;

/**
 * The main abstraction between different versions of Hibernate
 */
public interface HibernateVersionSupport {
	/**
	 * Generate a Hibernate SessionFactory and do any other prep work
	 */
	void setUp(Class[] annotatedClasses, String[] hbmfiles, boolean createSchema);

	/**
	 * Get a delegate for performing the first step of HQL interpretation,
	 * which is having Antlr generate its "parse tree" of the HQL statement
	 */
	HqlParseTreeBuilder getHqlParseTreeBuilder();

	/**
	 * Get a delegate for performing the second step of HQL interpretation,
	 * which is to perform initial "semantic interpretation" of the parse tree
	 */
	HqlSemanticInterpreter getHqlSemanticInterpreter();

	/**
	 * Perform some action in a Hibernate Session
	 */
	void inTransaction(Consumer<EntityManager> consumer);

	/**
	 * Perform some action in a Hibernate Session, returning a value
	 */
	<T> T inTransaction(Function<EntityManager, T> function);

	/**
	 * Close the SessionFactory, etc
	 */
	void shutDown();
}

