package com.flab.investing.stock.batch.infrastructure.stock;

import com.flab.investing.stock.batch.infrastructure.krx.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StockDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(final List<Item> items) {
        final String sql = "INSERT INTO stock (code, short_code, name, short_name, corporation_code) VALUES (:code, :shortCode, :name, :shortName, :corporationCode) ";

        List<SqlParameterSource> parameters = items.stream().map(item -> {
                    MapSqlParameterSource paramSource = new MapSqlParameterSource();
                    paramSource.addValue("code", item.isinCd());
                    paramSource.addValue("short_code", item.srtnCd());
                    paramSource.addValue("name", item.corpNm());
                    paramSource.addValue("short_name", item.itmsNm());
                    paramSource.addValue("corporation_code", item.crno());

                    return paramSource;
                })
                .collect(Collectors.toList());

        namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[items.size()]));
    }

}
