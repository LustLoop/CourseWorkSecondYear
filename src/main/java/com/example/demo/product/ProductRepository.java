package com.example.demo.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNumberOfProducts() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM PRODUCT", Integer.class);
    }

    public int addNewProduct(ProductInputDto product) {
        String INSERT_PRODUCT_SQL = "INSERT INTO products (title, price, energy_resource, accuracy) " + "VALUES (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL, new String[]{"product_id"});
            ps.setString(1, product.getTitle());
            ps.setBigDecimal(2, product.getPrice());
            ps.setString(3, product.getEnergyResource());
            ps.setString(4, product.getAccuracy());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int getWorktableId(String title) {
        String GET_WORKTABLE_ID = "SELECT worktable_type_id FROM worktable_types WHERE title = ?";
        return (Integer) jdbcTemplate.queryForObject(GET_WORKTABLE_ID, new Object[] { title }, Integer.class);
    }

    public int addNewWorktable(ProductInputDto product) {
        int productId = addNewProduct(product);

        String INSERT_WORKTABLE_SQL = "INSERT INTO worktables (product_id, worktable_type_id, portable) " + "VALUES (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_WORKTABLE_SQL, new String[]{"worktable_id"});
            ps.setInt(1, productId);
            ps.setInt(2, getWorktableId(product.getTypeOfWork().name()));
            ps.setBoolean(3, product.isPortable());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int addNewHydraulicWorktable(ProductInputDto product) {
        int worktableId = addNewWorktable(product);

        String INSERT_HYDRAULIC_WORKTABLE_SQL = "INSERT INTO hydraulic_worktables "
                + "(worktable_id, electricity_consumes, time_consumes_for_one_unit) "
                + "VALUES (?,?,?)";

        return jdbcTemplate.update(INSERT_HYDRAULIC_WORKTABLE_SQL,
                worktableId, product.getElectricityConsumes(), product.getTimeConsumesForOneUnit());
    }

    public void add(ProductInputDto product) {
        switch (product.getTypeOfProduct()) {
            case TOOL:
                System.out.println("Didnt implement adding tools");
                break;
            case WORKTABLE:
                switch (product.getTypeOfWork()) {
                    case HYDRAULIC:
                        addNewHydraulicWorktable(product);
                        break;
                    case LASER:
                        System.out.println("2");
                        break;
                    case PLASMIC:
                        System.out.println("3");
                        break;
                    default:
                        System.out.println("Something went wrong");
                        break;
                }
        }
    }
}
