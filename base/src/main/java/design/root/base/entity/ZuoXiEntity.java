package design.root.base.entity;

/**
 * 坐席实体
 */
public class ZuoXiEntity {
    public String name;
    public String price;
    public String count;
    public int type;
    public ZuoXiEntity() {
    }

    public ZuoXiEntity(String name, String price, String count, int type) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.type=type;
    }
}
