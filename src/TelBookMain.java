import java.util.Scanner;

public class TelBookMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            do {
                System.out.println("1.입력 2.수정 3.삭제 4.전체출력 5.ID검색 6.종료");
                System.out.println("▶ 메뉴 입력 : ");
                input = sc.nextInt();
            } while (input < 1 || input > 6);
            switch (input) {
                case 1:
                    break;
                case2:
                    break;
                case3:
                    break;
                case4:
                    break;
                case5:
                    break;
                case6:
                    System.out.println("종료합니다.");
            }
        }
    }
}
