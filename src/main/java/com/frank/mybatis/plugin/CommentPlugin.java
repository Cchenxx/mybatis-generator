package com.frank.mybatis.plugin;

import com.frank.mybatis.utils.JsonUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName com.frank.mybatis.plugin.CommentPlugin
 * @Description 自定义注释生成
 * @Author Lambert
 */
public class CommentPlugin extends PluginAdapter {

    private Boolean makeConstant = false;

    private String author = null;

    private static final String JSON_PREFIX = "{";

    private static final String JSON_SUFFIX = "}";

    private static final String SEPARATION = "#";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String makeConstant = this.properties.getProperty("makeConstant");
        if (StringUtility.stringHasValue(makeConstant)) {
            this.makeConstant = "TRUE".equalsIgnoreCase(makeConstant);
        }
        String author = this.properties.getProperty("author");
        if (StringUtility.stringHasValue(author)) {
            this.author = author;
        }
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.getJavaDocLines().clear();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @describe: " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * @create: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        topLevelClass.addJavaDocLine(" * @table: " + introspectedTable.getFullyQualifiedTable());
        if (StringUtility.stringHasValue(this.author)) {
            topLevelClass.addJavaDocLine(" * @author: " + this.author);
        }
        topLevelClass.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        this.comment(field, topLevelClass, introspectedColumn, introspectedTable);
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return true;
    }

    private void comment(JavaElement element, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable) {
        element.getJavaDocLines().clear();
        element.addJavaDocLine("/**");
        String remark = introspectedColumn.getRemarks();
        if (remark != null && remark.length() > 1) {
            element.addJavaDocLine(" * " + remark);
            element.addJavaDocLine(" *");
        }
        element.addJavaDocLine(" * Column:    " + introspectedColumn.getActualColumnName());
        element.addJavaDocLine(" * Length:    " + introspectedColumn.getLength());
        element.addJavaDocLine(" * DefaultValue:  " + (StringUtility.stringHasValue(introspectedColumn.getDefaultValue()) ? introspectedColumn.getDefaultValue() : "无默认值"));
        element.addJavaDocLine(" * Nullable:  " + introspectedColumn.isNullable());
        boolean autoIncrement = introspectedColumn.isAutoIncrement();
        if (autoIncrement) {
            element.addJavaDocLine(" * AutoIncrement:  true");
        }
        element.addJavaDocLine(" */");
        if (this.makeConstant) {
            System.out.println("start constant " + introspectedColumn.getActualColumnName());
            if (StringUtility.stringHasValue(remark) && remark.contains(JSON_PREFIX) && remark.contains(JSON_SUFFIX)) {
                String commentJson = remark.substring(remark.indexOf(JSON_PREFIX), remark.lastIndexOf(JSON_SUFFIX) + 1);
                try {
                    LinkedHashMap<String, String> commentMap = JsonUtil.fromJson(LinkedHashMap.class, commentJson);
                    commentMap.forEach((key, value) -> {
                        Field field = new Field();
                        field.setName(introspectedColumn.getActualColumnName().toUpperCase() + "_" + key.toUpperCase());
                        field.setStatic(true);
                        field.setFinal(true);
                        field.setType(introspectedColumn.getFullyQualifiedJavaType());
                        String desc = "";
                        String constant = "";
                        if (value.contains(SEPARATION)) {
                            String[] split = value.split(SEPARATION);
                            constant = split[0];
                            desc = split[1];
                        } else {
                            constant = value;
                        }
                        field.setInitializationString(constant);
                        field.setVisibility(JavaVisibility.PRIVATE);
                        field.addJavaDocLine("/**");
                        field.addJavaDocLine("* " + introspectedColumn.getActualColumnName() + ":" + desc);
                        field.addJavaDocLine("*/");
                        if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3_DSQL) {
                            context.getCommentGenerator().addFieldAnnotation(field, introspectedTable,
                                    topLevelClass.getImportedTypes());
                        } else {
                            context.getCommentGenerator().addFieldComment(field, introspectedTable);
                        }
                        topLevelClass.addField(field);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.commentResultMap(element, introspectedTable);
        return true;
    }

    @Override
    public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.commentResultMap(element, introspectedTable);
        return true;
    }

    void commentResultMap(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> es = element.getElements();
        if (!es.isEmpty()) {
            String alias = introspectedTable.getTableConfiguration().getAlias();
            int aliasLen = -1;
            if (alias != null) {
                aliasLen = alias.length() + 1;
            }

            Iterator<Element> it = es.iterator();
            HashMap map = new HashMap();

            while (true) {
                while (it.hasNext()) {
                    Element e = (Element) it.next();
                    if (e instanceof TextElement) {
                        it.remove();
                    } else {
                        XmlElement el = (XmlElement) e;
                        List<Attribute> as = el.getAttributes();
                        if (!as.isEmpty()) {
                            String col = null;
                            Iterator i$ = as.iterator();

                            while (i$.hasNext()) {
                                Attribute a = (Attribute) i$.next();
                                if (a.getName().equalsIgnoreCase("column")) {
                                    col = a.getValue();
                                    break;
                                }
                            }

                            if (col != null) {
                                if (aliasLen > 0) {
                                    col = col.substring(aliasLen);
                                }

                                IntrospectedColumn ic = introspectedTable.getColumn(col);
                                if (ic != null) {
                                    StringBuilder sb = new StringBuilder();
                                    if (ic.getRemarks() != null && ic.getRemarks().length() > 1) {
                                        sb.append("<!-- ");
                                        sb.append(ic.getRemarks());
                                        sb.append(" -->");
                                        map.put(el, new TextElement(sb.toString()));
                                    }
                                }
                            }
                        }
                    }
                }

                if (map.isEmpty()) {
                    return;
                }

                Set<Element> set = map.keySet();
                Iterator i$ = set.iterator();

                while (i$.hasNext()) {
                    Element e = (Element) i$.next();
                    int id = es.indexOf(e);
                    es.add(id, (Element) map.get(e));
                    es.add(id, new TextElement(""));
                }

                return;
            }
        }
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    private void removeAttribute(List<Attribute> as, String name) {
        if (!as.isEmpty()) {
            Iterator it = as.iterator();

            Attribute attr;
            do {
                if (!it.hasNext()) {
                    return;
                }

                attr = (Attribute) it.next();
            } while (!attr.getName().equalsIgnoreCase(name));

            it.remove();
        }
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        document.getRootElement().addElement(new TextElement(""));
        document.getRootElement().addElement(new TextElement("<!-- ### 以上代码由asc-common-db CommentPlugin自动生成, 生成时间: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + " ### -->\n\n\n"));
        document.getRootElement().addElement(new TextElement("<!-- Your codes goes here!!! -->"));
        document.getRootElement().addElement(new TextElement(""));
        return true;
    }
}