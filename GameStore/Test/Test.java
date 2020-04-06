import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.dao.GameTypeDao;
import com.ssm.dao.impl.GameTypeDaoImpl;
import com.ssm.pojo.GameType;
import com.ssm.pojo.User;

public class Test {
	public static void main(String[] args) {
		File f=new File("C:\\Users\\lenovo\\Desktop\\apache-tomcat-6.0.20\\webapps\\GameStore\\images\\uploadImages\\war_game.jpg");
		System.out.println(f.getAbsolutePath());
		System.out.println(f.exists());
		f.delete();
	}
}
