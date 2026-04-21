import db.DBConn;
import view.UserView;

import java.sql.Connection;
import java.util.Scanner;

public class TelBookMain {
    public static void main(String[] args) throws Exception {
//        Connection connection = DBConn.getConnection();
        
        Scanner sc = new Scanner(System.in);

        // UserView 인스턴스를 생성
        UserView userView = new UserView(sc);
        int input;
        while (true) {
            do {
                System.out.println("1.입력 2.수정 3.삭제 4.전체출력 5.ID검색 6.종료");
                System.out.println("▶ 메뉴 입력 : ");
                input = sc.nextInt();
            } while (input < 1 || input > 6);
            switch (input) {
                case 1:
                    userView.insert();
                    break;
                case 2:
                    userView.update();
                    break;
                case 3:
                    userView.delete();
                    break;
                case 4:
                    userView.searchAll();
                    break;
                case 5:
                    userView.searchOne();
                    break;
                case 6:
                    System.out.println("종료합니다.");
            }
        }
    }
}
