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

    public int addNewProduct(Product product) {
        String INSERT_PRODUCT_SQL = "INSERT INTO " +
                "products (title, description, price, energy_resource, accuracy, type_of_product_id)" +
                " VALUES (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL, new String[]{"product_id"});
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getEnergyResource());
            ps.setString(5, product.getAccuracy());
            ps.setInt(6, getProductTypeId(product.getTypeOfProduct().name()));
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
        int productId = addNewProduct(product.convertToTool());

        String INSERT_WORKTABLE_SQL = "INSERT INTO tools (product_id, tool_type_id, consumable, rechargeable) " +
                "VALUES (?,?,?,?)";

        jdbcTemplate.update(INSERT_WORKTABLE_SQL,
                productId,
                getToolTypeId(product.getToolType().name()),
                product.isConsumable(),
                product.isRechargeable());
        return productId;
    }

    public int addNewWorktable(Worktable worktable, int productId) {
        String INSERT_WORKTABLE_SQL = "INSERT INTO worktables " +
                "(product_id, worktable_type_id, efficiency, portable, electricity_consumes, time_consumes_for_one_unit) " +
                "VALUES (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_WORKTABLE_SQL, new String[]{"worktable_id"});
            ps.setInt(1, productId);
            ps.setInt(2, getWorktableTypeId(worktable.getWorktableType().name()));
            ps.setBigDecimal(3, worktable.getEfficiency());
            ps.setBoolean(4, worktable.isPortable());
            ps.setBigDecimal(5, worktable.getElectricityConsumes());
            ps.setBigDecimal(6, worktable.getTimeConsumesForOneUnit());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public int addNewHydraulicWorktable(ProductInputDto product) {
        HydraulicWorktable hw = product.convertToHydraulicWorktable();
        int productId = addNewProduct(hw);
        int worktableId = addNewWorktable(hw, productId);

        String INSERT_HYDRAULIC_WORKTABLE_SQL = "INSERT INTO hydraulic_worktables " +
                "(worktable_id) " +
                "VALUES (?)";

        jdbcTemplate.update(INSERT_HYDRAULIC_WORKTABLE_SQL,
                worktableId);

        return productId;
    }

    public int addNewLaserWorktable(ProductInputDto product) {
        LaserWorktable lw = product.convertToLaserWorktable();
        int productId = addNewProduct(lw);
        int worktableId = addNewWorktable(lw, productId);

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
        PlasmicWorktable pw = product.convertToPlasmicWorktable();
        int productId = addNewProduct(pw);
        int worktableId = addNewWorktable(pw, productId);

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

    public Collection<ProductInputDto> getProductsOfPage(int pageId, Filters filters, String sortType) {
        String GET_PRODUCTS_OF_PAGE = "SELECT * FROM (" +
                "SELECT DISTINCT ON (p.product_id) * " +
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
                addExistingFilters(filters) +
                ") r " +
                addSorting(sortType) +
                " LIMIT 6 OFFSET 6 * (? - 1)";
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
        String filterStatement = "WHERE true ";
        if (filters.getTitle() != null) {
            filterStatement += "AND p.title LIKE '%' || '" + filters.getTitle() + "' || '%' ";
        }
        if (filters.getTypes() != null) {
            String typeIndexes = "";
            for (String type : filters.getTypes()) {
                if (typeIndexes != "") {
                    typeIndexes +=", ";
                }
                typeIndexes += type;
            }
            filterStatement += "AND p.type_of_product_id IN (" + typeIndexes + ") ";
        }
        if (filters.getStartPriceRange() != null && filters.getEndPriceRange() != null) {
            filterStatement += "AND p.price > " + filters.getStartPriceRange() + "  AND p.price < " + filters.getEndPriceRange() + " ";
        }
        return filterStatement;
    }

    public String addSorting(String sortType) {
        String sortStatement = "ORDER BY ";
        switch (sortType) {
            case "name":
                sortStatement += "r.title ";
                break;
            case "price":
                sortStatement += "r.price ";
                break;
            case "priceDesc":
                sortStatement += "r.price DESC ";
                break;
            case "efficiency":
                sortStatement += "r.efficiency ";
                break;
            default:
                sortStatement = " ";
                break;
        }
        return sortStatement;
    }
}
