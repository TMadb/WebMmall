package Advanced.ORMTest;

import java.util.Date;

@TableAnnotation(tableName = "mmall_product")
public class ProductBean {

    @ColumnAnnotation(columnName = "id")
    private int id;

    @ColumnAnnotation(columnName = "category_id")
    private int category_id;

    @ColumnAnnotation(columnName = "name")
    private String name;

    @ColumnAnnotation(columnName = "subtitle")
    private String subtitle;

    @ColumnAnnotation(columnName = "main_image")
    private String main_image;

    @ColumnAnnotation(columnName = "sub_images")
    private String sub_images;

    @ColumnAnnotation(columnName = "details")
    private String details;

    @ColumnAnnotation(columnName = "price")
    private double price;

    @ColumnAnnotation(columnName = "stock")
    private int stock;

    @ColumnAnnotation(columnName = "status")
    private int status;

    @ColumnAnnotation(columnName = "create_time")
    private Date create_time;

    @ColumnAnnotation(columnName = "update_time")
    private Date update_time;

    private int count = 1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getSub_images() {
        return sub_images;
    }

    public void setSub_images(String sub_images) {
        this.sub_images = sub_images;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", main_image='" + main_image + '\'' +
                ", sub_images='" + sub_images + '\'' +
                ", details='" + details + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
