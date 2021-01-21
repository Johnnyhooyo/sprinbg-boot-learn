package com.hyq.easy.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * @author：huyuanqiang
 * @time: 2020-07-12 10:34
 * @description:
 **/
@Rule(name = "hardCoding", description = "hard coding for this question")
public class RuleHardCoding extends AbsRule  {

    @Condition
    @Override
    public boolean isThisCondition(@Fact("hardCoding") Object condition) {
        return condition instanceof CodeMethod && "hardCoding".equals(((CodeMethod) condition).getMethod());
    }

    @Action
    @Override
    public void action(Facts facts) {
        CodeMethod codeMethod = (CodeMethod) facts.get("hardCoding");
        System.out.println("solve this question at method:" + codeMethod.getMethod());
    }

}
