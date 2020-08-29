package JavaBean;

public class PageBean {

    private int currentPage;//当前页面

    private int conut;//总记录数

    private int totalPage;//总页数

    private int pageSize;//每一页的显示记录数


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getConut() {
        return conut;
    }

    public void setConut(int conut) {
        this.conut = conut;
    }

    public int getTotalPage() {
        if(conut%pageSize == 0){
            totalPage = conut/pageSize;
        }else{
            totalPage = conut/pageSize+1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
