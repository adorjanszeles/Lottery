package com.lottery.listener;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import java.util.Map;

public class LottoAgendaEventListener extends DefaultAgendaEventListener {

    private String ruleName;
    private Integer countFire;

    public LottoAgendaEventListener() {
        this.countFire = 0;
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        Rule rule = event.getMatch().getRule();
        this.ruleName = event.getMatch().getRule().getName();

        Map<String, Object> ruleMetaDataMap = rule.getMetaData();

        StringBuilder sb = new StringBuilder("Rule fired: ").append(this.ruleName);

        if (ruleMetaDataMap.size() > 0) {
            sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
            for (String key : ruleMetaDataMap.keySet()) {
                sb.append("\n    key=" + key + ", value=" + ruleMetaDataMap.get(key));
            }
        }

        this.countFire++;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Integer getCountFire() {
        return countFire;
    }
}
