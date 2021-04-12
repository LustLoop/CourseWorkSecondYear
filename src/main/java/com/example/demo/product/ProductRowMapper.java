package com.example.demo.product;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductInputDto> {

    @Override
    public ProductInputDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductInputDto product = new ProductInputDto();

        product.setId(rs.getInt("product_id"));
        product.setTitle(rs.getString("title"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setEnergyResource(rs.getString("energy_resource"));
        product.setAccuracy(rs.getString("accuracy"));
        product.setTypeOfProduct(TypeOfProduct.valueOf(rs.getString("type_of_product_title")));

        if (product.getTypeOfProduct().name().equals("TOOL")) {
            product.setToolType(ToolType.valueOf(rs.getString("tool_type_title")));
            product.setConsumable(rs.getBoolean("consumable"));
            product.setRechargeable(rs.getBoolean("rechargeable"));
        } else if (product.getTypeOfProduct().name().equals("WORKTABLE")) {
            product.setPortable(rs.getBoolean("portable"));
            product.setWorktableType(WorktableType.valueOf(rs.getString("worktable_type_title")));
            switch (product.getWorktableType().name()) {
                case "HYDRAULIC":
                    product.setElectricityConsumes(rs.getBigDecimal("electricity_consumes"));
                    product.setTimeConsumesForOneUnit(rs.getBigDecimal("time_consumes_for_one_unit"));
                    break;
                case "LASER":
                    product.setElectricityConsumes(rs.getBigDecimal("electricity_consumes"));
                    product.setTimeConsumesForOneUnit(rs.getBigDecimal("time_consumes_for_one_unit"));
                    product.setCartridgeConsumes(rs.getBigDecimal("cartridge_consumes"));
                    product.setCartridgeUsageTimes(rs.getBigDecimal("cartridge_usage_times"));
                    break;
                case "PLASMIC":
                    product.setElectricityConsumes(rs.getBigDecimal("electricity_consumes"));
                    product.setTimeConsumesForOneUnit(rs.getBigDecimal("time_consumes_for_one_unit"));
                    product.setGasConsumes(rs.getBigDecimal("gas_consumes"));
                    break;
                default:
                    System.out.println("Got unsupportable worktable type");
                    break;
            }
        } else {
            System.out.println("Got unsupportable product type");
        }
        return product;
    }
}
