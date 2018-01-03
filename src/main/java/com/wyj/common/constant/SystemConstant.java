package com.wyj.common.constant;

/**
 * 系统级静态变量
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月23日 下午12:47:12
 */
public class SystemConstant {
	
	
	/**
	 * 菜单类型
	 *
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    

}
