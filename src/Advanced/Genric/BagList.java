package Advanced.Genric;


public class BagList<T> {

    //新建Object数组
    Object[] list = null;
    //添加元素的方法
    public void add(T t){

        //判断数组中是否有元素
        if(list == null){
            //创建并把数据存放到数组中
            list = new Object[1];
            list[0] = t;
        }else{
            //如果有相同的元素直接结束方法
            for(int i =0 ;i<list.length;i++){
                if(list[i].equals(t)){
                    return;
                }
            }
        }
        //添加数据(新数组的长度等于老数组的长度+1)
        Object[] newList = new Object[list.length+1];
        //拷贝旧数组的元素到新数组
        for(int i = 0 ; i < list.length ; i++){
            newList[i] = list[i];
        }
        //每次长度只加1，所以旧数组的值拷贝到新数组就是新数组的最后一位，所以要给他重新赋值
        newList[newList.length-1] = t;
        //添加完成，将新数组重新赋值给旧数组
        list = newList;

    }

    //获取元素
    public T get(int i){
        return (T)list[i];
    }

}
