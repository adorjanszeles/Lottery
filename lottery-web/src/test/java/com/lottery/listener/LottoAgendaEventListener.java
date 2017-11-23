package com.lottery.listener;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import java.util.Map;

/**
 * Custom AgendaEventListened {@link DefaultAgendaEventListener} kiterjesztése
 */
public class LottoAgendaEventListener extends DefaultAgendaEventListener {

    private String ruleName;
    private Integer countFire;

    public LottoAgendaEventListener() {
        this.countFire = 0;
    }

    /**
     * Az override segítségével kiegészítettem az afterMatchFired() metódust, úgy, hogy abból az elsült rule nevét is ki
     * tudjuk szedni, ugyanis a stateful kiesession valamiért a stateless fireallrules() metódusát becsomagolta az
     * execute() metódusba és így az elsült rule nevéhez már nem biztosít hozzáférést...
     *
     * @param event kiesession event
     */
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
