package com.example.demo.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collection;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addNewProduct(ProductInputDto product) {
        String INSERT_PRODUCT_SQL = "INSERT INTO products (title, price, energy_resource, accuracy, type_of_product_id)" +
                " VALUES (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL, new String[]{"product_id"});
            ps.setString(1, product.getTitle());
            ps.setBigDecimal(2, product.getPrice());
            ps.setString(3, product.getEnergyResource());
            ps.setString(4, product.getAccuracy());
            ps.setInt(5, getProductTypeId(product.getTypeOfProduct().name()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int getProductTypeId(String title) {
        String GET_PRODUCT_ID = "SELECT type_of_product_id FROM product_types WHERE type_of_product_title = ?";
        return jdbcTemplate.queryForObject(GET_PRODUCT_ID, new Object[]{title}, Integer.class);
    }

    public int getWorktableTypeId(String title) {
        String GET_WORKTABLE_ID = "SELECT worktable_type_id FROM worktable_types WHERE worktable_type_title = ?";
        return jdbcTemplate.queryForObject(GET_WORKTABLE_ID, new Object[]{title}, Integer.class);
    }

    public int getToolTypeId(String title) {
        String GET_TOOL_ID = "SELECT tool_type_id FROM tool_types WHERE tool_type_title = ?";
        return jdbcTemplate.queryForObject(GET_TOOL_ID, new Object[]{title}, Integer.class);
    }

    public int addNewTool(ProductInputDto product) {
        int productId = addNewProduct(product);

        String INSERT_WORKTABLE_SQL = "INSERT INTO tools (product_id, tool_type_id, consumable, rechargeable) " +
                "VALUES (?,?,?,?)";

        jdbcTemplate.update(INSERT_WORKTABLE_SQL,
                productId,
                getToolTypeId(product.getToolType().name()),
                product.isConsumable(),
                product.isRechargeable());
        return productId;
    }

    public int addNewWorktable(ProductInputDto product, int productId) {
        String INSERT_WORKTABLE_SQL = "INSERT INTO worktables " +
                "(product_id, worktable_type_id, portable, electricity_consumes, time_consumes_for_one_unit) " +
                "VALUES (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_WORKTABLE_SQL, new String[]{"worktable_id"});
            ps.setInt(1, productId);
            ps.setInt(2, getWorktableTypeId(product.getWorktableType().name()));
            ps.setBoolean(3, product.isPortable());
            ps.setBigDecimal(4, product.getElectricityConsumes());
            ps.setBigDecimal(5, product.getTimeConsumesForOneUnit());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int addNewHydraulicWorktable(ProductInputDto product) {
        int productId = addNewProduct(product);
        int worktableId = addNewWorktable(product, productId);

        String INSERT_HYDRAULIC_WORKTABLE_SQL = "INSERT INTO hydraulic_worktables " +
                "(worktable_id) " +
                "VALUES (?)";

        jdbcTemplate.update(INSERT_HYDRAULIC_WORKTABLE_SQL,
                worktableId);

        return productId;
    }

    public int addNewLaserWorktable(ProductInputDto product) {
        int productId = addNewProduct(product);
        int worktableId = addNewWorktable(product, productId);

        String INSERT_LASER_WORKTABLE_SQL = "INSERT INTO laser_worktables " +
                "(worktable_id, cartridge_consumes, cartridge_usage_times) " +
                "VALUES (?,?,?)";

        jdbcTemplate.update(INSERT_LASER_WORKTABLE_SQL,
                worktableId,
                product.getCartridgeConsumes(),
                product.getCartridgeUsageTimes());
        return productId;
    }

    public int addNewPlasmicWorktable(ProductInputDto product) {
        int productId = addNewProduct(product);
        int worktableId = addNewWorktable(product, productId);

        String INSERT_PLASMIC_WORKTABLE_SQL = "INSERT INTO plasmic_worktables " +
                "(worktable_id, gas_consumes) " +
                "VALUES (?,?)";

        jdbcTemplate.update(INSERT_PLASMIC_WORKTABLE_SQL,
                worktableId,
                product.getGasConsumes());

        return productId;
    }

    public int add(ProductInputDto product) {
        switch (product.getTypeOfProduct()) {
            case TOOL:
                return addNewTool(product);
            case WORKTABLE:
                switch (product.getWorktableType()) {
                    case HYDRAULIC:
                        return addNewHydraulicWorktable(product);
                    case LASER:
                        return addNewLaserWorktable(product);
                    case PLASMIC:
                        return addNewPlasmicWorktable(product);
                    default:
                        System.out.println("Unsupportable type of worktable " + product.getWorktableType());
                        break;
                }
            default:
                System.out.println("Unsupportable type of product");
                break;
        }
        return 0;
    }

    public Collection<ProductInputDto> getAll() {
        String GET_ALL = "SELECT DISTINCT ON (p.product_id) * " +
                "FROM products p " +
                "         LEFT JOIN product_types pt " +
                "                   ON pt.type_of_product_id = p.type_of_product_id " +
                "         LEFT JOIN tools t " +
                "                   ON p.type_of_product_id = 1 AND t.product_id = p.product_id " +
                "         LEFT JOIN tool_types tt " +
                "                   ON tt.tool_type_id = t.tool_type_id " +
                "         LEFT JOIN worktables w " +
                "                   ON p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN worktable_types wt " +
                "                   ON wt.worktable_type_id = w.worktable_type_id " +
                "         LEFT JOIN hydraulic_worktables hw " +
                "                   ON w.worktable_type_id = 1 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN laser_worktables " +
                "                   ON w.worktable_type_id = 2 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN plasmic_worktables " +
                "                   ON w.worktable_type_id = 3 AND p.type_of_product_id = 2 AND w.product_id = p.product_id ";
        return jdbcTemplate.query(GET_ALL, new Object[]{}, new ProductRowMapper());
    }

    public Collection<ProductInputDto> getProductsOfPage(int pageId, Filters filters) {
        String filterStatement = addExistingFilters(filters);
        String GET_PRODUCTS_OF_PAGE = "SELECT DISTINCT ON (p.product_id) * " +
                "FROM products p " +
                "         LEFT JOIN product_types pt " +
                "                   ON pt.type_of_product_id = p.type_of_product_id " +
                "         LEFT JOIN tools t " +
                "                   ON p.type_of_product_id = 1 AND t.product_id = p.product_id " +
                "         LEFT JOIN tool_types tt " +
                "                   ON tt.tool_type_id = t.tool_type_id " +
                "         LEFT JOIN worktables w " +
                "                   ON p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN worktable_types wt " +
                "                   ON wt.worktable_type_id = w.worktable_type_id " +
                "         LEFT JOIN hydraulic_worktables hw " +
                "                   ON w.worktable_type_id = 1 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN laser_worktables " +
                "                   ON w.worktable_type_id = 2 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN plasmic_worktables " +
                "                   ON w.worktable_type_id = 3 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                filterStatement +
                "ORDER BY p.product_id " +
                "LIMIT 6 OFFSET 6 * (? - 1)";
        return jdbcTemplate.query(GET_PRODUCTS_OF_PAGE, new Object[]{pageId}, new ProductRowMapper());
    }

    public ProductInputDto getProductById(int input_id) {
        String GET_BY_ID = "SELECT DISTINCT ON (p.product_id) * " +
                "FROM products p " +
                "         LEFT JOIN product_types pt " +
                "                   ON pt.type_of_product_id = p.type_of_product_id " +
                "         LEFT JOIN tools t " +
                "                   ON p.type_of_product_id = 1 AND t.product_id = p.product_id " +
                "         LEFT JOIN tool_types tt " +
                "                   ON tt.tool_type_id = t.tool_type_id " +
                "         LEFT JOIN worktables w " +
                "                   ON p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN worktable_types wt " +
                "                   ON wt.worktable_type_id = w.worktable_type_id " +
                "         LEFT JOIN hydraulic_worktables hw " +
                "                   ON w.worktable_type_id = 1 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN laser_worktables " +
                "                   ON w.worktable_type_id = 2 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "         LEFT JOIN plasmic_worktables " +
                "                   ON w.worktable_type_id = 3 AND p.type_of_product_id = 2 AND w.product_id = p.product_id " +
                "WHERE p.product_id = ?";

        return jdbcTemplate.queryForObject(GET_BY_ID, new Object[]{input_id}, new ProductRowMapper());
    }

    public void deleteProductById(int input_id) {
        String DELETE_BY_ID = "DELETE FROM products WHERE product_id = ?";
        jdbcTemplate.update(DELETE_BY_ID, input_id);
    }

    public String addExistingFilters(Filters filters) {
        String filterStatement = "WHERE true";
        if (filters.getTitle() != null) {
            filterStatement += " AND p.title LIKE '%' || '" + filters.getTitle() + "' || '%' ";
        }
        return filterStatement;
    }
}
