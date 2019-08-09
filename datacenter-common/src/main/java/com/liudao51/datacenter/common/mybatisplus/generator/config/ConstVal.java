/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.liudao51.datacenter.common.mybatisplus.generator.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.nio.charset.Charset;

/**
 * <p>
 * 定义常量
 * </p>
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-31
 */
public interface ConstVal {

    String MODULE_NAME = "ModuleName";

    String ENTITY = "Entity";
    String DAO = "Dao";
    String DAO_IMPL = "DaoImpl";
    String SERVICE = "Service";
    String SERVICE_IMPL = "ServiceImpl";
    String MAPPER = "Mapper";
    String XML = "Xml";
    String CONTROLLER = "Controller";
    String CONTROLLER_IMPL = "ControllerImpl";

    String ENTITY_PATH = "entity_path";
    String DAO_PATH = "dao_path";
    String DAO_IMPL_PATH = "dao_impl_path";
    String SERVICE_PATH = "service_path";
    String SERVICE_IMPL_PATH = "service_impl_path";
    String MAPPER_PATH = "mapper_path";
    String XML_PATH = "xml_path";
    String CONTROLLER_PATH = "controller_path";
    String CONTROLLER_IMPL_PATH = "controller_impl_path";

    String JAVA_TMPDIR = "java.io.tmpdir";
    String UTF8 = Charset.forName("UTF-8").name();
    String UNDERLINE = "_";

    String JAVA_SUFFIX = StringPool.DOT_JAVA;
    String KT_SUFFIX = ".kt";
    String XML_SUFFIX = ".xml";

    String TEMPLATE_BASE_PATH = "/templates/";
    String TEMPLATE_ENTITY_JAVA = TEMPLATE_BASE_PATH + "entity.java";
    String TEMPLATE_ENTITY_KT = TEMPLATE_BASE_PATH + "entity.kt";
    String TEMPLATE_MAPPER = TEMPLATE_BASE_PATH + "mapper.java";
    String TEMPLATE_XML = TEMPLATE_BASE_PATH + "mapper.xml";
    String TEMPLATE_DAO = TEMPLATE_BASE_PATH + "dao.java";
    String TEMPLATE_DAO_IMPL = TEMPLATE_BASE_PATH + "daoImpl.java";
    String TEMPLATE_SERVICE = TEMPLATE_BASE_PATH + "service.java";
    String TEMPLATE_SERVICE_IMPL = TEMPLATE_BASE_PATH + "serviceImpl.java";
    String TEMPLATE_CONTROLLER = TEMPLATE_BASE_PATH + "controller.java";
    String TEMPLATE_CONTROLLER_IMPL = TEMPLATE_BASE_PATH + "controllerImpl.java";

    String VM_LOAD_PATH_KEY = "file.resource.loader.class";
    String VM_LOAD_PATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    String SUPER_MAPPER_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.BaseMapper";
    String SUPER_DAO_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.IService";
    String SUPER_DAO_IMPL_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.ServiceImpl";
    String SUPER_SERVICE_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.IService";
    String SUPER_SERVICE_IMPL_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.ServiceImpl";
    String SUPER_CONTROLLER_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.IController";
    String SUPER_CONTROLLER_IMPL_CLASS = "com.liudao51.datacenter.common.mybatisplus.generator.strategy.IController";

}
