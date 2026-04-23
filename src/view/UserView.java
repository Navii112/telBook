package view;

import dto.TelDto;
import exception.InputValidation;
import exception.MyException;
import service.TelBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner;
    private final TelBookService service;
    // 검증 클래스 생성
    InputValidation validation = new InputValidation();

    public UserView(Scanner scanner, TelBookService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void insert() throws MyException{
        // 입력자료 저장을 위한 변수 선언
        String name="";
        int age=0;
        String phone="";
        String address="";

        // 이름, 나이, 전화번호, 주소
        System.out.println("== 전화번호 등록 ==");
        // 이름 옳바른 값이 들어올 때 까지 반복
        boolean nameOk = false;
        do {
            try {
                // 이름 : 무조건 한글만.. 중간공백 없이
                System.out.println("이름 : ");
                name = scanner.next();
                validation.nameCheck(name);
                nameOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (! nameOk);

        // 나이 : 0세 ~ 120세 사이값
        boolean ageOk = false;
        do {
            try {
                System.out.println("나이 : ");
                age = scanner.nextInt();
                validation.ageCheck(age);
                ageOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (! ageOk);

        // 주소
        System.out.println("주소 : ");
        address = scanner.next();

        // 전화번호(010-XXXX-XXXX)
        boolean phoneOk = false;
        do {
            try {
                System.out.println("전화번호 : ");
                phone = scanner.next();
                validation.phoneCheck(phone);
                phoneOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (! phoneOk);

        service.insert(name, age, address, phone);
    }

    public void update() {
        // 새로 입력받는 자료를 저장할 변수 선언
        String name = "";
        int age = 0;
        String address = "";
        String phone = "";

        System.out.println("수정 할 ID : ");
        int id = scanner.nextInt();
        // 해당 아이디가 존재하는지 확인
        // 메서드의 리턴타입을 쉽게 얻는 법
        // 단축 키 : ctrl + alt + v
        List<TelDto> exits = service.getListOne(id);
        if (exits.isEmpty()) {
            System.out.println("해당 ID가 없습니다.");
            return;
        }
        // ID가 존재하는 경우의 처리
        // 리스트에 들어 있는 검색 결과를 dto에 담아놓는다.
        TelDto oldData = exits.get(0);
//        System.out.println(oldData);
        boolean nameOk = false;
        do {
            try {
                // 이름 : 무조건 한글만.. 중간공백 없이
                System.out.println("수정 전 이름 : ");
                System.out.println(oldData.getName());
                System.out.println("수정 할 이름 : ");
                name = scanner.next();
                validation.nameCheck(name);
                nameOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (! nameOk);

        // 나이 : 0세 ~ 120세 사이값
        boolean ageOk = false;
        do {
            try {
                System.out.println("수정 전 나이 : ");
                System.out.println(oldData.getAge());
                System.out.println("나이 : ");
                age = scanner.nextInt();
                validation.ageCheck(age);
                ageOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (! ageOk);

        // 주소
        System.out.println("수정 전 주소 : ");
        System.out.println(oldData.getAddress());
        System.out.println("주소 : ");
        address = scanner.next();

        // 전화번호(010-XXXX-XXXX)
        boolean phoneOk = false;
        do {
            try {
                System.out.println("수정 전 전화번호 : ");
                System.out.println(oldData.getTelNumber());
                System.out.println("전화번호 : ");
                phone = scanner.next();
                validation.phoneCheck(phone);
                phoneOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (! phoneOk);
        // dto로 서비스에 전달
        oldData.setName(name);
        oldData.setAge(age);
        oldData.setAddress(address);
        oldData.setTelNumber(phone);
        service.update(oldData);
    }

    public void delete() {
        System.out.println("삭제할 ID : ");
        int id = scanner.nextInt();
        // 서비스에 호출한 이후에 삭제 성공유무를 리턴받아서
        // 뷰에서 출력(성공 : 1, 실패 : 0)
        int result = service.delete(id);
        System.out.println(result == 1
                ? "ID : " + id + " 삭제 완료!"
                : "삭제 실패 : ID 확인하세요");
    }

    public void searchAll() {
        List<TelDto> list = new ArrayList<>();
        list = service.getListAll();
        // 리스트가 비어있는지 확인
        if (list.isEmpty()) {
            System.out.println("주소록이 비어있습니다.");
            return;
        }
        // 리스트를 출력
        for (TelDto dto : list) {
            System.out.println(dto);
        }

        // 스트림을 이용해서 출력
//        list.forEach(x -> System.out.println(x));
    }

    public void searchOne() {
        System.out.println("검색 할 ID : ");
        int id = scanner.nextInt();
        List<TelDto> list = service.getListOne(id);
        if (list.isEmpty()) {
            System.out.println("해당 ID가 없습니다.");
        } else {
            list.forEach(x -> System.out.println(x));
        }
    }

    public void search() {
        int choice = 0;
        String keyword = "";
        do {
            System.out.println("검색할 기준을 선택하세요.");
            System.out.println("1. 이름 2. 주소");
            choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("검색할 이름의 일부를 입력 : ");
                keyword = scanner.next();
            } else {
                System.out.println("검색할 주소의 일부를 입력 : ");
                keyword = scanner.next();
            }

        } while (choice < 1 || choice > 2);
        List<TelDto> searchLists = service.search(choice, keyword);
        // 결과 출력
        if (searchLists.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return;
        } else {
            searchLists.forEach(x -> System.out.println(x));
        }
    }
}