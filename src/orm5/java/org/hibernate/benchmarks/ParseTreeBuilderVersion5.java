package org.hibernate.benchmarks;

import org.hibernate.benchmarks.common.HqlParseTreeBuilder;

public class ParseTreeBuilderVersion5 implements HqlParseTreeBuilder {

    @Override
    public Object getHQLParser(String hqlString) {
        return 0;
    }
}
