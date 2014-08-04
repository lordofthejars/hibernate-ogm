/*
 * Hibernate OGM, Domain model persistence for NoSQL datastores
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.ogm.dialect.impl;

import org.hibernate.engine.spi.QueryParameters;
import org.hibernate.ogm.datastore.spi.Tuple;
import org.hibernate.ogm.dialect.BatchOperationsDelegator;
import org.hibernate.ogm.dialect.BatchableGridDialect;
import org.hibernate.ogm.dialect.GridDialect;
import org.hibernate.ogm.dialect.spi.QueryableGridDialect;
import org.hibernate.ogm.query.spi.BackendQuery;
import org.hibernate.ogm.query.spi.ParameterMetadataBuilder;
import org.hibernate.ogm.util.ClosableIterator;

/**
 * Batch delegator to be used for {@link GridDialect}s which support the execution of native queries.
 *
 * @author Davide D'Alto &lt;davide@hibernate.org&gt;
 */
public class QueryableBatchOperationsDelegator extends BatchOperationsDelegator implements QueryableGridDialect {

	private final QueryableGridDialect dialect;

	public QueryableBatchOperationsDelegator(BatchableGridDialect dialect) {
		super( dialect );
		this.dialect = (QueryableGridDialect) dialect;
	}

	@Override
	public ClosableIterator<Tuple> executeBackendQuery(BackendQuery query, QueryParameters queryParameters) {
		return dialect.executeBackendQuery( query, queryParameters );
	}

	@Override
	public ParameterMetadataBuilder getParameterMetadataBuilder() {
		return dialect.getParameterMetadataBuilder();
	}

	@Override
	public Object parseNativeQuery(String nativeQuery) {
		return dialect.parseNativeQuery( nativeQuery );
	}
}