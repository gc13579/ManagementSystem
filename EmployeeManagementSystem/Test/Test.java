import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.web.VO.PermissionsVO;
import com.web.VO.UserVO;
import com.web.factory.ObjectFactory;
import com.web.mapper.MenuMapper;
import com.web.mapper.RoleMapper;
import com.web.pojo.Menu;
import com.web.pojo.Role;
import com.web.pojo.User;
import com.web.service.MenuService;
import com.web.service.PermissionsService;
import com.web.service.PermissionsVOService;
import com.web.service.RoleService;
import com.web.service.UserVOService;
import com.web.service.impl.MenuServiceImpl;
import com.web.util.JDBCTemplate;

public class Test {
	static MenuService menuServiceImpl;
	static PermissionsVOService permissionsVOService;
	static PermissionsService permissionsService;
	static RoleService roleService;
	static UserVOService userVOService;

	public static void main(String[] args) throws Exception {
		System.out.println(getInfo());
		System.out.println("父节点：" + getstr(getInfo()));
		System.out.println("子节点" + subJieDian(getInfo(), getstr(getInfo())));
		System.out.println("转成数组之前:" + subJieDian(getInfo(), getstr(getInfo())).get(3));
		System.out.println("length:" + sss(subJieDian(getInfo(), getstr(getInfo())).get(3)).length);
		System.out.println(subJieDian(getInfo(), getstr(getInfo())));
		System.out.println(subJieDian(getInfo(), getstr(getInfo())).get(0));
		System.out.println(subJieDian(getInfo(), getstr(getInfo())).get(1));
		System.out.println(subJieDian(getInfo(), getstr(getInfo())).get(2));
		System.out.println(subJieDian(getInfo(), getstr(getInfo())).get(3));
		for (int i = 1; i <= getstr(getInfo()).size(); i++) {
			if (getstr(getInfo()).get(i) == null) {
				continue;
			}
			for (int j = 0; j < sss(subJieDian(getInfo(), getstr(getInfo())).get(i)).length; j++) {
				System.out.println("第" + j + "个二级标签:" + sss(subJieDian(getInfo(), getstr(getInfo())).get(i))[j]);
			}
			System.out.println("---------");
		}

		// permissionsService=(PermissionsService)
		// ObjectFactory.getBean("permissionsService");
		// permissionsService.removePermissionsVOById("2");
		// JDBCTemplate<Menu> temp = new JDBCTemplate<Menu>();
		// String sql=new StringBuffer()
		// .append(" select ")
		// .append(" 	t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time ")
		// .append(" from ")
		// .append(" 	t_menu ")
		// .toString();
		// List<Menu> list=temp.selectAll(new MenuMapper(), sql);
		// for (Menu menu : list) {
		// System.out.println(menu);
		// }
		// roleService = (RoleService) ObjectFactory.getBean("roleService");
		// JDBCTemplate<Role> temp = new JDBCTemplate<Role>();
		// String sql = new
		// StringBuffer().append(" select ").append("  	t_role_id,t_role_name,t_create_time ").append(" from ").append(" 	t_role ").toString();
		// List<Role> list = temp.selectAll(new RoleMapper(), sql);
		// for (Role role : list) {
		// System.out.println(role);
		// }

		// userVOService=(UserVOService) ObjectFactory.getBean("userVOService");
		// List<UserVO> list=userVOService.getAllUserVos("admin", "正常", "管理员");
		// System.out.println("@@@@@@"+list.size());
		// for (UserVO userVO : list) {
		// System.out.println("!!!!!!!!!!!!!!:"+userVO);
		// }

	}

	public static String[] sss(String str) {
		String[] strs = str.split(" ");
		return strs;
	}

	public static List<Menu> getInfo() throws Exception {
		MenuService menuServiceImpl;
		menuServiceImpl = (MenuServiceImpl) ObjectFactory.getBean("menuService");
		User user = new User(new Integer(1), "admin", "admin", "qqq", new Integer(1), "正常", new Date(System.currentTimeMillis()));
		List<Menu> list = menuServiceImpl.selectAllMenuNames(user);
		return list;
	}

	public static HashMap<Integer, String> getstr(List<Menu> list) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (Menu menu : list) {
			// 保存根节点
			// key:根节点id ,value:根结点名字
			if (menu.getT_parent_id() == 0) {
				map.put(menu.getT_menu_id(), menu.getT_menu_name());
			}
		}
		// 获取所有根结点
		return map;
	}

	public static HashMap<Integer, String> subJieDian(List<Menu> list, HashMap<Integer, String> map) {
		HashMap<Integer, String> subMap = new HashMap<Integer, String>();
		// list:所有菜单
		String str = "";
		// set为所有根节点map的key集合
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			// 遍历根节点的id,即key
			for (Menu menu : list) {
				// 若根节点的id(i) 和 菜单的父节点id相同
				if (menu.getT_parent_id() == i) {
					// str添加子菜单名
					str += menu.getT_menu_name() + " ";
				}

			}
			subMap.put(i, str);
			str = "";
		}
		// 获取根节点的子节点
		return subMap;
	}
}
