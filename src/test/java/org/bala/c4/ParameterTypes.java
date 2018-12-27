package org.bala.c4;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;
import io.cucumber.datatable.TableEntryTransformer;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import static java.util.Locale.ENGLISH;

public class ParameterTypes implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return ENGLISH;
    }

    @Override
    public void configureTypeRegistry(final TypeRegistry typeRegistry) {
        Transformer transformer = new Transformer();

        typeRegistry.setDefaultDataTableCellTransformer(transformer);
        typeRegistry.setDefaultDataTableEntryTransformer(transformer);
        typeRegistry.setDefaultParameterTransformer(transformer);

        typeRegistry.defineDataTableType(new DataTableType( Trade.class, transformer));

    }

    private class Transformer implements ParameterByTypeTransformer,
                                         TableEntryByTypeTransformer,
                                         TableCellByTypeTransformer,
                                         TableEntryTransformer<Trade> {
        final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Object transform(final String value, final Type type) {
            return objectMapper.convertValue(value, objectMapper.constructType(type));
        }

        @Override
        public <T> T transform(final Map<String, String> row, final Class<T> type, final TableCellByTypeTransformer transformer) {
            return objectMapper.convertValue(row, type);
        }

        @Override
        public <T> T transform(final String value, final Class<T> type) {
            return objectMapper.convertValue(value, type);
        }

        /**
         * Need this specific implementation as Trade is immutable.
         * i.e., the above generic transformers work only for mutable
         * objects that will have setters
         *
         * @param row map of column name to cell value
         * @return Trade constructed from each row
         */
        @Override
        public Trade transform(final Map<String, String> row) {
            return new Trade(row.get("productId"),
                             parseLong(row.get("size")),
                             parseDouble(row.get("price")),
                             parseLong(row.get("timestamp")));
        }
    }
}
