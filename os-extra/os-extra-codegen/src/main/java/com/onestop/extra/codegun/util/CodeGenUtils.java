package com.onestop.extra.codegun.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 * @author Clark
 * @version 2021-05-20
 */
@Slf4j
@Component
public class CodeGenUtils {
    /**
     * 获取配置信息
     *
     * @return Configuration
     */
    public Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            log.error("获取配置文件失败，", e);
        }
        return null;
    }

    /**
     * 生成代码
     */
    public void codeGenerator() {
        Configuration config = getConfig();

        // 代码生成器
        String projectPath = System.getProperty("user.dir");
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + config.getString("OutputDir"));
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // 自定义ControllerName
        gc.setControllerName("%sApi");
        // XML 二级缓存
//        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor(config.getString("Author"));
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(config.getString("Db.Url"));
        dsc.setDriverName(config.getString("Db.DriverName"));
        dsc.setUsername(config.getString("Db.Username"));
        dsc.setPassword(config.getString("Db.Password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(config.getString("PackageConfig.Parent"));
        pc.setEntity(config.getString("PackageConfig.Entity"));
        pc.setModuleName(config.getString("PackageConfig.ModuleName"));
        pc.setController(config.getString("PackageConfig.Controller"));
        mpg.setPackageInfo(pc);

        //配置自定义模板
        TemplateConfig templateConfig = new TemplateConfig().setController("templates/api.java");
        mpg.setTemplate(templateConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        mpg.setCfg(injectionConfig);

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        //生成一个自定义的Api
//        focList.add(new FileOutConfig("/templates/api.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return gc.getOutputDir() + "/com/cloud/wx/cp/api/"
//                        + tableInfo.getEntityName() + "Api" + StringPool.DOT_JAVA;
//            }
//        });
//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录");
//                return false;
//            }
//        });
        injectionConfig.setFileOutConfigList(focList);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setSuperControllerClass("");
        strategy.setRestControllerStyle(true);
        // 表名生成策略
        strategy.setEntityLombokModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(config.getStringArray("TablePrefix"));
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setVersionFieldName("version");
        strategy.setLogicDeleteFieldName("deleted");
        mpg.setStrategy(strategy);

        mpg.execute();
    }
}