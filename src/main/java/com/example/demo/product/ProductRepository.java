package com.example.demo.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addNewProduct(ProductInputDto product) {
        String INSERT_PRODUCT_SQL = "INSERT INTO products (title, price, energy_resource, accuracy) VALUES (?,?,?,?)";

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

    public int getWorktableTypeId(String title) {
        String GET_WORKTABLE_ID = "SELECT worktable_type_id FROM worktable_types WHERE worktable_type_title = ?";
        return (Integer) jdbcTemplate.queryForObject(GET_WORKTABLE_ID, new Object[] { title }, Integer.class);
    }

    public int getToolTypeId(String title) {
        String GET_TOOL_ID = "SELECT tool_type_id FROM tool_types WHERE tool_type_title = ?";
        return (Integer) jdbcTemplate.queryForObject(GET_TOOL_ID, new Object[] { title }, Integer.class);
    }

    public int addNewTool(ProductInputDto product) {
        int productId = addNewProduct(product);

        String INSERT_WORKTABLE_SQL = "INSERT INTO tools (product_id, tool_type_id, consumable, rechargeable) " +
                "VALUES (?,?,?,?)";

        return jdbcTemplate.update(INSERT_WORKTABLE_SQL,
                productId,
                getToolTypeId(product.getToolType().name()),
                product.isConsumable(),
                product.isRechargeable());
    }

    public int addNewWorktable(ProductInputDto product) {
        int productId = addNewProduct(product);

        String INSERT_WORKTABLE_SQL = "INSERT INTO worktables (product_id, worktable_type_id, portable) VALUES (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_WORKTABLE_SQL, new String[]{"worktable_id"});
            ps.setInt(1, productId);
            ps.setInt(2, getWorktableTypeId(product.getTypeOfWork().name()));
            ps.setBoolean(3, product.isPortable());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int addNewHydraulicWorktable(ProductInputDto product) {
        int worktableId = addNewWorktable(product);

        String INSERT_HYDRAULIC_WORKTABLE_SQL = "INSERT INTO hydraulic_worktables " +
                "(worktable_id, electricity_consumes, time_consumes_for_one_unit) " +
                "VALUES (?,?,?)";

        return jdbcTemplate.update(INSERT_HYDRAULIC_WORKTABLE_SQL,
                worktableId,
                product.getElectricityConsumes(),
                product.getTimeConsumesForOneUnit());
    }

    public int addNewLaserWorktable(ProductInputDto product) {
        int worktableId = addNewWorktable(product);

        String INSERT_LASER_WORKTABLE_SQL = "INSERT INTO laser_worktables " +
                "(worktable_id, electricity_consumes, time_consumes_for_one_unit, cartridge_consumes, cartridge_usage_times) " +
                "VALUES (?,?,?,?,?)";

        return jdbcTemplate.update(INSERT_LASER_WORKTABLE_SQL,
                worktableId,
                product.getElectricityConsumes(),
                product.getTimeConsumesForOneUnit(),
                product.getCartridgeConsumes(),
                product.getCartridgeUsageTimes());
    }

    public int addNewPlasmicWorktable(ProductInputDto product) {
        int worktableId = addNewWorktable(product);

        String INSERT_PLASMIC_WORKTABLE_SQL = "INSERT INTO hydraulic_worktables " +
                "(worktable_id, electricity_consumes, time_consumes_for_one_unit, gas_consumes) " +
                "VALUES (?,?,?,?)";

        return jdbcTemplate.update(INSERT_PLASMIC_WORKTABLE_SQL,
                worktableId,
                product.getElectricityConsumes(),
                product.getTimeConsumesForOneUnit(),
                product.getGasConsumes());
    }

    public void add(ProductInputDto product) {
        switch (product.getTypeOfProduct()) {
            case TOOL:
                addNewTool(product);
                break;
            case WORKTABLE:
                switch (product.getTypeOfWork()) {
                    case HYDRAULIC:
                        addNewHydraulicWorktable(product);
                        break;
                    case LASER:
                        addNewLaserWorktable(product);
                        break;
                    case PLASMIC:
                        addNewPlasmicWorktable(product);
                        break;
                    default:
                        System.out.println("Unsupportable type of worktable " + product.getTypeOfWork());
                        break;
                }
            default:
                System.out.println("Unsupportable type of product");
                break;
        }
    }

    public Collection<Product> getAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
            "SELECT tool_types.tool_type_title, T.consumable, T.rechargeable " +
                    "FROM tool_types " +
                    "JOIN tools AS T " +
                    "ON tool_types.tool_type_id = T.tool_type_id ");

        for (Map row : rows) {
            ProductInputDto product = new ProductInputDto();
            // convert to dto?
        }
        return null;
    }

    public ProductInputDto getProductById(int input_id) {
        String GET_BY_ID = "SELECT DISTINCT ON (products.product_id) * " +
                "FROM products " +
                "JOIN tools " +
                "ON products.product_id = ? " +
                "JOIN tool_types " +
                "ON tool_types.tool_type_id = tools.tool_type_id ";

        return jdbcTemplate.queryForObject(GET_BY_ID, new Object[] { input_id }, new ProductRowMapper());
    }
}
