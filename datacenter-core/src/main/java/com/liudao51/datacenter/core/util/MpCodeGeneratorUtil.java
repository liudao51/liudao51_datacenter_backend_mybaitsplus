package com.liudao51.datacenter.core.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mybatis-Plus 代码生成器
 * </p>
 */
public class MpCodeGeneratorUtil {

    //项目路径
    private static String canonicalPath = "";

    /**
     * <p>
     * Mysql 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        try {
            canonicalPath = new File("").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(canonicalPath + "/datacenter-core/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setOpen(false);//生成后打开文件夹
        gc.setAuthor("jewel");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //TODO: 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://193.168.1.12:3306/information_schema?useUnicode=true&characterEncoding=utf-8");
        mpg.setDataSource(dsc);

        //TODO: 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix(new String[]{""});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"TABLES"}); // 需要生成的表
        mpg.setStrategy(strategy);

        //TODO: 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.amii.core");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //TODO: 自定义属性注入
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.add(new FileOutConfig("mp_code_generator_templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return canonicalPath + "/datacenter-core/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(fileOutList);
        mpg.setCfg(cfg);

        //TODO: 配置模板
        TemplateConfig tc = new TemplateConfig();

        //指定自定义模板路径位置：/resources/mp_code_generator_templates/entity2.java.ftl(或者是.vm)
        //PS: 注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
        tc.setXml("mp_code_generator_templates/mapper.xml");
        tc.setMapper("mp_code_generator_templates/mapper.java");
        tc.setEntity("mp_code_generator_templates/entity.java");
        tc.setEntityKt("mp_code_generator_templates/entity.kt");
        tc.setService("mp_code_generator_templates/service.java");
        tc.setServiceImpl("mp_code_generator_templates/serviceImpl.java");
        tc.setController("mp_code_generator_templates/controller.java");
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }
}