/**
 * Copyright (C): 恒大集团版权所有 Evergrande Group
 */

import org.junit.Test;
import org.pzy.opensource.codegenerator.domain.bo.DbConnectionInfo;
import org.pzy.opensource.codegenerator.domain.bo.TableInfoBO;
import org.pzy.opensource.codegenerator.domain.bo.WinterCodeGeneratorConfigBO;
import org.pzy.opensource.codegenerator.domain.enums.CodeGeneratorModelEnum;
import org.pzy.opensource.codegenerator.domain.enums.WinterStyleSuperEntityEnum;
import org.pzy.opensource.codegenerator.support.util.CodeGeneratorUtil;

/**
 * CodeGeneratorTest
 *
 * @author pan
 * @date 2020/4/3 10:00
 */
public class CodeGeneratorTest {

    @Test
    public void test() {

        String driverName = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/pzy-acl?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&useSSL=false";
        DbConnectionInfo dbConnectionInfo = new DbConnectionInfo(driverName, username, password, url);

        String moduleName = "acl";
        String parentPackage = "org.pzy.archetypesystem.base.module";
        String projectPath = "/Users/pan/workspace/idea/pzy-opensource/pzy-archetype-system/pzy-acl-system/pzy-acl-rest";
        WinterStyleSuperEntityEnum superEntityInfoBO = WinterStyleSuperEntityEnum.LogicDelBaseEntity;
        TableInfoBO tableInfoBO = new TableInfoBO(new String[]{"sys_user", "sys_role"});

        WinterCodeGeneratorConfigBO winterCodeGeneratorConfigBO = new WinterCodeGeneratorConfigBO(dbConnectionInfo, moduleName, parentPackage, projectPath, superEntityInfoBO, tableInfoBO);
        winterCodeGeneratorConfigBO.setCodeGeneratorModelEnum(CodeGeneratorModelEnum.ONLY_CONTROLLER);
        CodeGeneratorUtil.generateWinterStyle(winterCodeGeneratorConfigBO);
    }
}
