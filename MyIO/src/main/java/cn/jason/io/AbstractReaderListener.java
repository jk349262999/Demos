package cn.jason.io;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractReaderListener
 * NIO逐行读数据回调方法
 * @author: Jason
 * @date: 2020/3/10
 */
public abstract class AbstractReaderListener {

    /**
     * 一次读取行数，默认为500
     */
    private int readColNum = 500;

    private List<String> list = new ArrayList<String>();

    /**
     * 设置一次读取行数
     *
     * @param readColNum
     */
    protected void setReadColNum(int readColNum) {
        this.readColNum = readColNum;
    }

    /**
     * 每读取到一行数据，添加到缓存中
     *
     * @param lineStr 读取到的数据
     * @param lineNum 行号
     * @param over    是否读取完成
     * @throws Exception
     */
    public void outLine(String lineStr, long lineNum, boolean over) throws Exception {
        if (null != lineStr){
            list.add(lineStr);
        }
        if (!over && (lineNum % readColNum == 0)) {
            output(list);
            list.clear();
        } else if (over) {
            output(list);
            list.clear();
        }
    }

    /**
     * 批量输出
     *
     * @param stringList
     * @throws Exception
     */
    public abstract void output(List<String> stringList) throws Exception;

}
