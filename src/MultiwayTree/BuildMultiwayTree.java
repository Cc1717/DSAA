package MultiwayTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 17yyxovo
 * @version 1.0
 * @date 2021/10/10 17:53
 */
public class BuildMultiwayTree {
	public static void main(String[] args) {
		List<Menu1> menu1s = new ArrayList<>();
		menu1s.add(new Menu1(10,"10.0",0));
		menu1s.add(new Menu1(20,"20.0",0));
		menu1s.add(new Menu1(30,"30.0",0));
		menu1s.add(new Menu1(40,"40.0",10));
		menu1s.add(new Menu1(50,"50.0",10));
		menu1s.add(new Menu1(60,"60.0",20));
		menu1s.add(new Menu1(70,"70.0",20));
		menu1s.add(new Menu1(80,"80.0",30));
		menu1s.add(new Menu1(90,"90.0",30));
		menu1s.add(new Menu1(100,"100.0",40));
		menu1s.add(new Menu1(110,"110.0",60));
		for (Menu2 menu2 : convert(menu1s)) {
			System.out.println(menu2.toString()+'\n');
		}
	}

	private static List<Menu2> convert(List<Menu1> menu1s){
		Menu2 menu2 = new Menu2(0, null, null);
		List<Menu2> menu2s = new ArrayList<>();
		menu2s.add(menu2);
		convert2(menu2s,menu1s);
		return menu2.menuList;
	}

	private static void convert2(List<Menu2> menu2s,List<Menu1> menu1s){
		if(menu1s.size()==0){
			return;
		}
		List<Menu2> nextMenu2s = new ArrayList<>();
		for(Menu2 menu2:menu2s){
			List<Menu1> filterMenu1s = menu1s.stream().filter(menu1 -> {
				return menu1.parentId == menu2.menuId;
			}).collect(Collectors.toList());
			menu1s.removeAll(filterMenu1s);
			List<Menu2> convertMenu2s = filterMenu1s.stream().map(menu -> {
				return new Menu2(menu.menuId, menu.name, null);
			}).collect(Collectors.toList());
			menu2.menuList = convertMenu2s;
			nextMenu2s.addAll(convertMenu2s);
		}
		convert2(nextMenu2s,menu1s);
	}
}

class Menu1{
	public long menuId;
	public String name;
	public long parentId;

	public Menu1(long menuId, String name, long parentId) {
		this.menuId = menuId;
		this.name = name;
		this.parentId = parentId;
	}
}

class Menu2{
	public long menuId;
	public String name;
	public List<Menu2> menuList;

	public Menu2(long menuId, String name, List<Menu2> menuList) {
		this.menuId = menuId;
		this.name = name;
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "Menu2{" +
				"menuId=" + menuId +
				", name='" + name + '\'' +
				", menuList=" + menuList +
				'}';
	}
}

