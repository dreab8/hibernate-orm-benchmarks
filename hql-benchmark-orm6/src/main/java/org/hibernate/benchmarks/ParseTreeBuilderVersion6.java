/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks;

import org.hibernate.query.sqm.produce.internal.hql.HqlParseTreeBuilder;

/**
 * @author John O`Hara
 * @author Luis Barreiro
 * @author Andrea Boriero
 * @author Steve Ebersole
 */
public class ParseTreeBuilderVersion6 implements org.hibernate.benchmarks.HqlParseTreeBuilder {

	@Override
	public Object getHQLParser(String hqlString) {
		return HqlParseTreeBuilder.INSTANCE.parseHql( hqlString );
	}
}