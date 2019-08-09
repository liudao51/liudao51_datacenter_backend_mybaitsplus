package com.liudao51.datacenter.common.mybatisplus;


import com.baomidou.mybatisplus.annotation.DbType;
import com.liudao51.datacenter.common.mybatisplus.generator.AutoGenerator;
import com.liudao51.datacenter.common.mybatisplus.generator.InjectionConfig;
import com.liudao51.datacenter.common.mybatisplus.generator.config.*;
import com.liudao51.datacenter.common.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.liudao51.datacenter.common.mybatisplus.generator.config.po.TableInfo;
import com.liudao51.datacenter.common.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mybatis-Plus 代码生成器
 * 参考: https://mp.baomidou.com/guide/generator.html
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
        gc.setOutputDir(canonicalPath + "/datacenter-common/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setOpen(false);//生成后打开文件夹
        gc.setAuthor("jewel");
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setDaoName("I%sDao");
        gc.setDaoImplName("%sDaoImpl");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("I%sController");
        gc.setControllerImplName("%sControllerImpl");
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

        //TODO: 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.liudao51.datacenter.common");
        pc.setController("controller");
        pc.setControllerImpl("controller.impl");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setDao("dao");
        pc.setDaoImpl("dao.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        //TODO: 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperMapperClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.IBaseMapper");
        strategy.setSuperDaoClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.IBaseDao");
        strategy.setSuperDaoImplClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.impl.BaseDaoImpl");
        strategy.setSuperServiceClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.IBaseService");
        strategy.setSuperServiceImplClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.impl.BaseServiceImpl");
        strategy.setSuperControllerClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.IBaseController");
        strategy.setSuperControllerImplClass("com.liudao51.datacenter.common.mybatisplus.generator.strategy.impl.BaseControllerImpl");
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setTablePrefix(new String[]{""});   // 此处可以修改为：表前缀(如:dc_)
        strategy.setInclude(new String[]{"TABLES"}); // 此处可以修改为：需要生成的表(如:dc_user)
        mpg.setStrategy(strategy);

        //TODO: 配置模板
        TemplateConfig tc = new TemplateConfig();
        //指定自定义模板位置: resources/mp_code_generator_templates/entity.java.vm(或.ftl); 这里不要加resources路径及vm(或.ftl)文件后缀名, 代码生成器会根据实际使用的模板引擎自动识别
        String templateBasePath = "/mp_code_generator_templates/";
        tc.setXml(null); //不在mapper文件夹下生成*Mapper.xml文件,改用通过<自定义属性注入>设置生成resources/mapper/*Mapper.xml文件
        tc.setMapper(templateBasePath + "mapper.java");
        tc.setEntity(templateBasePath + "entity.java");
        tc.setEntityKt(templateBasePath + "entity.kt");
        tc.setDao(templateBasePath + "dao.java");
        tc.setDaoImpl(templateBasePath + "daoImpl.java");
        tc.setService(templateBasePath + "service.java");
        tc.setServiceImpl(templateBasePath + "serviceImpl.java");
        tc.setController(templateBasePath + "controller.java");
        tc.setControllerImpl(templateBasePath + "controllerImpl.java");
        mpg.setTemplate(tc);

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
        //设置生成resources/mapper/*Mapper.xml文件
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return canonicalPath + "/datacenter-common/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(fileOutList);
        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();
    }
}