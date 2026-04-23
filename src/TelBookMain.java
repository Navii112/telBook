import db.DBConn;
import repository.TelBookRepository;
import service.TelBookService;
import view.UserView;

import java.sql.Connection;
import java.util.Scanner;

public class TelBookMain {
    public static void main(String[] args) throws Exception {
        Connection connection = DBConn.getConnection();
        
        Scanner sc = new Scanner(System.in);
        TelBookRepository repository = new TelBookRepository(connection);
        TelBookService service = new TelBookService(repository);

        // UserView 인스턴스를 생성
        UserView userView = new UserView(sc, service);
        int input;
        while (true) {
            do {
                System.out.println("1.입력 2.수정 3.삭제 4.전체출력 5.ID검색 6.검색 7.종료");
                System.out.println("▶ 메뉴 입력 : ");
                input = sc.nextInt();
            } while (input < 1 || input > 6);
            switch (input) {
                case 1:
                    // 종료
                    userView.insert();
                    break;
                case 2:
                    // 종료
                    userView.update();
                    break;
                case 3:
                    userView.delete();
                    break;
                case 4:
                    // 종료
                    userView.searchAll();
                    break;
                case 5: // id로 1개 검색
                    userView.searchOne();
                    break;
                case 6 : // 검색을 선택하면, 1. 이름 2. 주소를 선택하는 화면이 나오고
                    // 검색 카테고리 선택 번호 : choice
                    // keyword 값으로 받아서 검색기능을 실행
                    userView.search();
                    break;
                case 7:
                    DBConn.close();
                    System.out.println("종료합니다.");
            }
        }
    }
}
