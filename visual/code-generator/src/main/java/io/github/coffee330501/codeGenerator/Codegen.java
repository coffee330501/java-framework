package io.github.coffee330501.codeGenerator;

import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Codegen {


    static GlobalConfig globalConfig = new GlobalConfig();
    static EnumTypeHandler enumTypeHandler = new EnumTypeHandler();

    public static void main(String[] args) {
        final String database = "test_mybatis_flex";
        final String ipAndPort = "127.0.0.1:3306";
        final String username = "root";
        final String password = "root";

        // 如果要使用枚举，就要维护代码生成器
        enumTypeHandler
                .add("user", "type", "io.github.coffee330501.testMybatisFlex.enums.UserTypeEnum");
        generate(database, ipAndPort, username, password);
    }

    /**
     * 代码生成器配置
     */
    public static GlobalConfig createGlobalConfig(String database) {
        // 设置根包
        String sourceDir = System.getProperty("user.dir") + "/visual/test-mybatis-flex/src/main/java";
        globalConfig
                .getPackageConfig()
                .setSourceDir(sourceDir)
                .setBasePackage("io.github.coffee330501.testMybatisFlex");

        // 设置生成mapper/service/serviceImpl/controller
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.setEntityGenerateEnable(true);

        // 允许覆盖
        globalConfig.setEntityOverwriteEnable(true);
        globalConfig.setMapperOverwriteEnable(false);
        globalConfig.setControllerOverwriteEnable(false);
        globalConfig.setServiceOverwriteEnable(false);
        globalConfig.setServiceImplOverwriteEnable(false);

        // 设置表前缀和只生成哪些表
        globalConfig.setGenerateSchema(database);

        // 启用 Lombok
        globalConfig.setEntityWithLombok(true);

        // 通用列配置
        commonColumnConfig();
        return globalConfig;
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
        globalConfig.setColumnConfig(idColumn);

        // 创建时间
        ColumnConfig createTimeColumn = new ColumnConfig();
        createTimeColumn.setColumnName("create_time");
        createTimeColumn.setOnInsertValue("now()");
        globalConfig.setColumnConfig(createTimeColumn);

        // 更新时间
        ColumnConfig updateTimeColumn = new ColumnConfig();
        updateTimeColumn.setColumnName("update_time");
        updateTimeColumn.setOnInsertValue("now()");
        updateTimeColumn.setOnUpdateValue("now()");
        globalConfig.setColumnConfig(updateTimeColumn);

        // 版本控制
        ColumnConfig versionColumn = new ColumnConfig();
        versionColumn.setColumnName("version");
        versionColumn.setVersion(true);
        globalConfig.setColumnConfig(versionColumn);

        // 删除时间
        ColumnConfig deleteTimeColumn = new ColumnConfig();
        deleteTimeColumn.setColumnName("delete_time");
        deleteTimeColumn.setLogicDelete(true);
        globalConfig.setColumnConfig(deleteTimeColumn);
        globalConfig.setColumnConfig(deleteTimeColumn);
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
            globalConfig.setColumnConfig(tableName, columnConfig);
            return this;
        }
    }

    @SuppressWarnings("all")
    private static void generate(String database, String ipAndPort, String username, String password) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://" + ipAndPort + "/" + database + "?characterEncoding=utf-8");
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        GlobalConfig globalConfig = createGlobalConfig(database);
        Generator generator = new Generator(hikariDataSource, globalConfig);
        generator.generate();
    }
}