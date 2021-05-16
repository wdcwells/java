package com.wdc.test.hasor.dao;

import net.hasor.dataql.Finder;
import net.hasor.dataql.Query;
import net.hasor.dataql.QueryResult;
import net.hasor.dataql.compiler.QueryModel;
import net.hasor.dataql.compiler.qil.QIL;
import net.hasor.dataql.domain.DataModel;
import net.hasor.dataql.runtime.QueryHelper;
import org.junit.Test;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

/**
 * @author wdc
 * @version 1.0.0
 * @since 2020-05-29 09:01
 */
public class DataQlSimpleTest {

    /**
     * JDK1.6开始 Java引入了 JSR223 规范，通过该规范可以用一致的形式在JVM上执行一些脚本语言
     * @throws Exception
     */
    @Test
    public void jsr223() throws Exception {
        //DataQLScriptEngineFactory
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("dataql");
        SimpleScriptContext scriptContext = new SimpleScriptContext();
        scriptContext.setBindings(scriptEngine.createBindings(), ScriptContext.GLOBAL_SCOPE);
        scriptContext.setBindings(scriptEngine.createBindings(), ScriptContext.ENGINE_SCOPE);
        scriptContext.setAttribute("uid", "uid form engine", ScriptContext.ENGINE_SCOPE);
        scriptContext.setAttribute("sid", "sid form global", ScriptContext.GLOBAL_SCOPE);
//
        Object eval = scriptEngine.eval("return [${uid},${sid}]", scriptContext);
        DataModel dataModel = ((QueryResult) eval).getData();
        System.out.println(dataModel.unwrap());
    }

    /**
     * 基于底层接口
     * @throws Exception
     */
    @Test
    public void interfaces() throws Exception {
        //解析
        QueryModel queryModel = QueryHelper.queryParser("");
        //编译
        QIL qil = QueryHelper.queryCompiler(queryModel, null, Finder.DEFAULT);
        //执行
        Query dataQuery = QueryHelper.createQuery(qil, Finder.DEFAULT);

    }
}
