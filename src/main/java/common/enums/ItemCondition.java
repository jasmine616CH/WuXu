package common.enums;

/**
 * 物品状态
 */
public enum ItemCondition {

    AVAILABLE(0,"在库"),
    BORROWED(1,"已借出"),
    MAINTENANCE(2,"维修中"),
    SCRAPPED(3,"已报废"),
    LOST(4,"已丢失"),
    SUBSCRIBE(5, "已预定")
    ;

    private final String desc;
    private final int code;

    ItemCondition(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
