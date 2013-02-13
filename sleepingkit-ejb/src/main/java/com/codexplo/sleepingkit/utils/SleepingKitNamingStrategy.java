package com.codexplo.sleepingkit.utils;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 2/13/13
 * Time: 4:28 PM
 */
public class SleepingKitNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String tableName(String tableName) {
        return "tbl_"+tableName.toLowerCase();
    }

    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className);
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return super.propertyToColumnName(propertyName);
    }

    @Override
    public String columnName(String columnName) {
        return super.columnName(columnName);
    }
}
