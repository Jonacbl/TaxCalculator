import utils.*;

/**
 * Main 类是程序的入口点。
 * 它负责初始化应用程序并显示主菜单。
 */
public class Main {
    /**
     * 默认构造函数
     * 暂时未使用
     */
    public Main() {

    }
    /**
     * 主方法，启动程序。
     * 该方法创建了一个 Menu 对象并调用其 showMenu 方法来显示菜单。
     *
     * @param args 命令行参数，目前未使用。
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMenu();
    }
}