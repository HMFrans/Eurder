package com.switchfully.eurder.security;

import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    MEMBER(newArrayList(Feature.CREATE_ORDER, Feature.GET_ALL_ORDERS_BY_MEMBER)),
    ADMIN(newArrayList(Feature.ADD_ITEM, Feature.MAKE_ADMIN, Feature.GET_ALL_MEMBERS, Feature.GET_MEMBER));

    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
