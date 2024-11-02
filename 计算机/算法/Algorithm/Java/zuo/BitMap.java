package Java.zuo;

// 位图 储存连续范围的数字 极大节省空间
// 你储存的最大数字maxnum 开出的内存空间为 SpaceSize = maxNum/32
// 使用maxNum的空间 可以储存maxNum*32的数字
// 本来储存0 ~ maxNum中的数字需要maxNum*32位的空间大小 现在使用位图只需要maxNum的空间大小
// 位图实现的功能 初始化支持0~maxNum的后续操作 添加数 删除数 如果数字存在就删除，否则加入 查找数是否在位图中

public class BitMap {
    int[] bitMap;

    // 初始化
    public BitMap(int maxNum) {
        // 向上取整
        bitMap = new int[((maxNum + 31) / 32)];
    }

    // 添加数字
    public void add(int num) {
        bitMap[getIndex(num)] |= 1 << getBit(num);
    }

    // 删除数字
    public void remove(int num) {
        bitMap[getIndex(num)] &= ~(1 << getBit(num));
    }

    // 查找数字
    public boolean contains(int num) {


        return false;
    }

    // 存在就删除 否则添加
    public void reveres(int num) {

    }

    // 获取数字在位图什么位置
    public int getIndex(int num) {
        return num / 32;
    }

    // 获取数字在该位第几位
    public int getBit(int num) {
        return num % 32;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(1000);
        bitMap.add(1);
        bitMap.add(2);
        bitMap.add(500);
        bitMap.add(1000);

        System.out.println("1是否存在:" + bitMap.contains(1));
        System.out.println("2是否存在:" + bitMap.contains(2));
        System.out.println("500是否存在:" + bitMap.contains(500));
        System.out.println("1000是否存在:" + bitMap.contains(1000));
    }
}