package common.enums;

/**
 * 物品特性
 */
public enum itemProperties {

    DURABLE(0 , "耐用品"),
    CONSUMABLES(1 , "消耗品")
    ;

    private final Integer code;
    private final String desc;

    itemProperties(Integer code , String desc){
        this.code = code;
        this.desc = desc;
    }

}
