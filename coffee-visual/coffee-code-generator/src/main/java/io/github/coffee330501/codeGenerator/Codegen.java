package io.github.coffee330501.codeGenerator;

import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Codegen {


    static GlobalConfig entityConfig = new GlobalConfig();
    static GlobalConfig projectConfig = new GlobalConfig();
    static EnumTypeHandler enumTypeHandler = new EnumTypeHandler();

    public static void main(String[] args) {
        final String database = "test_mybatis_flex";
        final String ipAndPort = "127.0.0.1:3306";
        final String username = "root";
        final String password = "root";

        // 如果要使用枚举，就要维护代码生成器
        enumTypeHandler
                .add("user", "type", "io.github.coffee330501.common.enums.UserTypeEnum");
        generate(database, ipAndPort, username, password);
    }

    @SuppressWarnings("all")
    private static void generate(String database, String ipAndPort, String username, String password) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://" + ipAndPort + "/" + database + "?characterEncoding=utf-8");
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        initEntityConfig(database);
        Generator generator = new Generator(hikariDataSource, entityConfig);
        generator.generate();
    }


    /**
     * entity代码生产配置
     */
    public static GlobalConfig initEntityConfig(String database) {
        // 设置根包
        String sourceDir = System.getProperty("user.dir") + "/coffee-model/src/main/java";
        String basePackage = "io.github.coffee330501.model";
        entityConfig
                .getPackageConfig()
                .setSourceDir(sourceDir)
                .setBasePackage(basePackage);

        // 设置生成mapper/entity
        entityConfig.setMapperGenerateEnable(true);
        entityConfig.setEntityGenerateEnable(true);
        entityConfig.setEntityPackage(basePackage + ".domain.entity");


        // 允许覆盖
        entityConfig.setEntityOverwriteEnable(true);
        entityConfig.setMapperOverwriteEnable(false);

        // 设置表前缀和只生成哪些表
        entityConfig.setGenerateSchema(database);

        // 启用 Lombok
        entityConfig.setEntityWithLombok(true);

        // 通用列配置
        commonColumnConfig();
        return entityConfig;
    }

    /**
     * 业务代码生产配置
     */
    public static GlobalConfig createProjectConfig(String project, String basePackage) {
        // 设置根包
        String sourceDir = System.getProperty("user.dir") + "/" + project + "/src/main/java";
        projectConfig
                .getPackageConfig()
                .setSourceDir(sourceDir)
                .setBasePackage(basePackage);

        // 设置生成service/serviceImpl/controller
        projectConfig.setServiceImplGenerateEnable(true);
        projectConfig.setServiceGenerateEnable(true);
        projectConfig.setControllerGenerateEnable(true);

        // 不允许覆盖
        projectConfig.setControllerOverwriteEnable(false);
        projectConfig.setServiceOverwriteEnable(false);
        projectConfig.setServiceImplOverwriteEnable(false);

        return projectConfig;
    }

    /**
     * 通用列配置
     */
    private static void commonColumnConfig() {
        // Id
        ColumnConfig idColumn = new ColumnConfig();
        idColumn.setColumnName("id");
        idColumn.setPrimaryKey(true);
        idColumn.setKeyType(KeyType.Generator);
        idColumn.setKeyValue("SnowflakeId");
        entityConfig.setColumnConfig(idColumn);

        // 创建时间
        ColumnConfig createTimeColumn = new ColumnConfig();
        createTimeColumn.setColumnName("create_time");
        createTimeColumn.setOnInsertValue("now()");
        entityConfig.setColumnConfig(createTimeColumn);

        // 更新时间
        ColumnConfig updateTimeColumn = new ColumnConfig();
        updateTimeColumn.setColumnName("update_time");
        updateTimeColumn.setOnInsertValue("now()");
        updateTimeColumn.setOnUpdateValue("now()");
        entityConfig.setColumnConfig(updateTimeColumn);

        // 版本控制
        ColumnConfig versionColumn = new ColumnConfig();
        versionColumn.setColumnName("version");
        versionColumn.setVersion(true);
        entityConfig.setColumnConfig(versionColumn);

        // 删除时间
        ColumnConfig deleteTimeColumn = new ColumnConfig();
        deleteTimeColumn.setColumnName("delete_time");
        deleteTimeColumn.setLogicDelete(true);
        entityConfig.setColumnConfig(deleteTimeColumn);
        entityConfig.setColumnConfig(deleteTimeColumn);
    }

    static class EnumTypeHandler {
        /**
         * @param tableName    表名
         * @param column       列名
         * @param propertyType 字段的全限定类名
         * @return
         */
        public EnumTypeHandler add(String tableName, String column, String propertyType) {
            ColumnConfig columnConfig = new ColumnConfig();
            columnConfig.setColumnName(column);
            columnConfig.setPropertyType(propertyType);
            entityConfig.setColumnConfig(tableName, columnConfig);
            return this;
        }
    }
}