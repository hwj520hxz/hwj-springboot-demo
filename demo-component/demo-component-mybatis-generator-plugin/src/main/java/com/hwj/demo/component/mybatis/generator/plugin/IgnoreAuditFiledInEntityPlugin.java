package com.hwj.demo.component.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class IgnoreAuditFiledInEntityPlugin extends PluginAdapter {

    private static final String[] AUDIT_COLUMN = {"rec_Id", "rec_created_by", "rec_created_org", "rec_created_time", "rec_modified_by", "rec_modified_org", "rec_modified_time", "rec_version"};

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable,
                                       Plugin.ModelClassType modelClassType) {
        return handle(introspectedColumn);
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return handle(introspectedColumn);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return handle(introspectedColumn);
    }

    private boolean handle(IntrospectedColumn introspectedColumn) {
        long count = Arrays.stream(AUDIT_COLUMN).filter(x -> x.equalsIgnoreCase(introspectedColumn.getActualColumnName())).count();
        return count == 0;
    }
}
