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

    public UserView(Scanner scanner, TelBookService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void insert() throws MyException{
        // 검증 클래스 생성
        InputValidation validation = new InputValidation();
        // 입력자료 저장을 위한 변수 선언
        String name= "";
        int age = 0;
        String phone = "";
        String address="";

        // 이름, 나이, 주소, 전화번호
        System.out.println("== 전화번호 등록 ==");
        // 이름이 옳바른 값이 들어올 때 까지 반복
        boolean nameOk = false;
        do {
            try {
                // 이름 : 무조건 한글만 중간 공백없이
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
        } while (!ageOk);

        System.out.println("주소 : ");
        address = scanner.next();
        // 전화번호 (010-XXXX-XXXX)
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
        } while (!phoneOk);

        service.insert(name, age, address, phone);
    }

    public void update() {
    }

    public void delete() {
        System.out.println("삭제 할 ID : ");
        int id = scanner.nextInt();
        // 서비스에 호출한 이후에 삭제 성공 유무를  리턴 받아서
        // 뷰에서 출력 (성공 : 1, 실패 : 0)
        int result = service.delete(id);
        System.out.println(result == 1
        ? "ID : " + id + "삭제 완료!"
        : "삭제 실패 : ID 확인하세");
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
//        list.forEach( x -> System.out.println(x));
    }

    public void searchOne() {
        System.out.println("검색 할 ID :");
        int id = scanner.nextInt();
        List<TelDto> list = service.getListOne(id);
        if (list.isEmpty()) {
            System.out.println("해당 ID가 없습니다.");
        } else {
            list.forEach(x -> System.out.println(x));
        }
    }
}
