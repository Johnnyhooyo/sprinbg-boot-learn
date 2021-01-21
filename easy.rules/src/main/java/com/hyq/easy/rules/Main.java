package com.hyq.easy.rules;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.BasicRule;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 * @author：huyuanqiang
 * @time: 2020-07-12 10:32
 * @description:
 **/
public class Main {

    public static void main(String[] args) {
        CodeMethod codeMethod = new CodeMethod("hardCoding");
        CodeMethod codeMethod1 = new CodeMethod("softCoding");
        Fact<CodeMethod> fact = new Fact<>("hardCoding", codeMethod);

        Facts facts = new Facts();
        facts.add(fact);
        facts.put("softCoding", codeMethod1);

        Rules rules = new Rules();
        rules.register(new RuleHardCoding());

        RulesEngine engine = new DefaultRulesEngine();
        engine.fire(rules, facts);
    }

}
