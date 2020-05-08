package com.hwj.demo.component.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.sql.Types;
import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class JSR303Plugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        if (!introspectedColumn.isNullable()) {
            if (!introspectedColumn.isIdentity()) {
                topLevelClass.addImportedType("javax.validation.constraints.NotEmpty");
                field.addAnnotation(String.format("@NotEmpty(message = \"%s不能为空！\")",introspectedColumn.getRemarks()));
            }
        }

        if (introspectedColumn.isStringColumn()) {
            topLevelClass.addImportedType("javax.validation.constraints.Size");
            field.addAnnotation(String.format("@Size(min = 0, max = %d , message = \"%s的长度必须大于{min}，小于{max}！\")", introspectedColumn.getLength(), introspectedColumn.getRemarks() ));
        }

        if (introspectedColumn.getJdbcType() == Types.INTEGER) {
            topLevelClass.addImportedType("javax.validation.constraints.Max");
            field.addAnnotation(String.format("@Max(value=2147483647,message=\"%s的长度不能大于{value}！\")", introspectedColumn.getRemarks()));
            topLevelClass.addImportedType("javax.validation.constraints.Min");
            field.addAnnotation(String.format("@Min(value=0,message=\"%s的长度不能小于{value}！\")",introspectedColumn.getRemarks()));
        }

        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);
    }
}
